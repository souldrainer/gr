package com.gildedrose.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

import static java.lang.String.format;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "gilded_rose", type = "items")
public class Item {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private int sellIn;
    private int quality;

    @Override
    public String toString() {
        return format("Item{%s, %s, %s, %s}", this.id, this.name, this.sellIn, this.quality);
    }
}
