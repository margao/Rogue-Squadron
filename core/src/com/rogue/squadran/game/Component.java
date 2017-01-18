package com.rogue.squadran.game;

// A Component encapsulates related data that
// describes a cohesive part of an Entity's state.
public abstract class Component {

    Entity attachedEntity;

    void update(float dt) {}

}
