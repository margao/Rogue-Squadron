package com.rogue.squadran.gametests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rogue.squadran.game.*;

/**
 * Functional test:
 * Test that Map Component was successfully attached to World Map Entity and then checks
 * that the world map has been initialized with arbitrary size 10x10 array where each value in the
 * array is set to 0.
 */

public class MapInitializationTest {

    @Test
    public void initializeMap() {

        WorldMapEntity testmap = new WorldMapEntity(10, 10);

        boolean checkComponent = testmap.getComponent(MapComponent.class).getClass().equals(MapComponent.class);

        assertTrue(checkComponent); //Check that Map Component is attached to testmap WorldMap Entity

        boolean checkValue = true;

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                if (testmap.tilemap.tilemapArray[j][i] != 0) {
                    checkValue = false;
                }
            }
        }

        assertTrue("This test will only pass if a 10x10 array is initialized where each value is set to 0.", checkValue);
    }
}
