package com.gildedrose.quality;

import com.gildedrose.model.Item;

public class DefaultQualityControl implements QualityControl {

    @Override
    public void updateQualityFor(Item item) {
        item.setQuality(item.getQuality() - qualityDropFor(item));
    }

    private int qualityDropFor(Item item) {
        int defaultQualityDrop = defaultQualityDropFor(item);
        return item.getQuality() - defaultQualityDrop >= 0
                ? defaultQualityDrop
                : item.getQuality();
    }

    private int defaultQualityDropFor(Item item) {
        return item.getSellIn() < 0
                ? DEFAULT_QUALITY_DROP * 2
                : DEFAULT_QUALITY_DROP;
    }
}
