package com.gildedrose;

import java.util.function.Function;
import java.util.function.Predicate;

public class ItemRule {

    private final Predicate<String> namePredicate;
    private Predicate<Integer> sellInPredicate = x -> true;
    private Function<Integer, Integer> sellInModifier = x -> x - 1;
    private Function<Integer, Integer> qualityModifier = x -> x - 1;

    public ItemRule(Predicate<String> namePredicate) {
        this.namePredicate = namePredicate;
    }

    public static ItemRule ifNameIsAny() {
        return new ItemRule(x -> true);
    }

    public static ItemRule ifNameIs(String name) {
        return new ItemRule(s -> s.equals(name));
    }

    public ItemRule ifSellIn(Predicate<Integer> sellInPredicate) {
        this.sellInPredicate = sellInPredicate;
        return this;
    }

    public ItemRule andSellInLessThan(int value) {
        return ifSellIn(integer -> integer < value);
    }

    public ItemRule updateSellIn(Function<Integer, Integer> sellInModifier) {
        this.sellInModifier = sellInModifier;
        return this;
    }

    public ItemRule reduceSellInBy(int value) {
        return updateSellIn(sellIn -> sellIn - value);
    }

    public ItemRule updateQuality(Function<Integer, Integer> qualityModifier) {
        this.qualityModifier = qualityModifier;
        return this;
    }

    public ItemRule increaseQualityBy(int value) {
        return updateQuality(quality -> quality + value);
    }

    public ItemRule reduceQualityBy(int value) {
        return updateQuality(quality -> quality - value);
    }

    public ItemModifer getModifierFor(Item item) {
        return new ItemModifer(namePredicate, sellInPredicate, sellInModifier, qualityModifier, item);
    }
}
