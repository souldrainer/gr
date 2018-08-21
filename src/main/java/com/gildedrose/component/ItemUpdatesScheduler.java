package com.gildedrose.component;

import com.gildedrose.model.Item;
import com.gildedrose.service.AsyncItemUpdateService;
import com.gildedrose.service.GildedRoseService;
import com.gildedrose.service.ItemsSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ItemUpdatesScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemUpdatesScheduler.class);

    private ItemsSearchService itemsSearchService;
    private AsyncItemUpdateService asyncItemUpdateService;
    private GildedRoseService gildedRoseService;

    @Autowired
    public ItemUpdatesScheduler(ItemsSearchService itemsSearchService, AsyncItemUpdateService asyncItemUpdateService, GildedRoseService gildedRoseService) {
        this.itemsSearchService = itemsSearchService;
        this.asyncItemUpdateService = asyncItemUpdateService;
        this.gildedRoseService = gildedRoseService;
    }

    @Scheduled(cron = "${scheduler.cron}")
    public void scheduleItemsUpdateTask() {
        LOGGER.info("Starting items update task");
        List<Item> itemList = itemsSearchService.findAll();
        gildedRoseService.updateQuality(itemList);
        CompletableFuture[] futures = new CompletableFuture[itemList.size()];
        for (Item item : itemList) {
            CompletableFuture<Item> i = asyncItemUpdateService.updateItem(item);
            futures[itemList.indexOf(item)] = i;
        }
        CompletableFuture.allOf(futures).join();
        LOGGER.info("Finished items update task");

    }
}
