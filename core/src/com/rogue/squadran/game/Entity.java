package com.rogue.squadran.game;

import java.util.ArrayList;

// An Entity represents an object in the game world.
// The state of an Entity is described by the various
// Components attached to it. An Entity can have
// many Components, but can only have up to one
// instance of any Component subclass.

class Entity {

    // Just using a simple ArrayList here to store the Components.
    ArrayList<Component> components;
    
    // Default constructor
    Entity() {
        //instantiate list with init. capacity of 5; an arbitrary value
        components = new ArrayList<Component>(5);
    }
    
    // Calling this method returns an array containing all
    // of the attached Components.
    public Component[] getComponents() {

        Component[] comps = new Component[components.size()];
        return components.toArray(comps);
    }
    
    // Calling this method will return the instance of the given
    // Component subclass from the list. Returns null if such an instance
    // does not exist.
    //
    // Ex:
    //     Component comp = entity.getComponent(MyComponentSubclass.class);
    //
    public Component getComponent(Class<? extends Component> compClass) {

        for (Component c : components) {
            if (compClass.equals(c.getClass())) {
                return c; // check if c is an instance of compClass
            }
        }

        
        return null;
    }
    
    // Calling this method will add the given Component to the list iff
    // there is not already a Component of the same class in it. Returns
    // false if this condition is not met, otherwise returns the result
    // from calling the 'add()' method on the list.
    boolean attachComponent(Component comp) {

        // The Class class (that sounds a bit weird) allows us to
        // check if a we should attach the given Component or not,
        // i.e. whether attaching would break the condition of up
        // to one instance per subclass.
        Class compClass = comp.getClass();

        comp.attachedEntity = this;
        
        for (Component c : components) {
            if (compClass.equals(c.getClass())) { //check if comp and c are of the same class
                return false;
            }
        }

        
        return components.add(comp);
    }
    
    // Calling this method will remove and return the instance of the given
    // Component subclass from the list. Returns null if such an instance
    // does not exist.
    //
    // Ex:
    //     Component comp = entity.detach(MyComponentSubclass.class);
    //
    public Component detachComponent(Class<? extends Component> compClass) {
        for (int i = 0; i < components.size(); i++) {
            if (compClass.equals(components.get(i).getClass())) {
                return components.remove(i);
            }
        }
        
        return null;
    }
    
    public void update(float dt) {
        for (Component c : components) {
            c.update(dt);
        }
    }
}
