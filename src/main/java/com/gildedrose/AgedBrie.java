package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality)
    {
        super("Aged Brie", sellIn, quality);
        checkQualityRange();
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
