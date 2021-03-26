package com.gildedrose;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

class RegularItemUpdateTest {

    @Test
    void updateRegularItem() {
        GildedRose app = new GildedRose(ItemFixture.regular().sellIn(1).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.regular().sellIn(0).quality(0).build()));
    }

    @Test
    void updateExpiredRegularItem() {
        GildedRose app = new GildedRose(ItemFixture.regular().sellIn(0).quality(2).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.regular().sellIn(-1).quality(0).build()));
    }

    @Test
    void updateUselessRegularItem() {
        GildedRose app = new GildedRose(ItemFixture.regular().sellIn(1).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.regular().sellIn(0).quality(0).build()));
    }

    @Test
    void updateExpiredLoValueRegularItem() {
        GildedRose app = new GildedRose(ItemFixture.regular().sellIn(0).quality(1).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.regular().sellIn(-1).quality(0).build()));
    }

    @Test
    void updateExpiredUselessRegularItem() {
        GildedRose app = new GildedRose(ItemFixture.regular().sellIn(0).quality(0).build());
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(ItemFixture.regular().sellIn(-1).quality(0).build()));
    }
}
