package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.matchers.ItemMatcher.matches;
import static org.hamcrest.MatcherAssert.assertThat;

class GildedRoseTest {

    @Test
    void updateRegularItem() {
        Item item = new Item("foo", 10, 10);
        GildedRose app = new GildedRose(item);
        Item[] updatedItems = app.updateQuality();
        assertThat(updatedItems[0], matches(new Item("foo", 9, 9)));
    }

}
