package com.gildedrose.service;

import com.gildedrose.model.Item;
import com.gildedrose.repo.ItemsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsSearchService.class);

    private ItemsRepo itemsRepo;

    @Autowired
    public ItemsSearchService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    public List<Item> findAll() {
        LOGGER.debug("Starting to find all");
        List<Item> result = new ArrayList<>();
        Iterable<Item> items = itemsRepo.findAll();
        items.forEach(result::add);
        LOGGER.debug("Find all finsihed");
        return result;
    }

}
