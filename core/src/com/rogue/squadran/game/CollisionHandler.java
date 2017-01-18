package com.rogue.squadran.game;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;


/*  this class is for the system that contains all the collision components
    as well as checks for collisions each time an object's position is updated
 */

import java.util.ArrayList;

class CollisionHandler {
    private int key;
    int key2;
    private CollisionComponent[] boxes;

    CollisionHandler() {
        boxes = new CollisionComponent[10];
        key = 0;
    }

    int addCollider(CollisionComponent collisionComponent) {
        for (int i = 0; i < key; i++) {
            if (!boxes[i].isActive()) {  //checks if any space is currently inactive
                boxes[i] = collisionComponent;//and then uses that key instead of adding to the end
                return i;
            }
        }
        if (key < 10) {
            boxes[key] = collisionComponent;
            key++;
            return key - 1;
        } else {
            CollisionComponent[] boxesTemp = new CollisionComponent[key * 2];
            System.arraycopy(boxes, 0, boxesTemp, 0, key);
            boxes = boxesTemp;
            boxes[key] = collisionComponent;
            key++;
            return (key - 1);
        }
    }

    private boolean checkNewPos(Point2D point) {
        for (int i = 0; i < key; i++) {
            if (point.equals(boxes[i].getPoint()) && boxes[i].isActive()) {
                //checks if the new position collides with any other point
                return false;       //returns false if collision is detected
            }
        }
        return true;                //returns true if no collision detected
    }

    boolean updatePos(int key, Point2D point) {
        if (!checkNewPos(point)) {
            return false;
        } else {
            boxes[key].setPos(point);
        }
        return true;
    }

    public ArrayList<CollisionComponent> inRange(int key1, int range) {
        ArrayList<Point2D> attackRange = new ArrayList<Point2D>();
        int index = 0;
        int xposition = boxes[key1].getPoint().getX();
        int yposition = boxes[key1].getPoint().getY();
        for (int i = xposition - range; i <= xposition + range; i++) {
            // adds all movable tiles to movable

            int dx = abs(i - xposition); // outer loop goes from minX to maxX
            int maxY = (int) sqrt(range * range - dx * dx); // computes a maxY for each value of x
            for (int j = yposition + maxY; j >= yposition - maxY; j--) {
                // adds points from +maxY to -maxY
                attackRange.add(index, new Point2D(i, j));
                index++;
            }
        }
        ArrayList<CollisionComponent> vulnerable = new ArrayList<CollisionComponent>();
        for (int i = 0; i < attackRange.size(); i++) {
            for (int j = 0; j < key; j++) {
                if (attackRange.get(i).getX() == boxes[j].getPoint().getX()
                        && attackRange.get(i).getY() == boxes[j].getPoint().getY()) {
                    if (j != key1) {
                        vulnerable.add(boxes[j]);
                    }
                }
            }
        }


        return vulnerable;
    }
}
