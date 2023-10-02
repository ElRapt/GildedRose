package com.gildedrose;

public class BackstagePass extends Item{

    public BackstagePass(String concert, int sellIn, int quality)
    {
        super("Backstage pass to a " + concert + " concert", quality, sellIn);
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("Quality should be between 0 and 50");
        }
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

