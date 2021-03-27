package com.gildedrose;

import java.util.function.Function;
import java.util.function.Predicate;

public class ItemModifer {
    private final Predicate<String> namePredicate;
    private final Predicate<Integer> sellInPredicate;
    private final Function<Integer, Integer> sellInModifier;
    private final Function<Integer, Integer> qualityModifier;
    private final Item item;

    public ItemModifer(
        Predicate<String> namePredicate,
        Predicate<Integer> sellInPredicate,
        Function<Integer, Integer> sellInModifier,
        Function<Integer, Integer> qualityModifier,
        Item item
    ) {
        this.namePredicate = namePredicate;
        this.sellInPredicate = sellInPredicate;
        this.sellInModifier = sellInModifier;
        this.qualityModifier = qualityModifier;
        this.item = item;
    }

    public boolean ruleMatches() {
        return namePredicate.test(item.name) && sellInPredicate.test(item.sellIn);
    }

    public Item modify() {
        int maxQuality = 50;
        int minQuality = 0;
        return new Item(
            item.name,
            sellInModifier.apply(item.sellIn),
            Math.max(minQuality, Math.min(qualityModifier.apply(item.quality), maxQuality))
        );
    }
}
