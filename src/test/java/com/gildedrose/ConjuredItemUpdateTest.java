package com.gildedrose;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

class ConjuredItemUpdateTest {

    @Test
    void updateConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(1).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(0).quality(0).build()));
    }

    @Test
    void updateHigherQualityConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(1).quality(2).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(0).quality(0).build()));
    }

    @Test
    void updateTopQualityConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(1).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(0).quality(48).build()));
    }

    @Test
    void updateExpiredLoQualityConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(0).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(-1).quality(0).build()));
    }

    @Test
    void updateExpiredHiQualityConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(0).quality(4).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(-1).quality(0).build()));
    }

    @Test
    void updateExpiredTopQualityConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(0).quality(50).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(-1).quality(46).build()));
    }

    @Test
    void updateUselessConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(1).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(0).quality(0).build()));
    }

    @Test
    void updateExpiredUselessConjuredItem() {
        GildedRose app = new GildedRose(ItemFixture.conjured().sellIn(0).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.conjured().sellIn(-1).quality(0).build()));
    }
}
