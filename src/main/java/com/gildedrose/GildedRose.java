package com.gildedrose;

import static com.gildedrose.ItemRule.ifNameIs;
import static com.gildedrose.ItemRule.ifNameIsAny;

import java.util.Arrays;
import java.util.List;

class GildedRose {

    private final static String PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final static String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final static String AGED_BRIE = "Aged Brie";

    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public Item[] updateQuality() {
        List<ItemRule> itemRules = Arrays.asList(
            ifNameIs(SULFURAS).reduceQualityBy(0).reduceSellInBy(0),
            ifNameIs(PASSES).andSellInLessThan(1).updateQuality(value -> 0),
            ifNameIs(PASSES).andSellInLessThan(6).increaseQualityBy(3),
            ifNameIs(PASSES).andSellInLessThan(11).increaseQualityBy(2),
            ifNameIs(PASSES).increaseQualityBy(1),
            ifNameIs(AGED_BRIE).andSellInLessThan(1).increaseQualityBy(2),
            ifNameIs(AGED_BRIE).increaseQualityBy(1),
            ifNameIsAny().andSellInLessThan(1).reduceQualityBy(2));

        return Arrays.stream(items)
            .map(item -> itemRules.stream()
                .map(rule -> rule.getModifierFor(item))
                .filter(ItemModifer::ruleMatches)
                .map(ItemModifer::modify)
                .findFirst()
                .orElseGet(() -> ifNameIsAny().getModifierFor(item).modify()))
            .toArray(Item[]::new);
    }
}
