package com.gildedrose;

public class Conjured extends Item {
    public Conjured(String name, int quality, int sellIn)
    {
        super("Conjured "+ name, quality, sellIn);
    }

    @Override
    public void updateQuality(){
        // Math.max ensures quality can never go below 0 even with large updates
    if (sellIn >= 0) {
        quality = Math.max(quality-2, 0);
      }
    
    else if (sellIn < 0 )
    {
        quality = Math.max(quality-4, 0);
    } 
      sellIn--;
}}

    

