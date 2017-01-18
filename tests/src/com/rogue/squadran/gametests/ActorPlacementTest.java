package com.rogue.squadran.gametests;

import com.rogue.squadran.game.ActorEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static org.junit.Assert.assertTrue;

/**
 * Unit test:
 * Test that player actor is placed in center (4,4) of 9x9 tile map and that enemy is placed at point
 * (5,7) corresponding to tilemap[7][5] in array.
 */

@RunWith(GdxTestRunner.class)
public class ActorPlacementTest {

    @Test
    public void placeActors() {
        int mapWidth = 9;
        int mapHeight = 9;

        ActorEntity testPlayerMap = new ActorEntity(mapWidth, mapHeight);
        ActorEntity testEnemyMap = new ActorEntity(mapWidth, mapHeight);

        testPlayerMap.tilemap.addId(1, testPlayerMap.tilemap.getSizeX() / 2, testPlayerMap.tilemap.getSizeY() / 2);
        testEnemyMap.tilemap.addId(1, 5, 7);

        assertTrue(testPlayerMap.tilemap.getId(4, 4) == 1 && testEnemyMap.tilemap.getId(5, 7) == 1);

    }
}