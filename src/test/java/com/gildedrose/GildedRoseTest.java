package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new NormalItem("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.passDay();
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
    Item agedBrie = new AgedBrie(2, 5);
    GildedRose app = new GildedRose(new Item[]{agedBrie});
    simulateDays(app, 100);
    String failMessage = "Failed on item: " + agedBrie.name;
    assertTrue(agedBrie.quality > 5, failMessage + " (Quality hasn't increased)");
  }

  @Test
  @DisplayName("Test that Conjured items behave correctly")
  void testConjuredQuality() {
    Item conjuredCake = new Conjured("Mana cake", 3, 6);
    Item conjuredWater = new Conjured("Fresh water", -1, 4);
    GildedRose app = new GildedRose(new Item[]{conjuredCake, conjuredWater});
    app.passDay();
    assertEquals(4, conjuredCake.quality, " (Quality didn't degrade twice as fast)");
    assertEquals(0, conjuredWater.quality," (Quality didn't degrade four times as fast)");
    
  }


  @Test
  @DisplayName("Test that quality drops twice as fast for normal items after sellIn is negative")
  void testQualityExpired() {
    Item normalItem = new NormalItem("Normal Item", 0, 6);
    GildedRose app = new GildedRose(new Item[]{normalItem});
    app.passDay();
    String failMessage = "Failed on item: " + normalItem.name;
    assertEquals(4, normalItem.quality, failMessage + " (Quality didn't drop twice as fast after sellIn is negative)");
  }

  @Test
  @DisplayName("Test that Backstage passes behave correctly")
  void testBackstagePasses() {
      Item[] items = new Item[]{
        new BackstagePass("TAFKAL80ETC", 10, 49),
        new BackstagePass("TAFKAL80ETC", 4, 48),
        new BackstagePass("TAFKAL80ETC", 10, 11),
        new BackstagePass("TAFKAL80ETC", 10, 10),
        new BackstagePass("TAFKAL80ETC", 10, 5),
        new BackstagePass("TAFKAL80ETC", 10, 0)
        
      };
      GildedRose app = new GildedRose(items);
      app.passDay();

      String failMessage = "Failed on item with quality = 49: " + items[0].name;
      assertEquals(11, items[0].quality, failMessage + " (Quality didn't increase by 1 point)");
  
      failMessage = "Failed on item with quality = 48: " + items[1].name;
      assertEquals(5, items[1].quality, failMessage + " (Quality didn't increase by 1 point)");
      
      failMessage = "Failed on item with sellIn > 10: " + items[2].name;
      assertEquals(11, items[2].quality, failMessage + " (Quality didn't increase by 1 point)");
  

      failMessage = "Failed on item with 5 < sellIn <= 10: " + items[3].name;
      assertEquals(12, items[3].quality, failMessage + " (Quality didn't increase by 2 points)");
  

      failMessage = "Failed on item with sellIn <= 5: " + items[4].name;
      assertEquals(13, items[4].quality, failMessage + " (Quality didn't increase by 3 points)");
  
      failMessage = "Failed on item with sellIn < 0: " + items[5].name;
      assertEquals(0, items[5].quality, failMessage + " (Quality didn't drop to 0)");
  
  }
  

  @Test
  @DisplayName("Test that toString method works as expected")
  void testToStringMethod() {
      Item item = new NormalItem("Normal Item", 3, 6);
      assertEquals("Normal Item, 3, 6", item.toString(), "toString() output mismatch");
  }


  @Test
  @DisplayName("Test that Argument exception are correctly raised")
  void testIllegalArgument() {
      assertThrows(IllegalArgumentException.class, () -> {
          new NormalItem("Free Action Potion", 1, 51);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new NormalItem("Free Action Potion", 1, -1);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new Conjured("Mana cake", 1, 51);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new Conjured("Mana cake", 1, -1);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new BackstagePass("Arctic Monkeys", 1, 51);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new BackstagePass("Arctic Monkeys", 1, -1);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new AgedBrie(1, 51);
      });
      assertThrows(IllegalArgumentException.class, () -> {
          new AgedBrie(1, -1);
      });
  }
  


  private Item[] generateTestItems() {
    return new Item[]{
      new AgedBrie(2, 5),
      new BackstagePass("TAFKAL80ETC", 15, 20),
      new Conjured("Mana cake", 3, 6),
      new NormalItem("Normal Item", 3, 6),
      new Sulfuras()
    };
  }

  private void simulateDays(GildedRose app, int days) {
    for (int i = 0; i < days; i++) {
      app.passDay();
    }
  }
}

