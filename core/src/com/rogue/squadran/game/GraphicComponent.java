package com.rogue.squadran.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Graphic component which holds texture data and has various accessor methods.
 **/
public class GraphicComponent extends Component {
    private int textureId;
    private Texture img;

    GraphicComponent() {
    }

    GraphicComponent(int textureId) {
        this.textureId = textureId;
        setTexture(textureId);
    }


    private void setTexture(int texId) {
        switch (texId) {
          case 0:
              img = new Texture("player.png");
              textureId = 0;
              break;
          case 1:
              img = new Texture("enemy.png");
              textureId = 1;
              break;
          default:
              img = new Texture("badlogic.jpg");
              textureId = 999;
              break;
        }
    }

    Texture getTexture() {
        return img;
    }

    public void setTestTexture() {
        img = new Texture(Gdx.files.internal("../android/assets/badlogic.jpg"));
        this.textureId = 999;
    }

    public Texture getWorldTexture(int texId) {
        switch (texId) {
          case 0:
              img = new Texture("floor.png");
              textureId = 0;
              return img;
          case 1:
              img = new Texture("wall.png");
              textureId = 1;
              return img;
          default:
              img = new Texture("badlogic.jpg");
              textureId = 999;
              return img;
        }
    }

    public int getTextureId() {
        return textureId;
    }
}
