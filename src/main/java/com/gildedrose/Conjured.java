package com.gildedrose;

public class Conjured extends Item {
    public Conjured(String name, int quality, int sellIn)
    {
        super("Conjured "+ name, quality, sellIn);
    }

    @Override
    public void updateQuality(){
    if (quality > 0) {
        quality-=2;
      }
      sellIn--;
    }
}
    

