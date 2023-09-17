package com.gildedrose;

public abstract class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public abstract void updateQuality();
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", " + this.sellIn + ", " + this.quality;
    }
}
