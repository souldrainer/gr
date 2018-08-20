package com.gildedrose.repo;

import com.gildedrose.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemsRepo extends ElasticsearchRepository<Item, String> {
}
