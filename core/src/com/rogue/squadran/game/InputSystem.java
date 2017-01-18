package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public final class InputSystem {

    //will only read 20 taps
    private static final int MAX_TOUCHES = 20;

    private InputSystem(){}

    static void setInputProcessor(InputProcessor inputProcessor) {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public static ArrayList<Point2D> getTouches() {

        ArrayList<Point2D> pointsTouched = new ArrayList<Point2D>();
        for (int i = 0; i < MAX_TOUCHES; i++) {
            pointsTouched.add(new Point2D(
                    Gdx.input.getX(i),
                    (-Gdx.input.getY(i)) + Gdx.graphics.getHeight()));
        }
        return pointsTouched;

    }

    public static Point2D getTouchByIndex(int index) {
        return new Point2D(
                Gdx.input.getX(index),
                (-Gdx.input.getY(index)) + Gdx.graphics.getHeight());
    }

}
