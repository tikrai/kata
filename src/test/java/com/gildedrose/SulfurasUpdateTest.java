package com.gildedrose;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

class SulfurasUpdateTest {

    @Test
    void updateSulfuras() {
        GildedRose app = new GildedRose(ItemFixture.sulfuras().sellIn(1).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.sulfuras().sellIn(1).quality(1).build()));
    }

    @Test
    void updateExpiredSulfuras() {
        GildedRose app = new GildedRose(ItemFixture.sulfuras().sellIn(0).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.sulfuras().sellIn(0).quality(1).build()));
    }

    @Test
    void updateUselessSulfuras() {
        GildedRose app = new GildedRose(ItemFixture.sulfuras().sellIn(1).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.sulfuras().sellIn(1).quality(0).build()));
    }

    @Test
    void updateExpiredUselessSulfuras() {
        GildedRose app = new GildedRose(ItemFixture.sulfuras().sellIn(0).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.sulfuras().sellIn(0).quality(0).build()));
    }
}
