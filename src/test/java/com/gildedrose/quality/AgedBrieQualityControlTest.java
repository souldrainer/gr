package com.gildedrose.quality;

import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static com.gildedrose.quality.QualityControlFactory.AGED_BRIE_ITEM_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AgedBrieQualityControlTest extends CalculationTest {

    private AgedBrieQualityControl agedBrieQualityControl;
    private Item agedBrie;

    @Before
    public void initialise() {
        agedBrieQualityControl = new AgedBrieQualityControl();
        agedBrie = Item.builder().name(AGED_BRIE_ITEM_NAME).build();
    }

    @Test
    public void testOrdinaryAgedBrieQualityIncrease() {
        agedBrie.setQuality(TEN_DAYS);
        agedBrieQualityControl.updateQualityFor(agedBrie);
        assertThat(agedBrie.getQuality(), is(11));
    }

    @Test
    public void testAgedBrieMaxQuality() {
        agedBrie.setQuality(FIFTY_DAYS);
        agedBrieQualityControl.updateQualityFor(agedBrie);
        assertThat(agedBrie.getQuality(), is(50));
    }

    @Test
    public void testQualityControlFactory() {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(agedBrie);
        assertThat(qualityControl, is(instanceOf(AgedBrieQualityControl.class)));
    }
}