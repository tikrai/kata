package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {

    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public Item[] updateQuality() {
        Item item = items[0];

        ItemMatcher sulfuras = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Sulfuras, Hand of Ragnaros"))
            .qualityModifier(integer -> integer)
            .sellInModifier(integer -> integer)
            .build();
        ItemMatcher backStage = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Backstage passes to a TAFKAL80ETC concert"))
            .sellInPredicate(integer -> integer <= 0)
            .qualityModifier(integer -> 0)
            .build();
        ItemMatcher backStage2 = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Backstage passes to a TAFKAL80ETC concert"))
            .sellInPredicate(integer -> integer <= 5)
            .qualityModifier(integer -> integer + 3)
            .build();
        ItemMatcher backStage3 = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Backstage passes to a TAFKAL80ETC concert"))
            .sellInPredicate(integer -> integer <= 10)
            .qualityModifier(integer -> integer + 2)
            .build();
        ItemMatcher backStage4 = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Backstage passes to a TAFKAL80ETC concert"))
            .qualityModifier(integer -> integer + 1)
            .build();
        ItemMatcher aged_brie = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Aged Brie"))
            .sellInPredicate(integer -> integer < 1)
            .qualityModifier(integer -> integer + 2)
            .build();
        ItemMatcher aged_brie2 = ItemMatcher.builder(item)
            .namePredicate(s -> s.equals("Aged Brie"))
            .qualityModifier(integer -> integer + 1)
            .build();
        ItemMatcher regular_exp = ItemMatcher.builder(item)
            .sellInPredicate(integer -> integer < 1)
            .qualityModifier(integer -> integer - 2)
            .build();
        ItemMatcher regular = ItemMatcher.builder(item).build();

        List<ItemMatcher> itemMatchers = Arrays.asList(
            sulfuras, backStage, backStage2, backStage3, backStage4, aged_brie, aged_brie2, regular_exp, regular
        );

        Item item1 = itemMatchers.stream()
            .filter(ItemMatcher::matches)
            .map(ItemMatcher::modify)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No matcher applies to item"));

        items[0] = item1;

        return items;
    }
}