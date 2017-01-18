package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

class MoveAction extends Action {
    private Point2D dest;
    private float timeSinceLastMove;
    Logger logger = new Logger("MoveLog");
    WorldMapEntity world;

    MoveAction(Point2D dest) {
        this.dest = dest;
        timeSinceLastMove = 0.0f;
    }

    @Override
    public boolean execute(float dt) {
        timeSinceLastMove += dt;

        if (timeSinceLastMove >= .1f) {
            MapComponent map = (MapComponent) actor.getComponent(MapComponent.class);

            if (dest.getX() > 0
                    && dest.getY() > 0
                    && dest.getX() < 49
                    && dest.getY() < 49
                    && map.getId(dest.getX(), dest.getY()) != 1) {

                int dx = dest.getX() - map.positionX;
                int dy = dest.getY() - map.positionY;
                map.removeId(map.positionX, map.positionY);

                if (dx == 0 && dy == 0) {
                    return true;
                }

                if (Math.abs(dx) >= Math.abs(dy)) {
                    map.positionX += (int) Math.signum(dx);
                } else {
                    map.positionY += (int) Math.signum(dy);
                }
                map.addId(1, dest.getX(), dest.getY());

                timeSinceLastMove = 0.0f;
            }
        }

        return false;
    }

    void updateDestination(Point2D dest) {
        int dx = dest.getX();
        int dy = dest.getY();
        MapComponent map = (MapComponent) actor.getComponent(MapComponent.class);
        this.dest.setX(dx + map.positionX);
        this.dest.setY(dy + map.positionY);
    }

}
