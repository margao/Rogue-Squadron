package com.rogue.squadran.game;

/**
 *  Class which holds map data.
 *  Current components: Map, Graphic
 *
 **/

public class WorldMapEntity extends Entity {

    private static final int TEXTURE_SIZE = 128;
    public MapComponent tilemap;
    private GraphicComponent img;
    int mapHeight;
    int mapWidth;

    public WorldMapEntity(int xloc, int yloc) {
        mapWidth = xloc;
        mapHeight = yloc;
        img = new GraphicComponent();
        tilemap = new MapComponent(mapWidth,mapHeight);
        this.attachComponent(tilemap); //has map associated with position
        this.attachComponent(img); //has graphical texture associated with ID
    }
}
