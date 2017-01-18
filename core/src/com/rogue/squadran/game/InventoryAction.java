package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class InventoryAction extends Action {

    private float timeSinceLastUpdate;

    InventoryAction() {
        timeSinceLastUpdate = 0.0f;
    }

    @Override
    public boolean execute(float dt) {
        return false;
    }

    public boolean execute(float dt, Table inv, Drawable empty) {

        timeSinceLastUpdate += dt;

        if (timeSinceLastUpdate >= 3f) {
            for (Cell c: inv.getCells()) {
                if (c.getActor() instanceof ImageButton) {
                    ImageButton ib = (ImageButton) c.getActor();
                    if (Gdx.input.getX(0) < ib.getX() + ib.getWidth()
                            && Gdx.input.getX(0) > ib.getX()
                            && (-Gdx.input.getY(0)) + Gdx.graphics.getHeight()
                            < ib.getY() + ib.getHeight()
                            && (-Gdx.input.getY(0)) + Gdx.graphics.getHeight()
                            > ib.getY()) {
                        if (!ib.getImage().getDrawable().equals(empty)) {
                            determineInvFunction();
                            timeSinceLastUpdate = 0f;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private void determineInvFunction() {
        if (actor.inventory.equippedItems.contains(BaseStats.BASIC_SWORD)) {
            actor.inventory.unequip(BaseStats.BASIC_SWORD);
            Gdx.app.debug("Stat 1 After Un-equip",
                    "Strength - " + actor.stats.getStat(0));
        } else {
            actor.inventory.equip(BaseStats.BASIC_SWORD);
            Gdx.app.debug("Stat 1 After Equip",
                    "Strength - " + actor.stats.getStat(0));
        }
    }

}