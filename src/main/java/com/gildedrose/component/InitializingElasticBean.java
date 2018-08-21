package com.gildedrose.component;

import com.gildedrose.model.Item;
import com.gildedrose.service.AsyncItemUpdateService;
import com.gildedrose.service.ItemsSearchService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializingElasticBean implements InitializingBean {

    private ItemsSearchService itemsSearchService;
    private AsyncItemUpdateService asyncItemUpdateService;

    @Autowired
    public InitializingElasticBean(ItemsSearchService itemsSearchService, AsyncItemUpdateService asyncItemUpdateService) {
        this.itemsSearchService = itemsSearchService;
        this.asyncItemUpdateService = asyncItemUpdateService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Item> items = itemsSearchService.findAll();
        if(items.isEmpty()) {
            asyncItemUpdateService.updateItem(Item.builder().name("+5 Dexterity Vest").sellIn(10).quality(20).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Aged Brie").sellIn(2).quality(0).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Elixir of the Mongoose").sellIn(5).quality(7).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(0).quality(80).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(-1).quality(80).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(20).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(49).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(49).build());
            asyncItemUpdateService.updateItem(Item.builder().name("Conjured Mana Cake").sellIn(3).quality(6).build());
        }
    }
}
