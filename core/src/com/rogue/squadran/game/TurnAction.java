package com.rogue.squadran.game;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import java.util.ArrayList;


class TurnAction extends Action {
    private MoveAction moveAction;
    private Point2D dest;
    private InputComponent input;
    MapComponent mapComponent;
    //int key;


    TurnAction() {

    }

    void addInput(InputComponent inputComponent) {
        input = inputComponent;
    }

    private void movePhase(float dt) {
        moveAction = actor.moveAction;
        Point2D point = actor.tilemap.getPosition();
        double agl = actor.stats.getStat(1);
        int xposition = point.getX();
        int yposition = point.getY();
        int maxX = (xposition + (int) agl);
        int maxY;
        int index = 0;
        ArrayList<Point2D> movable = new ArrayList<Point2D>();

        for (int i = xposition - (int) agl; i <= maxX; i++) { // adds all movable tiles to movable
            int dx = abs(i - xposition); // outer loop goes from minX to maxX
            maxY = (int) sqrt(agl * agl - dx * dx); // computes a maxY for each value of x
            for (int j = yposition + maxY; j >= yposition - maxY; j--) {
                // adds points from +maxY to -maxY
                movable.add(index, new Point2D(i, j));
                index++;
            }
        }
        boolean confirmed = false;

        Point2D point2 = actor.input.mapTapLocation(0);
        dest = point2;
        Point2D destTile = new Point2D(xposition + point2.getX(), yposition + point2.getY());
        moveAction.updateDestination(dest);
        for (int i = 0; i < movable.size(); i++) {
            if (destTile.getX()
                    == movable.get(i).getX() && destTile.getY()
                    == movable.get(i).getY()) { //if touched point is a movable tile
                confirmed = true;
                moveAction.execute(.1f + dt);
                actor.moving = false;
                attackPhase();
                return;
            }

        }
        moveAction.execute(dt);
        attackPhase();
    }

    private void attackPhase() {
        int str = (int) actor.stats.getStat(1);
        Point2D point = actor.tilemap.getPosition();
        int xposition = point.getX();
        int yposition = point.getY();
        int range = 1;
        ArrayList<ActorEntity> inRange = actor.collision.inRange(range);
        boolean isOver = true;

        Point2D point2 = input.mapTapLocation(0);
        Point2D destTile = new Point2D(xposition + point2.getX(), yposition + point2.getY());
        for (int i = 0; i < inRange.size(); i++) {
            if (destTile == inRange.get(i).tilemap.getPosition()) {
                isOver = true;
                double hp = inRange.get(i).stats.getStat(3);
                inRange.get(i).stats.updateStat(3, hp - str);
                return;
            }
        }


    }

    private void startTurn(float dt) {
        movePhase(dt);

    }

    @Override
    public boolean execute(float dt) {
        startTurn(dt);
        return false;
    }
}
