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
        itemsService.save(Item.builder().name("Aged Brie").sellIn(15).quality(3).build());
    }
}
