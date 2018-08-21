package com.gildedrose.component;

import com.gildedrose.model.Item;
import com.gildedrose.service.ItemsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializingElasticBean implements InitializingBean {

    private ItemsService itemsService;

    @Autowired
    public InitializingElasticBean(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        itemsService.save(Item.builder().name("+5 Dexterity Vest").sellIn(10).quality(20).build());
        itemsService.save(Item.builder().name("Aged Brie").sellIn(2).quality(0).build());
        itemsService.save(Item.builder().name("Elixir of the Mongoose").sellIn(5).quality(7).build());
        itemsService.save(Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(0).quality(80).build());
        itemsService.save(Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(-1).quality(80).build());
        itemsService.save(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(20).build());
        itemsService.save(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(49).build());
        itemsService.save(Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(49).build());
        itemsService.save(Item.builder().name("Conjured Mana Cake").sellIn(3).quality(6).build());
    }
}
