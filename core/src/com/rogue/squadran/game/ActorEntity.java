package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

/**
 *  Catch all actor class for player character/enemies
 *  Current components: Map, Graphic, Input, Stat, Inventory
 *
**/

public class ActorEntity extends Entity {
    int textureSize;
    public MapComponent tilemap;
    public GraphicComponent img;
    public StatComponent stats;
    CollisionComponent collision;
    InputComponent input;
    InventoryComponent inventory;
    private int mapHeight;
    private int mapWidth;
    public boolean moving;
    MoveAction moveAction;
    TurnAction turnAction;
    InventoryAction inventoryAction;

    //Player and Enenmy actors
    ActorEntity(int xwidth, int yheight, int textureId, double[] stats, CollisionHandler handler) {
        mapWidth = xwidth;
        mapHeight = yheight;
        img = new GraphicComponent(textureId);
        tilemap = new MapComponent(mapWidth,mapHeight);
        input = new InputComponent(false);
        inventory = new InventoryComponent();
        collision = new CollisionComponent();
        this.stats = new StatComponent(stats);
        this.attachComponent(tilemap); //has map associated with position
        this.attachComponent(img); //has graphical texture associated with ID
        this.attachComponent(input);
        this.attachComponent(this.stats);
        this.attachComponent(inventory);
        this.attachComponent(collision);
        collision.bindHandler(handler);
        moving = false;
        moveAction = new MoveAction(this.tilemap.getPosition());
        moveAction.bindToActor(this);
        turnAction = new TurnAction();
        turnAction.bindToActor(this);
        inventoryAction = new InventoryAction();
        inventoryAction.bindToActor(this);
    }

    //Other Actors
    ActorEntity(int xwidth, int yheight, int textureId) {
        mapWidth = xwidth;
        mapHeight = yheight;
        img = new GraphicComponent(textureId);
        tilemap = new MapComponent(mapWidth,mapHeight);
        input = new InputComponent(false);
        inventory = new InventoryComponent();
        this.attachComponent(tilemap); //has map associated with position
        this.attachComponent(img); //has graphical texture associated with ID
        this.attachComponent(input);
        moving = false;
        moveAction = new MoveAction(this.tilemap.getPosition());
        moveAction.bindToActor(this);
    }

    //Test Actor
    public ActorEntity(int xwidth, int yheight) {
        mapWidth = xwidth;
        mapHeight = yheight;
        img = new GraphicComponent();
        tilemap = new MapComponent(mapWidth,mapHeight);
        input = new InputComponent(false);
        stats = new StatComponent(new double[] {0, 0, 0, 0, 0, 0});
        inventory = new InventoryComponent();
        this.attachComponent(tilemap); //has map associated with position
        this.attachComponent(img); //has graphical texture associated with ID
        this.attachComponent(input);
        moving = false;
        moveAction = new MoveAction(this.tilemap.getPosition());
        moveAction.bindToActor(this);
    }

    @Override
    public void update(float dt) {
        for (Component c : components) {
            c.update(dt);
            if (c instanceof InputComponent) {
                performInputEvents(dt, c);
            } //else if (c instanceof StatComponent) { .....
        }
    }

    private void performInputEvents(float dt, Component component) {
        InputComponent inputComponent = (InputComponent) component;
        if (!inputComponent.isHidden()) {
            if (Gdx.input.isTouched(0)
                    && this.moving
                    && Gdx.input.getX(0)
                    <= (Gdx.graphics.getWidth() - 300)
                    && Gdx.input.getY(0)
                    <= (Gdx.graphics.getHeight() - 300)
                    && Gdx.input.getX(0) >= 300) {
                //moveAction.updateDestination(inputComponent.mapTapLocation(0));
                //moveAction.execute(dt);
                turnAction.execute(dt);
            }
        }
    }

}
