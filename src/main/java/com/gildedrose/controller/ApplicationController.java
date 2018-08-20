package com.gildedrose.controller;

import com.gildedrose.model.Item;
import com.gildedrose.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    private ItemsService itemsService;

    @Autowired
    public ApplicationController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping({"/list"})
    public List<Item> itemList(){
        return itemsService.findAll();
    }
}
