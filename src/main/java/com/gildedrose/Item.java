package com.gildedrose;

public abstract class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        // IllegalArgumentException not thrown here because Sulfuras would break it
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public abstract void updateQuality();

    protected void checkQualityRange() {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("Quality must be between 0 and 50");
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
