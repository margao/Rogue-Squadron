package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

/**
 * Final Class to hold base stats for classes. Use BaseStats.CLASS_BASE_STATS to receive array.
 */

final class BaseStats {

    ///STATS FORMAT : {STR, AGL, INT, HP, MP, DEF}
    //Base stats for character types.
    static double[] WARRIOR_BASE_STATS = {5, 2, 1, 100, 10, 10};
    static double[] MAGE_BASE_STATS = {1, 2, 8, 50, 100, 15};
    static double[] ARCHER_BASE_STATS = {3, 8, 2, 25, 25, 5};
    static double[] BERSERKER_BASE_STATS = {10, 1, 1, 100, 0, 5};

    //Base stats for basic weapons.
    static double[] BASIC_SWORD_STATS = {5, 0, 0, 0, 0, 0};
    static double[] BASIC_SHIELD_STATS = {0, 0, 0, 25, 0, 5};
    static double[] BASIC_BOW_STATS = {2, 2, 0, 0, 0, 0};
    static double[] BASIC_WAND_STATS = {0, 0, 2, 10, 10, 0};

    static Equipment BASIC_SWORD = new Equipment(BaseStats.BASIC_SWORD_STATS);
    static Equipment BASIC_WAND = new Equipment(BaseStats.BASIC_WAND_STATS);
    static Equipment BASIC_BOW = new Equipment(BaseStats.BASIC_BOW_STATS);

    private BaseStats(){}

}
