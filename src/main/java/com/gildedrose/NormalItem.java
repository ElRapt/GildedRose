package com.gildedrose;

public class NormalItem extends Item {
    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        if (quality < 0 || quality > 50) {
          throw new IllegalArgumentException("Quality should be between 0 and 50");
      }
    }

    @Override
    public void updateQuality() {
      if (quality > 0) {
        quality--;
      }
      sellIn--;
      if (sellIn < 0 && quality > 0) {
        quality--;
      }
    }
}
