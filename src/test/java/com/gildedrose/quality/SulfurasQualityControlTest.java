package com.gildedrose.quality;

import com.gildedrose.factory.QualityControlFactory;
import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static com.gildedrose.factory.QualityControlFactory.SULFURAS_ITEM_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SulfurasQualityControlTest {

    private QualityControlFactory qualityControlFactory = new QualityControlFactory();
    private QualityControl sulfurasQualityControl;

    @Before
    public void initialise() {
        sulfurasQualityControl = new SulfurasQualityControl();
    }

    @Test
    public void legendaryItemTest() {
        Item sulfuras = Item.builder().name(SULFURAS_ITEM_NAME).quality(20).sellIn(10).build();
        sulfurasQualityControl.updateQualityFor(sulfuras);
        assertThat(sulfuras.getQuality(), is(20));
    }

    @Test
    public void testQualityControlFactory() {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(Item.builder().name(SULFURAS_ITEM_NAME).build());
        assertThat(qualityControl, is(instanceOf(SulfurasQualityControl.class)));
    }
}