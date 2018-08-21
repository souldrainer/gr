package com.gildedrose.service;

import com.gildedrose.factory.QualityControlFactory;
import com.gildedrose.model.Item;
import com.gildedrose.quality.QualityControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GildedRoseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GildedRoseService.class);

    private SellInControlService sellInControlService;
    private QualityControlFactory qualityControlFactory;

    @Autowired
    public GildedRoseService(QualityControlFactory qualityControl, SellInControlService sellInControlService) {
        this.qualityControlFactory = qualityControl;
        this.sellInControlService = sellInControlService;
    }

    public void updateQuality(List<Item> items) {
        LOGGER.debug("Starting to update quality for items list");
        for (Item item : items) {
            updateSellInFor(item);
            updateQualityFor(item);
        }
        LOGGER.debug("Finished to update quality for items list");
    }

    private void updateQualityFor(Item item) {
        LOGGER.debug("Updating quality for :{}", item);
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(item);
        qualityControl.updateQualityFor(item);
        LOGGER.debug("Updated quality for: {}", item);
    }

    private void updateSellInFor(Item item) {
        LOGGER.debug("Updating sellin for: {}", item);
        sellInControlService.updateSellInFor(item);
        LOGGER.debug("Updated sellin for: {}", item);
    }
}