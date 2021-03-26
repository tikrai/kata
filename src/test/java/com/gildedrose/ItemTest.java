package com.gildedrose;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.gildedrose.fixtures.ItemFixture;
import org.junit.jupiter.api.Test;

class ItemTest {
  @Test
  void printItem() {
    String item = ItemFixture.regular().sellIn(1).quality(1).build().toString();
    assertThat(item, equalTo("regular item, 1, 1"));
  }
}
