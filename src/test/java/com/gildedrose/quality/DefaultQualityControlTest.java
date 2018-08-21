package com.gildedrose.quality;

import com.gildedrose.factory.QualityControlFactory;
import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultQualityControlTest {

    static final int TWENTY = 20;
    static final int TEN = 10;
    static final int ZERO = 0;

    private QualityControlFactory qualityControlFactory = new QualityControlFactory();
    private DefaultQualityControl defaultQualityControl;
    private Item unknown;

    @Before
    public void initialise() {
        defaultQualityControl = new DefaultQualityControl();
        unknown = Item.builder().build();
    }

    @Test
    public void testOrdinaryConjuredItemDecrease() {
        unknown.setQuality(TWENTY);
        unknown.setSellIn(TEN);
        defaultQualityControl.updateQualityFor(unknown);
        assertThat(unknown.getQuality(), is(19));
    }

    @Test
    public void testMinConjuredQuality() {
        unknown.setQuality(ZERO);
        unknown.setSellIn(TEN);
        defaultQualityControl.updateQualityFor(unknown);
        assertThat(unknown.getQuality(), is(0));
    }

    @Test
    public void testQualityControlFactory() {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(Item.builder().build());
        assertThat(qualityControl, is(instanceOf(DefaultQualityControl.class)));
    }

}