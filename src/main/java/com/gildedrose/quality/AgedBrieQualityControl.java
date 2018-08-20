package com.gildedrose.quality;

import com.gildedrose.model.Item;

import static java.lang.Math.min;

public class AgedBrieQualityControl implements QualityControl {

    @Override
    public void updateQualityFor(Item item) {
        item.setQuality(newQualityFor(item));
    }

    private int newQualityFor(Item item) {
        return item.getSellIn() < 0 ?
                min(item.getQuality() + DEFAULT_QUALITY_INCREASE * 2, MAX_QUALITY_ALLOWED) :
                min(item.getQuality() + DEFAULT_QUALITY_INCREASE, MAX_QUALITY_ALLOWED);
    }
}
