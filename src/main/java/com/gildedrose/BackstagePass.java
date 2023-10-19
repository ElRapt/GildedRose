package com.gildedrose;

public class BackstagePass extends Item{

    public BackstagePass(String concert, int sellIn, int quality)
    {
        super("Backstage pass to a " + concert + " concert", quality, sellIn);
        checkQualityRange();
    }
    
    @Override
    public void updateQuality() {
        sellIn--;
        // Math.min ensures that the quality never exceeds 50 but correctly increases
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

