package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;

class InputComponent extends Component {

    private static final double TILE_SIZE = 150;
    private boolean isComponentHidden;

    InputComponent(boolean isHidden) {
        isComponentHidden = isHidden;
    }

    public void setHidden(boolean isHidden) {
        isComponentHidden = isHidden;
    }

    boolean isHidden() {
        return isComponentHidden;
    }

    Point2D mapTapLocation(int index) {
        double inputx = Gdx.input.getX(index);
        double inputy = (-Gdx.input.getY(index)) + Gdx.graphics.getHeight();
        double offsetx = (Gdx.graphics.getWidth() / 2) - (TILE_SIZE / 2);
        double offsety = (Gdx.graphics.getHeight() / 2) - (TILE_SIZE / 2);

        return new Point2D(
                (int)Math.floor(((inputx - offsetx) / TILE_SIZE)),
                (int)Math.floor(((inputy - offsety) / TILE_SIZE)));
    }

}
