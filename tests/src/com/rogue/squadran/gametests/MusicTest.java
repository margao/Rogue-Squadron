package com.rogue.squadran.gametests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class MusicTest {
    private Music music;

    @Test
    public void muteMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("../android/assets/startmusic.mp3"));
        music.setVolume(0);

        assertTrue("This test will only pass if music is playing.", music.getVolume() == 0);
    }

}
