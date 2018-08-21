package com.gildedrose.controller;

import com.gildedrose.model.Item;
import com.gildedrose.service.ItemsSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    private ItemsSearchService itemsSearchService;


    @Autowired
    public ApplicationController(ItemsSearchService itemsSearchService) {
        this.itemsSearchService = itemsSearchService;
    }

    @GetMapping({"/list"})
    public List<Item> itemList() {
        LOGGER.info("Got call from /list endpoint");
        return itemsSearchService.findAll();
    }
}
