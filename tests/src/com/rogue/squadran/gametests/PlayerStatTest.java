package com.rogue.squadran.gametests;

import com.rogue.squadran.game.ActorEntity;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerStatTest {

    @Test
    public void addToAllStats() {

        //Create test actor with all stats = 0
        ActorEntity actor = new ActorEntity(0,0);

        //Increase all stats by 1
        double[] stats = new double[] {1,1,1,1,1,1};
        actor.stats.increaseAllStats(stats);

        //Iterate through actor's stats and make sure it = 1.
        boolean checkValue = true;
        for (int i = 0; i < 6; i++){
            if (actor.stats.getStat(i) != 1){
                checkValue = false;
            }
        }

        assertTrue("This will return true if every element of stats array = 1.", checkValue);

    }

}
