package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.quality.QualityControl;
import com.gildedrose.quality.QualityControlFactory;
import com.gildedrose.sell.SellInControl;

import java.util.List;

class GildedRose {
    private SellInControl sellInControl;
    private QualityControlFactory qualityControlFactory;

    public GildedRose(QualityControlFactory qualityControl, SellInControl sellInControl) {
        this.qualityControlFactory = qualityControl;
        this.sellInControl = sellInControl;
    }

    public void updateQuality(List<Item> items) {
        for (Item item : items) {
            udpateSellInFor(item);
            updateQualityFor(item);
        }
    }

    private void updateQualityFor(Item item) {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(item);
        qualityControl.updateQualityFor(item);
    }

    private void udpateSellInFor(Item item) {
        sellInControl.updateSellInFor(item);
    }
}