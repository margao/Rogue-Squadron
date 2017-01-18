package com.rogue.squadran.game;

/**
 * Class for Shields, Swords, Wands, Bows, Armor, and anything else equippable.
 */
class Equipment {

    double[] stats = new double[StatComponent.NUMB_UNIQUE_STATS];
    int itemSlot = -1;
    boolean equipped = false;

    Equipment(double[] stats) {
        // StatType.BASIC_SWORD or StatType.BASIC_BOW or StatType.BASIC_WAND ........
        this.stats = stats;
    }
}
