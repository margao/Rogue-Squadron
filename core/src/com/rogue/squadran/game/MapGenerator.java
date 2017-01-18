package com.rogue.squadran.game;

import java.util.Random;

/**
 * Created by Matt on 11/8/16.
 * Super simple at the moment; builds a rectangular map of random dimensions,
 * and places the player character's spawn point in the bottom left corner.
 */

public class MapGenerator {

    private static final int MIN = 5;
    private static final int MAX = 50;

    public static WorldMapEntity generate() {

        Random rand = new Random();

        int width = rand.nextInt((MAX - MIN) + 1) + MIN; //height
        int height = rand.nextInt((MAX - MIN) + 1) + MIN; //width

        Point2D[] enemySpawnPoints = new Point2D[]{
            new Point2D(1, height - 2),
            new Point2D(1, height - 2),
            new Point2D(1, height - 2)};

        WorldMapEntity map = new WorldMapEntity(1, 1);

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < width; i++) {
                if (j == 0 || j == height - 1 || i == 0 || i == height - 1) {
                    map.tilemap.addId(1,i,j);
                }
            }
        }

        return map;
    }
}
