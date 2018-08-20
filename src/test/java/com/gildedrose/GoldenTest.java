package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.quality.QualityControlFactory;
import com.gildedrose.sell.SellInControl;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class GoldenTest {
    @Test
    public void checkAllIsGold() throws IOException {
        String gold = new String(Files.readAllBytes(Paths.get("src/test/java/resources/gold.txt")));

        String current = buildGold().toString();

        Assert.assertEquals(gold.trim(), current.trim());
    }

    private static StringBuilder buildGold() {
        List<Item> items = Arrays.asList(
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        );

        StringBuilder builder = new StringBuilder();
        SellInControl sellInControl = new SellInControl();
        QualityControlFactory qualityControlFactory = new QualityControlFactory();
        GildedRose app = new GildedRose(qualityControlFactory, sellInControl);
        for (int i = 0; i < 32; i++) {
            builder.append(format("------------------------- day %s --------------------------%n", i));
            builder.append(format("%42s %6s %s%n", "NAME", "SELLIN", "QUALITY"));
            for (Item item : items) {
                builder.append(format("%42s %6s %s%n", item.name, item.sellIn, item.quality));
            }
            builder.append(format("%n"));
            app.updateQuality(items);
        }

        return builder;
    }
}
