package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Splash implements Screen {
    private SpriteBatch batch;
    private Texture textureSplash;
    private Texture textureStart;
    private Texture textureInfo;
    private RogusMaximus game;
    private Music music;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    int timer = 0;

    public Splash(RogusMaximus game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(textureSplash, width / 2 - 1470 / 2, height / 2 - 615 / 5, 1470, 615);
        batch.draw(textureInfo, width / 2 - 1263 / 2, height / 10, 1263, 39);
        if (timer < 80) {
            batch.draw(textureStart, width / 2 - 1059 / 2, height / 4, 1059, 39);
        }
        if (timer == 120) {
            timer = 0;
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            music.stop();
            music.setLooping(false);

            music = Gdx.audio.newMusic(Gdx.files.internal("startsound.mp3"));
            music.play();

            batch.begin();
            batch.draw(textureStart, width / 2 - 1059 / 2, height / 4, 1059, 39);
            batch.end();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }

            game.setScreen(new GameScreen());
        }
        timer++;
    }


    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void show() {
        batch = new SpriteBatch();
        textureSplash = new Texture("rogue.png");
        textureStart = new Texture("start.png");
        textureInfo = new Texture("cmsc.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("startmusic.mp3"));
        music.play();
        music.setLooping(true);
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void dispose() {
        textureSplash.dispose();
        textureStart.dispose();
        textureInfo.dispose();
        batch.dispose();
    }
}
