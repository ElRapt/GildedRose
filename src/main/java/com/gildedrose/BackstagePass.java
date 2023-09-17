package com.gildedrose;

public class BackstagePass extends Item{

    public BackstagePass(String concert, int quality, int sellIn)
    {
        super("Backstage pass to a " + concert + " concert", quality, sellIn);
    }
    
    @Override
    public void updateQuality() {
        sellIn--;
    
        if (sellIn >= 10) {
            quality = Math.min(50, quality + 1);
        } else if (sellIn >= 5) {
            quality = Math.min(50, quality + 2);
        } else if (sellIn >= 0) {
            quality = Math.min(50, quality + 3);
        } else {
            quality = 0;
        }
    }
    
}

