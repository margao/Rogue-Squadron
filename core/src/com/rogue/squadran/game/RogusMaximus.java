package com.rogue.squadran.game;

import com.badlogic.gdx.Game;


public class RogusMaximus extends Game {
    public boolean isSplashScreen = false;

    @Override
    public void create() {
        isSplashScreen = true;
        setScreen(new Splash(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
