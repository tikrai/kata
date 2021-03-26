package com.gildedrose;

import java.util.function.Function;
import java.util.function.Predicate;

public class ItemMatcher {
  private final Predicate<String> namePredicate;
  private final Predicate<Integer> sellInPredicate;
  private final Function<Integer, Integer> sellInModifier;
  private final Function<Integer, Integer> qualityModifier;
  private final Item item;

  private ItemMatcher(
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

  public static Builder builder(Item item) {
    return new Builder(item);
  }

  public boolean matches() {
    return namePredicate.test(item.name)
        && sellInPredicate.test(item.sellIn);
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

  public static class Builder {
    private Predicate<String> name = x -> true;
    private Predicate<Integer> sellIn = x -> true;
    private Function<Integer, Integer> sellInModifier = x -> x - 1;
    private Function<Integer, Integer> qualityModifier = x -> x - 1;

    private final Item item;

    private Builder(Item item) {
      this.item = item;
    }

    public Builder namePredicate(Predicate<String> name) {
      this.name = name;
      return this;
    }

    public Builder sellInPredicate(Predicate<Integer> sellIn) {
      this.sellIn = sellIn;
      return this;
    }

    public Builder sellInModifier(Function<Integer, Integer> sellInModifier) {
      this.sellInModifier = sellInModifier;
      return this;
    }

    public Builder qualityModifier(Function<Integer, Integer> qualityModifier) {
      this.qualityModifier = qualityModifier;
      return this;
    }

    public ItemMatcher build() {
      return new ItemMatcher(name, sellIn, sellInModifier, qualityModifier, item);
    }
  }
}
