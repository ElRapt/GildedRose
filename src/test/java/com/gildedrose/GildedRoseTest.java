package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals("FIXME", element.name, "the name changed");
  }

  @Test
  @DisplayName("Test that quality isn't negative and doesn't exceed 50 (and Sulfuras hasn't changed)")
  void testQuality() {
    Item[] items = new Item[]{
      new Item("Aged Brie", 2, 0),
      new Item("Backstage passes", 15, 20),
      new Item("Conjured", 3, 6),
      new Item("Normal Item", 3, 6),
      new Item("Sulfuras", 0, 80)
    }

    GildedRose app = new GildedRose(items);
    app.updateQuality();
    for (int day = 0; day < 100 ; day++) //Test for 100 days
    {
      for(Item item : items) 
      {
        if (item.name != "Sulfuras, Hand of Ragnaros")
        {
          assertTrue("Quality is negative", item.quality >= 0);
          assertTrue("Quality is over 50", item.quality <= 50);
        }
        else {
          assertTrue("Sulfuras quality has changed", item.quality == 80);
        }
      }
    }
  }

}
