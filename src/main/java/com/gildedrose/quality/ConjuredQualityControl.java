package com.gildedrose.quality;

import com.gildedrose.model.Item;

public class ConjuredQualityControl implements QualityControl {

    private static final int CONJURED_QUALITY_DROP = DEFAULT_QUALITY_DROP * 2;
    private static final int DOUBLE_CONJURED_QUALITY_DROP = CONJURED_QUALITY_DROP * 2;

    @Override
    public void updateQualityFor(Item item) {
        item.setQuality(item.getQuality() - qualityDropFor(item));
    }

    private int qualityDropFor(Item item) {
        if (item.getSellIn() < 0) {
            return item.getQuality() - DOUBLE_CONJURED_QUALITY_DROP >= 0 ? DOUBLE_CONJURED_QUALITY_DROP : item.getQuality();
        } else {
            return item.getQuality() - CONJURED_QUALITY_DROP >= 0 ? CONJURED_QUALITY_DROP : item.getQuality();
        }
    }
}
