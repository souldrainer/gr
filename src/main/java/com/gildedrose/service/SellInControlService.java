package com.gildedrose.service;

import com.gildedrose.model.Item;
import org.springframework.stereotype.Service;

import static com.gildedrose.factory.QualityControlFactory.SULFURAS_ITEM_NAME;

@Service
public class SellInControlService {
    private static final int DEFAULT_DECREASE = 1;
    private static final int NO_DECREASE = 0;

    public void updateSellInFor(Item item) {
        item.setSellIn(item.getSellIn() - sellInDecreaseFor(item));
    }

    private int sellInDecreaseFor(Item item) {
        return SULFURAS_ITEM_NAME.equals(item.getName()) ? NO_DECREASE : DEFAULT_DECREASE;
    }
}
