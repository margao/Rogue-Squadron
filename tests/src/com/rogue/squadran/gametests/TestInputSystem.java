package com.rogue.squadran.gametests;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import com.rogue.squadran.game.InputSystem;

import de.tomgrill.gdxtesting.GdxTestRunner;

/**
 * Unit tests:
 * Test that the input system retrieves values.
 * Tests for multi and single input methods.
 */

@RunWith(GdxTestRunner.class)
public class TestInputSystem {
    @Test
    public void testMultiInput() {
        assertTrue(InputSystem.getTouches().size() > 0);
    }

    @Test
    public void testSingleInput(){
        assertTrue(InputSystem.getTouchByIndex(0).getX() > -1 && InputSystem.getTouchByIndex(0).getY() > -1);
    }
}