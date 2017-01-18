package com.rogue.squadran.game;

abstract class Action {

    ActorEntity actor;
    
    void bindToActor(ActorEntity actor) {
        this.actor = actor;
    }
    
    public abstract boolean execute(float dt);
}
