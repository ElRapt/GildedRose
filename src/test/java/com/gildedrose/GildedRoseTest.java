package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    String name = element.name;
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(name, element.name, "the name changed");
  }

  @Test
  @DisplayName("Test that quality isn't negative and doesn't exceed 50 (and Sulfuras hasn't changed)")
  void testQuality() {
    Item[] items = new Item[]{
      new Item("Aged Brie", 2, 5),
      new Item("Backstage passes", 15, 20),
      new Item("Conjured", 3, 6),
      new Item("Normal Item", 3, 6),
      new Item("Sulfuras, Hand of Ragnaros", 0, 80)
    };

    GildedRose app = new GildedRose(items);
    for (int day = 0; day < 100; day++) {  // Simulate 100 days
      app.updateQuality();

      for (Item item : items) {
          String failMessage = "Failed on item: " + item.name; // String to detect which item failed
          
          assertTrue(item.quality >= 0, failMessage + " (Quality is below zero)");
          if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
              assertTrue(item.quality <= 50, failMessage + " (Quality is above 50)");
          } else {
              assertTrue(item.quality == 80, failMessage + " (Quality of Sulfuras has changed)");
          }
      }
  }
}


  @Test
  @DisplayName("Test that sellIn changed correctly")
  void testSellIn() {
    Item[] items = new Item[]{
      new Item("Aged Brie", 2, 5),
      new Item("Backstage passes", 15, 20),
      new Item("Conjured", 3, 6),
      new Item("Normal Item", 3, 6),
      new Item("Sulfuras, Hand of Ragnaros", 0, 80)
    };

    GildedRose app = new GildedRose(items);
    for (int day = 0; day < 100; day++) {  // Simulate 100 days
              int[] initialSellIns = new int[items.length];

              // Store initial sellIn values for all items
              for (int i = 0; i < items.length; i++) {
                  initialSellIns[i] = items[i].sellIn;
              }

              // Update all items
              app.updateQuality();

              // Check if sellIn has changed appropriately for each item
              for (int i = 0; i < items.length; i++) {
                  Item item = items[i];
                  String failMessage = "Failed on item: " + item.name;

                  if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
                      assertTrue(item.sellIn < initialSellIns[i], failMessage + " (SellIn hasn't changed)");
                  } else {
                      assertTrue(item.sellIn == 0, failMessage + " (SellIn of Sulfuras has changed)");
                  }
              }
          }
      }
  }

