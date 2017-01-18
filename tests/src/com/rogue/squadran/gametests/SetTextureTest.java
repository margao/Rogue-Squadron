package com.rogue.squadran.gametests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.rogue.squadran.game.*;

import de.tomgrill.gdxtesting.GdxTestRunner;

/**
 * Functional test:
 * Test that Graphic Component was successfully attached to Actor Entity and then checks
 * that the test texture "badlogic.jpg" was appropriately attached to the Actor Entity.
 */

@RunWith(GdxTestRunner.class)
public class SetTextureTest {


    @Test
    public void setTexture() {

        int testID = 999; //Default ID for badlogic.jpg, our test image.

        //Create new actor
        ActorEntity actor = new ActorEntity(0,0);

        boolean checkComponent = actor.getComponent(GraphicComponent.class).getClass().equals(GraphicComponent.class);
        assertTrue(checkComponent); //Check that Graphic Component is attached to actor entity

        actor.img.setTestTexture();
        assertTrue("This test asserts true if badlogic.jpg is set as texture for actor object.", actor.img.getTextureId() == testID);

    }

}
