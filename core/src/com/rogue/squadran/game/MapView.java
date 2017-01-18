package com.rogue.squadran.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *  Rendering system which renders map using center of screen as reference point.
 *
 **/


class MapView {

    static final int TEXTURE_SIZE = 128;
    private static final int EFFECTIVE_SIZE = 150;
    private int mapHeight;
    private int mapWidth;
    private SpriteBatch batch;
    private Texture wall = new Texture("wall.png");
    private Texture floor = new Texture("floor.png");

    //Constructor
    MapView(int width, int height) {
        mapWidth = width;   //# of tiles spanning width of map
        mapHeight = height; //# of tiles spanning height of map
        batch = new SpriteBatch();
    }

    void buildMap(WorldMapEntity world, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);

        //iterate through world map's tilemapArray and draw wall where tilemapArray[i][j] == 1
        batch.begin();
        for (int j = 0; j < mapHeight; j++) {
            for (int i = 0; i < mapWidth; i++) {
                if (world.tilemap.getId(i,j) == 1) {
                    batch.draw(
                            wall,
                            i * EFFECTIVE_SIZE,
                            j * EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE);
                } else {
                    batch.draw(floor,
                            i * EFFECTIVE_SIZE,
                            j * EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE); // else draw floor
                }
            }
        }
        batch.end();

    }

    void buildPlayerActors(ActorEntity actor, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);

        //iterate through player actor's tilemapArray
        //(which is of same size as worldmap) and draw player at the center of the screen
        batch.begin();
        for (int j = 0; j < mapHeight; j++) {
            for (int i = 0; i < mapWidth; i++) {
                if (actor.tilemap.getId(i, j) == 1) {
                    batch.draw(
                            actor.img.getTexture(),
                            i * EFFECTIVE_SIZE,
                            j * EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE);
                }
            }
        }
        batch.end();
    }

    void buildEnemyActors(ActorEntity enemy, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);

        //iterate through enemy actor's tilemapArray
        //(which is of same size as worldmap) and draw enemies based on enemy ID
        batch.begin();
        for (int j = 0; j < mapHeight; j++) {
            for (int i = 0; i < mapWidth; i++) {
                if (enemy.tilemap.getId(i,j) == 1) {
                    batch.draw(
                            enemy.img.getTexture(),
                            i * EFFECTIVE_SIZE,
                            j * EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE,
                            EFFECTIVE_SIZE);
                }
            }
        }
        batch.end();
    }
}
