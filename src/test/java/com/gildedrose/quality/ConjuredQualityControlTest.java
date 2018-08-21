package com.gildedrose.quality;

import com.gildedrose.factory.QualityControlFactory;
import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static com.gildedrose.factory.QualityControlFactory.CONJURED_ITEM_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConjuredQualityControlTest {

    static final int TWENTY_DAYS = 20;
    static final int ZERO_DAYS = 0;

    private QualityControlFactory qualityControlFactory = new QualityControlFactory();
    private ConjuredQualityControl conjuredQualityControl;
    private Item conjured;

    @Before
    public void initialise() {
        conjuredQualityControl = new ConjuredQualityControl();
        conjured = Item.builder().name(CONJURED_ITEM_NAME).build();
    }

    @Test
    public void testOrdinaryConjuredItemDecrease() {
        conjured.setQuality(TWENTY_DAYS);
        conjured.setSellIn(10);
        conjuredQualityControl.updateQualityFor(conjured);
        assertThat(conjured.getQuality(), is(18));
    }

    @Test
    public void testMinConjuredQuality() {
        conjured.setQuality(ZERO_DAYS);
        conjured.setSellIn(10);
        conjuredQualityControl.updateQualityFor(conjured);
        assertThat(conjured.getQuality(), is(0));
    }

    @Test
    public void testQualityControlFactory() {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(conjured);
        assertThat(qualityControl, is(instanceOf(ConjuredQualityControl.class)));
    }
}