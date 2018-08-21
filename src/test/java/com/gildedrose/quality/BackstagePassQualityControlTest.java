package com.gildedrose.quality;

import com.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

import static com.gildedrose.quality.QualityControlFactory.BACKSTAGE_PASS_ITEM_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BackstagePassQualityControlTest extends CalculationTest {

    private BackstagePassQualityControl backstagePassQualityControl;
    private Item backstagePass;

    @Before
    public void initialise() {
        backstagePassQualityControl = new BackstagePassQualityControl();
        backstagePass = Item.builder().name(BACKSTAGE_PASS_ITEM_NAME).build();
    }

    @Test
    public void testOrdinaryConcertQualityIncrease() {
        backstagePass.setSellIn(TWENTY_DAYS);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(11));
    }

    @Test
    public void testTenDaysBeforeConcertIncrease() {
        backstagePass.setSellIn(TEN_DAYS);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(11));
    }

    @Test
    public void testFiveDaysBeforeConcertIncrease() {
        backstagePass.setSellIn(FIVE_DAYS);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(12));
    }

    @Test
    public void testAfterConcertQuality() {
        backstagePass.setSellIn(LESS_THEN_ZERO_DAYS);
        backstagePass.setQuality(10);
        backstagePassQualityControl.updateQualityFor(backstagePass);
        assertThat(backstagePass.getQuality(), is(0));
    }

    @Test
    public void testMaxConcertQuality() {
        backstagePass.setSellIn(FIVE_DAYS);
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