package com.rogue.squadran.game;

import java.util.ArrayList;

/** Component that adds collision to an object
 *
 */

class CollisionComponent extends Component {
    private Point2D point;
    private int key;
    private boolean active;
    private CollisionHandler handler;
    ActorEntity actor;

    CollisionComponent() {
        point = new Point2D();
        active = true;

    }

    public CollisionComponent(Point2D point, CollisionHandler handler) {
        this.point = point;
        this.handler = handler;
        active = true;
        key = handler.addCollider(this);
    }

    public void bindHandler(CollisionHandler handler) {
        this.handler = handler;
        handler.addCollider(this);
    }

    Point2D getPoint() {
        return point;
    }

    public boolean updatePos(Point2D point) {
        return handler.updatePos(key, point);
    }

    void addActor(ActorEntity actor) {
        this.actor = actor;
    }

    void setPos(Point2D point) {
        this.point = point;
    }

    public void removeActivity() {
        active = false;
    }

    boolean isActive() {
        return active;
    }

    ArrayList<ActorEntity> inRange(int range) {
        ArrayList<CollisionComponent> vulnerable = handler.inRange(key, range);
        ArrayList<ActorEntity>  actorEntities = new ArrayList<ActorEntity>();
        for (int i = 0; i < vulnerable.size(); i++) {
            actorEntities.add(vulnerable.get(i).actor);
        }
        return actorEntities;
    }
}
