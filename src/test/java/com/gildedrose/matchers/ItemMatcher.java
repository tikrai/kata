package com.gildedrose.matchers;

import com.gildedrose.Item;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ItemMatcher extends TypeSafeMatcher<Item> {
  private final Item expectedValue;

  public ItemMatcher(Item equalArg) {
    expectedValue = equalArg;
  }

  @Override
  protected boolean matchesSafely(Item item) {
    return item.name.equals(expectedValue.name)
        && item.sellIn == expectedValue.sellIn
        && item.quality == expectedValue.quality;
  }

  @Override
  public void describeTo(Description description) {
    description.appendValue(expectedValue);
  }

  @Factory
  public static Matcher<Item> matches(Item operand) {
    return new ItemMatcher(operand);
  }
}
