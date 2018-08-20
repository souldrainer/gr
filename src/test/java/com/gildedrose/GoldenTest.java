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
        String gold = new String(Files.readAllBytes(Paths.get("src/test/resources/gold.txt")));

        String current = buildGold().toString();

        Assert.assertEquals(gold.trim(), current.trim());
    }

    private static StringBuilder buildGold() {
        List<Item> items = Arrays.asList(
                Item.builder().name("+5 Dexterity Vest").sellIn(10).quality(20).build(),
                Item.builder().name("Aged Brie").sellIn(2).quality(0).build(),
                Item.builder().name("Elixir of the Mongoose").sellIn(5).quality(7).build(),
                Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(0).quality(80).build(),
                Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(-1).quality(80).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(20).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(49).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(49).build(),
                Item.builder().name("Conjured Mana Cake").sellIn(3).quality(6).build()
        );

        StringBuilder builder = new StringBuilder();
        SellInControl sellInControl = new SellInControl();
        QualityControlFactory qualityControlFactory = new QualityControlFactory();
        GildedRose app = new GildedRose(qualityControlFactory, sellInControl);
        for (int i = 0; i < 32; i++) {
            builder.append(format("------------------------- day %s --------------------------%n", i));
            builder.append(format("%42s %6s %s%n", "NAME", "SELLIN", "QUALITY"));
            for (Item item : items) {
                builder.append(format("%42s %6s %s%n", item.getName(), item.getSellIn(), item.getQuality()));
            }
            builder.append(format("%n"));
            app.updateQuality(items);
        }

        return builder;
    }
}
