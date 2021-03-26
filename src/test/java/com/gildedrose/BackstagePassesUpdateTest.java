package com.gildedrose;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

class BackstagePassesUpdateTest {

    @Test
    void updateColdBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(11).quality(49).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(10).quality(50).build()));
    }

    @Test
    void updateColdTopQualityBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(11).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(10).quality(50).build()));
    }

    @Test
    void updateMinWarmBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(10).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(9).quality(2).build()));
    }

    @Test
    void updateMaxWarmBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(6).quality(48).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(5).quality(50).build()));
    }

    @Test
    void updateWarmQ49BackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(6).quality(49).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(5).quality(50).build()));
    }

    @Test
    void updateWarmQ50BackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(6).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(5).quality(50).build()));
    }

    @Test
    void updateMinHotBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(5).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(4).quality(3).build()));
    }

    @Test
    void updateMaxHotBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(1).quality(47).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(0).quality(50).build()));
    }

    @Test
    void updateHotQ48BackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(1).quality(48).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(0).quality(50).build()));
    }

    @Test
    void updateHotQ50BackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(1).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(0).quality(50).build()));
    }

    @Test
    void updateExpiredBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(0).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(-1).quality(0).build()));
    }

    @Test
    void updateExpiredUselessBackstagePasses() {
        GildedRose app = new GildedRose(ItemFixture.passes().sellIn(0).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.passes().sellIn(-1).quality(0).build()));
    }
}
