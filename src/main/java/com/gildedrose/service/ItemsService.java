package com.gildedrose.service;

import com.gildedrose.model.Item;
import com.gildedrose.repo.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

    private ItemsRepo itemsRepo;

    @Autowired
    public ItemsService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    public Item save(Item item) {
        return itemsRepo.save(item);
    }

    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        Iterable<Item> items = itemsRepo.findAll();
        items.forEach(result::add);
        return result;
    }
}
