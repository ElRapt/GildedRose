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
    assertEquals("foo", element.name, "the name changed");
  }

  @Test
  @DisplayName("Test that quality isn't negative and doesn't exceed 50 except Sulfuras")
  void testQuality() {
    Item[] items = generateTestItems();
    GildedRose app = new GildedRose(items);
    simulateDays(app, 100);

    for (Item item : items) {
      String failMessage = "Failed on item: " + item.name;
      assertTrue(item.quality >= 0, failMessage + " (Quality is below zero)");
      if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
        assertTrue(item.quality <= 50, failMessage + " (Quality is above 50)");
      } else {
        assertTrue(item.quality == 80, failMessage + " (Quality of Sulfuras has changed)");
      }
    }
  }

  @Test
  @DisplayName("Test that sellIn changed correctly")
  void testSellIn() {
    Item[] items = generateTestItems();
    GildedRose app = new GildedRose(items);
    simulateDays(app, 100);

    for (Item item : items) {
      String failMessage = "Failed on item: " + item.name;

      if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
        assertTrue(item.sellIn <= 0, failMessage + " (SellIn hasn't decreased)");
      } else {
        assertTrue(item.sellIn == 0, failMessage + " (SellIn of Sulfuras has changed)");
      }
    }
  }

  @Test
  @DisplayName("Test that Aged Brie increases in quality")
  void testAgedBrieQuality() {
    Item agedBrie = new Item("Aged Brie", 2, 5);
    GildedRose app = new GildedRose(new Item[]{agedBrie});
    simulateDays(app, 100);
    String failMessage = "Failed on item: " + agedBrie.name;
    assertTrue(agedBrie.quality > 5, failMessage + " (Quality hasn't increased)");
  }

  @Test
  @DisplayName("Test that Conjured items degrade twice as fast")
  void testConjuredQuality() {
    Item conjured = new Item("Conjured", 3, 6);
    GildedRose app = new GildedRose(new Item[]{conjured});
    simulateDays(app, 1);
    String failMessage = "Failed on item: " + conjured.name;
    assertEquals(4, conjured.quality, failMessage + " (Quality didn't degrade twice as fast)");
  }

  private Item[] generateTestItems() {
    return new Item[]{
      new Item("Aged Brie", 2, 5),
      new Item("Backstage passes", 15, 20),
      new Item("Conjured", 3, 6),
      new Item("Normal Item", 3, 6),
      new Item("Sulfuras, Hand of Ragnaros", 0, 80)
    };
  }

  private void simulateDays(GildedRose app, int days) {
    for (int i = 0; i < days; i++) {
      app.updateQuality();
    }
  }
}

