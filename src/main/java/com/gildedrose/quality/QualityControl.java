package com.gildedrose.quality;

import com.gildedrose.model.Item;

public interface QualityControl {
    int MAX_QUALITY_ALLOWED = 50;
    int DEFAULT_QUALITY_INCREASE = 1;
    int DEFAULT_QUALITY_DROP = 1;

    void updateQualityFor(Item item);
}
