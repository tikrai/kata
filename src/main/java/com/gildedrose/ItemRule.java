package com.gildedrose;

import java.util.function.Function;
import java.util.function.Predicate;

public class ItemRule {

    private final Predicate<String> name;
    private Predicate<Integer> sellIn = x -> true;
    private Function<Integer, Integer> sellInModifier = x -> x - 1;
    private Function<Integer, Integer> qualityModifier = x -> x - 1;

    public ItemRule(Predicate<String> name) {
        this.name = name;
    }

    public static ItemRule ifNameIsAny() {
        return new ItemRule(x -> true);
    }

    public static ItemRule ifNameIs(String name) {
        return new ItemRule(s -> s.equals(name));
    }

    public ItemRule ifSellIn(Predicate<Integer> sellIn) {
        this.sellIn = sellIn;
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
        return new ItemModifer(name, sellIn, sellInModifier, qualityModifier, item);
    }
}
