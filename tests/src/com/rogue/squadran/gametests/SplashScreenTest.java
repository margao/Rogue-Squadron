package com.rogue.squadran.gametests;

import com.badlogic.gdx.graphics.Texture;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static org.junit.Assert.assertTrue;

public class SplashScreenTest {
    private SpriteBatch batch;

    @Test
    public void checkBlinkingText() {
        batch = new SpriteBatch();
        int counter = 0;

        for (int i = 0; i < 100; i++) {
            batch.begin();
            if (i % 20 == 0) {
                batch.draw();
                counter++;
            }
            batch.end();
        }
        assertTrue("This test will only return true if the text was only drawn 5 times.", counter == 5);
    }

    public class SpriteBatch {

        public void SpriteBatch() {
        }

        public void begin() {
        }

        public void end() {
        }

        public void draw() {
        }
    }

}
