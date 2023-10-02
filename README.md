# Gilded Rose : TP1

![GitHub](https://img.shields.io/github/license/ElRapt/GildedRose)
![Language](https://img.shields.io/badge/Language-Java-orange)
![Library](https://img.shields.io/badge/Engine-gradle-yellow)
![Size](https://img.shields.io/badge/Size-1MB-yellowgreen)


## Objective

The aim of the project is to refactor the horrible initial code for the GildedRose kata. We will need also to expand the inventory system of the Gilded Rose shop by introducing a new item category called "Conjured" while adhering to existing business rules for other types of items.

## Approach

I chose a polymorphic approach to tackle this problem. Each type of item is represented by a subclass inheriting from the `Item` class. Each subclass implements its own logic for updating quality and `sellIn`.


## Tools Used

- **Build Automation**: Gradle
- **Code Coverage**: Jacoco

## Testing

Unit tests have been implemented to verify that the logic for updating each type of item meets the requirements.
