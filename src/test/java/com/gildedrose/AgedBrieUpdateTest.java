package com.gildedrose;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

class AgedBrieUpdateTest {

    @Test
    void updateGoodAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(1).quality(48).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(0).quality(49).build()));
    }

    @Test
    void updateVeryGoodAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(1).quality(49).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(0).quality(50).build()));
    }

    @Test
    void updateTopQualityAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(1).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(0).quality(50).build()));
    }

    @Test
    void updateExpiredAlmostGoodAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(0).quality(47).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(-1).quality(49).build()));
    }

    @Test
    void updateExpiredGoodAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(0).quality(48).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(-1).quality(50).build()));
    }

    @Test
    void updateExpiredVeryGoodAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(0).quality(49).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(-1).quality(50).build()));
    }

    @Test
    void updateExpiredTopQualityAgedBrie() {
        GildedRose app = new GildedRose(ItemFixture.brie().sellIn(0).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.brie().sellIn(-1).quality(50).build()));
    }
}
