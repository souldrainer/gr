package com.gildedrose.quality;

import com.gildedrose.factory.QualityControlFactory;
import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static com.gildedrose.factory.QualityControlFactory.BACKSTAGE_PASS_ITEM_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BackstagePassQualityControlTest {

    private static final int TWENTY = 20;
    private static final int NINE = 9;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int LESS_THEN_ZERO = -1;
    private static QualityControlFactory qualityControlFactory = new QualityControlFactory();

    private BackstagePassQualityControl backstagePassQualityControl;
    private Item backstagePass;

    @Before
    public void initialise() {
        backstagePassQualityControl = new BackstagePassQualityControl();
        backstagePass = Item.builder().name(BACKSTAGE_PASS_ITEM_NAME).build();
    }

    @Test
    public void testOrdinaryConcertQualityIncrease() {
        backstagePass.setSellIn(TWENTY);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(11));
    }

    @Test
    public void testLessThenTenDaysBeforeConcertIncrease() {
        backstagePass.setSellIn(NINE);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(12));
    }

    @Test
    public void testLessThenFiveDaysBeforeConcertIncrease() {
        backstagePass.setSellIn(FOUR);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(13));
    }

    @Test
    public void testAfterConcertQuality() {
        backstagePass.setSellIn(LESS_THEN_ZERO);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(0));
    }

    @Test
    public void testMaxConcertQuality() {
        backstagePass.setSellIn(FIVE);
        backstagePass.setQuality(50);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(50));
    }

    @Test
    public void testQualityControlFactory() {
        QualityControl qualityControl = qualityControlFactory.qualityControlFor(backstagePass);
        assertThat(qualityControl, is(instanceOf(BackstagePassQualityControl.class)));
    }
}