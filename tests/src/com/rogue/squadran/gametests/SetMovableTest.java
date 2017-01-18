package com.rogue.squadran.gametests;

import com.rogue.squadran.game.ActorEntity;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SetMovableTest {

    @Test
    public void checkMovable() {
        //Create test actor
        ActorEntity actor = new ActorEntity(0,0);
        actor.moving = !actor.moving;

        assertTrue("This test will only pass if actor is set to movable.", actor.moving);
    }

}
