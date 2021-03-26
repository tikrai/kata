package com.gildedrose.fixtures;

import com.gildedrose.Item;

public class ItemFixture {

  private String name;
  private int sellIn;
  private int quality;

  public static ItemFixture regular() {
    return new ItemFixture().name("regular item");
  }

  public static ItemFixture brie() {
    return new ItemFixture().name("Aged Brie");
  }

  public static ItemFixture sulfuras() {
    return new ItemFixture().name("Sulfuras, Hand of Ragnaros");
  }

  public ItemFixture name(String name) {
    this.name = name;
    return this;
  }

  public ItemFixture sellIn(int sellIn) {
    this.sellIn = sellIn;
    return this;
  }

  public ItemFixture quality(int quality) {
    this.quality = quality;
    return this;
  }

  public Item build() {
    return new Item(name, sellIn, quality);
  }
}