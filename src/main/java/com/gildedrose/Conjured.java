package com.gildedrose;

public class Conjured extends Item {
    public Conjured(String name, int sellIn, int quality)
    {
        super("Conjured "+ name, sellIn, quality);
        checkQualityRange();
    }

    @Override
    public void updateQuality(){
        // Math.max ensures quality can never go below 0 even with large updates
    if (sellIn >= 0) {
        quality = Math.max(quality-2, 0);
      }
    
    else
    {
        quality = Math.max(quality-4, 0);
    } 
      sellIn--;
}}

    

