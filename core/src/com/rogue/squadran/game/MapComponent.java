package com.rogue.squadran.game;

/**
 *  Map component which holds data of object's position.
 *  Based on array and has various accessor methods.
 *
 **/

public class MapComponent extends Component {

    public int[][] tilemapArray;
    int positionX;
    int positionY;
    private Point2D position;

    public MapComponent(int width, int height) {
        this.tilemapArray = new int[height][width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                this.tilemapArray[j][i] = 0;
            }
        }
        this.positionX = 0;
        this.positionY = 0;
        this.position = new Point2D(0,0);
    }

    int getPosX() {
        return positionX;
    }

    int getPosY() {
        return positionY;
    }

    Point2D getPosition() {
        return position;
    }

    public int getSizeX() {
        return tilemapArray[0].length;
    }

    public int getSizeY() {
        return tilemapArray.length;
    }

    public void addId(int id, int xloc, int yloc) {
        this.tilemapArray[yloc][xloc] = id;
        this.positionX = xloc;
        this.positionY = yloc;
        this.position.setX(xloc);
        this.position.setY(yloc);
    }

    public int getId(int xlocation, int ylocation) {
        return this.tilemapArray[ylocation][xlocation];
    }

    void removeId(int xlocation, int ylocation) {
        this.tilemapArray[ylocation][xlocation] = 0;
    }
}
