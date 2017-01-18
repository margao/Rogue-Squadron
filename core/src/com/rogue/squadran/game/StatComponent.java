package com.rogue.squadran.game;

/**
 * Holds the stats for a particular attached ActorEntity.
 * This will allow for easy transference of stat components between actors.
 */

public class StatComponent extends Component {
    static final int STR = 0;
    static final int AGILITY = 1;
    static final int INT = 2;
    static final int HP = 3;
    static final int MP = 4;
    static final int DEF = 5;
    static final int NUMB_UNIQUE_STATS = 6;
    private double[] stats = new double[NUMB_UNIQUE_STATS];

    //Use a defined base stat from BaseStats.
    StatComponent(double[] stats) {
        this.stats = stats;
    }

    public double getStat(int statType) {
        return stats[statType];
    }

    void updateStat(int statType, double newAmount) {
        stats[statType] = newAmount;
    }

    void increaseStat(int statType, double newAmount) {
        stats[statType] += newAmount;
    }

    void updateAllStats(double[] newAmount) {
        stats = newAmount;
    }

    public void increaseAllStats(double[] newAmount) {
        for (int i = 0; i < stats.length; i++) {
            stats[i] += newAmount[i];
        }
    }

    void decreaseAllStats(double[] newAmount) {
        for (int i = 0; i < stats.length; i++) {
            stats[i] -= newAmount[i];
        }
    }
}
