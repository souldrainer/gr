package com.gildedrose.quality;

import com.gildedrose.model.Item;

import static java.lang.Math.min;

public class BackstagePassQualityControl implements QualityControl {
    private static final int TEN_DAYS = 10;
    private static final int FIVE_DAYS = 5;

    private static final int NO_EXTRA_QUALITY_INCREASE = 0;
    private static final int DOUBLE_EXTRA_QUALITY_INCREASE = 2;
    private static final int EXTRA_QUALITY_INCREASE = 1;

    public void updateQualityFor(Item backstagePass) {
        backstagePass.setQuality(newQualityFor(backstagePass));
    }

    private int newQualityFor(Item backstagePass) {
        return backstagePass.getSellIn() >= 0
                ? min(backstagePass.getQuality() + extraQualityFor(backstagePass), MAX_QUALITY_ALLOWED)
                : 0;
    }

    private int extraQualityFor(Item backstagePass) {
        if (concertIsThenTenDaysOrMore(backstagePass)) {
            return DEFAULT_QUALITY_INCREASE;
        } else if (concertIsLessThenTenAndMoreOrEqualToFiveDays(backstagePass)) {
            return DEFAULT_QUALITY_INCREASE + EXTRA_QUALITY_INCREASE;
        } else if (concertIsLessThenFiveDays(backstagePass)) {
            return DEFAULT_QUALITY_INCREASE + DOUBLE_EXTRA_QUALITY_INCREASE;
        }
        return NO_EXTRA_QUALITY_INCREASE;
    }

    private boolean concertIsLessThenFiveDays(Item backstagePass) {
        return backstagePass.getSellIn() >= 0 && backstagePass.getSellIn() < FIVE_DAYS;
    }

    private boolean concertIsLessThenTenAndMoreOrEqualToFiveDays(Item backstagePass) {
        return backstagePass.getSellIn() >= FIVE_DAYS && backstagePass.getSellIn() < TEN_DAYS;
    }

    private boolean concertIsThenTenDaysOrMore(Item backstagePass) {
        return backstagePass.getSellIn() >= TEN_DAYS;
    }
}
