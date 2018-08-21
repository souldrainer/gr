package com.gildedrose.service;

import com.gildedrose.model.Item;
import com.gildedrose.repo.ItemsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncItemUpdateService {

    private ItemsRepo itemsRepo;

    public AsyncItemUpdateService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncItemUpdateService.class);

    @Async
    @Transactional
    public CompletableFuture<Item> updateItem(Item item) {
        LOGGER.debug("Updating quality for {}", item);
        Item result = itemsRepo.save(item);
        LOGGER.debug("Quality for {} updated", item);
        return CompletableFuture.completedFuture(result);
    }
}
