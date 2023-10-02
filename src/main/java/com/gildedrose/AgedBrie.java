package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality)
    {
        super("Aged Brie", sellIn, quality);
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("Quality should be between 0 and 50");
        }
    }
    
    @Override
    public void updateQuality()
    {
        if (quality < 50)
        {
            quality++;
        }
        sellIn--;
    }
}
