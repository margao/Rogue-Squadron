package com.rogue.squadran.game;

public class Point2D {
    private int xloc;
    private int yloc;

    public Point2D() {
        xloc = 0;
        yloc = 0;
    }

    Point2D(int xloc, int yloc) {
        this.xloc = xloc;
        this.yloc = yloc;
    }

    public int getX() {
        return xloc;
    }

    void setX(int xloc) {
        this.xloc = xloc;
    }

    public int getY() {
        return yloc;
    }

    void setY(int yloc) {
        this.yloc = yloc;
    }

    public void setPoint(int xloc, int yloc) {
        this.xloc = xloc;
        this.yloc = yloc;
    }

}
