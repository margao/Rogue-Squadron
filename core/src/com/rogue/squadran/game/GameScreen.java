package com.rogue.squadran.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;

public class GameScreen implements Screen {
    private OrthographicCamera camera = null;
    private MapView playerActorView;
    private MapView enemyActorView;
    private MapView mapView;
    private ActorEntity player;
    private ActorEntity enemy;
    private WorldMapEntity worldmap;
    private ShapeRenderer shape;
    private Stage stage;
    private Music music;
    private Table inv;
    private Drawable emptySlot;
    private Drawable sword;
    private CollisionHandler collisionHandler;
    static int TILE_SIZE = 128;
    private static int EFFECTIVE_TILE_SIZE = 150;


    @Override
    public void show() {

        SpriteBatch batch = new SpriteBatch();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        shape = new ShapeRenderer();
        stage = new Stage();

        music = Gdx.audio.newMusic(Gdx.files.internal("labyrinth.mp3"));
        music.play();
        music.setLooping(true);

        //Multiplexer for parsing UI input vs. Game World input
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GameInputProcessor());
        inputMultiplexer.addProcessor(stage);
        InputSystem.setInputProcessor(inputMultiplexer);

        //get size of current android screen
        final int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        //create new orthographic camera with those dimensions
        camera = new OrthographicCamera(width, height);
        //set world map width to 15 tiles across and height to 9 tiles high
        int mapWidth = 50;
        int mapHeight = 50;

        //create collision handler
        collisionHandler = new CollisionHandler();

        //create world map, player map and enemy map
        worldmap = new WorldMapEntity(mapWidth, mapHeight);
        player = new ActorEntity(mapWidth, mapHeight, 0,
                BaseStats.WARRIOR_BASE_STATS, collisionHandler);
        enemy = new ActorEntity(mapWidth, mapHeight, 1,
                BaseStats.ARCHER_BASE_STATS, collisionHandler);

        //Add base weapons to game and equip base player type.
        //IMPORTANT!! Base stat arrays are stored in BaseStats class.
        player.inventory.addEquipment(BaseStats.BASIC_SWORD);
        enemy.inventory.addEquipment(BaseStats.BASIC_BOW);
        player.inventory.equip(BaseStats.BASIC_SWORD);
        enemy.inventory.equip(BaseStats.BASIC_BOW);

        //add player and 3 enemies
        player.tilemap.addId(1,3,5);
        enemy.tilemap.addId(1,3,4);
        enemy.tilemap.addId(1,5,2);
        enemy.tilemap.addId(1,10,7);

        //Centering camera correctly
        camera.position.set(
                player.tilemap.getPosX() * EFFECTIVE_TILE_SIZE + (EFFECTIVE_TILE_SIZE / 2),
                player.tilemap.getPosY() * EFFECTIVE_TILE_SIZE + (EFFECTIVE_TILE_SIZE / 2),
                0);
        camera.update();

        //set edges of world map to walls
        for (int j = 0; j < mapHeight; j++) {
            for (int i = 0; i < mapWidth; i++) {
                if (j == 0 || j == mapHeight - 1 || i == 0 || i == mapWidth - 1) {
                    worldmap.tilemap.addId(1,i,j);
                }
            }
        }

        //Create all maps
        mapView = new MapView(mapWidth, mapHeight);
        playerActorView = new MapView(mapWidth, mapHeight);
        enemyActorView = new MapView(mapWidth, mapHeight);

        //Defining skin
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        Skin skin = new Skin();
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", new BitmapFont());
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        //Right sided move button
        Table rightTable = new Table();
        rightTable.setFillParent(true);
        rightTable.right().top();
        stage.addActor(rightTable);
        final TextButton moveButton = new TextButton("move mode", skin);
        moveButton.getLabel().setFontScale(3);
        rightTable.add(moveButton).size(300);

        //Left sided inventory button
        Table leftTable = new Table();
        leftTable.setFillParent(true);
        leftTable.left().top();
        stage.addActor(leftTable);
        final TextButton inventoryButton = new TextButton("inventory", skin);
        inventoryButton.getLabel().setFontScale(3);
        leftTable.add(inventoryButton).size(300);
        inv = buildInventoryScreen();
        stage.addActor(inv);

        moveButton.addListener(new InputListener() {
            public boolean touchDown(
                    InputEvent event, float xloc, float yloc, int pointer, int btn) {
                player.moving = !player.moving;
                return true;
            }
        });

        inventoryButton.addListener(new InputListener() {
            public boolean touchDown(
                    InputEvent event, float xloc, float yloc, int pointer, int btn) {
                inv.setVisible(!inv.isVisible());
                return true;
            }
        });
    }

    private Table buildInventoryScreen() {
        Table inventory = new Table();
        inventory.setFillParent(true);
        inventory.left().bottom();
        inventory.setDebug(true);

        Texture emptyTexture = new Texture("empty.jpg");
        Texture swordTexture = new Texture("sword1.png");

        emptySlot = new TextureRegionDrawable(new TextureRegion(emptyTexture));
        sword = new TextureRegionDrawable(new TextureRegion(swordTexture));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (player.inventory.checkEquipment(new Point2D(i, j)) == null) {
                    ImageButton inventorySlot = new ImageButton(emptySlot);
                    inventory.add(inventorySlot);
                } else {
                    ImageButton inventorySlot = new ImageButton(sword);
                    inventory.add(inventorySlot);
                }
            }
            inventory.row();
        }
        inventory.setVisible(false);

        return inventory;
    }

    @Override
    public void render(float delta) {

        camera.position.set(
                player.tilemap.getPosX() * EFFECTIVE_TILE_SIZE + (EFFECTIVE_TILE_SIZE / 2),
                player.tilemap.getPosY() * EFFECTIVE_TILE_SIZE + (EFFECTIVE_TILE_SIZE / 2),
                0);
        camera.update();

        //clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //refresh maps

        mapView.buildMap(worldmap, camera);
        playerActorView.buildPlayerActors(player, camera);
        enemyActorView.buildEnemyActors(enemy, camera);
        player.update(Gdx.graphics.getDeltaTime());
        if (inv.isVisible()) {
            player.inventoryAction.execute(delta, inv, emptySlot);
        }


        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        //debugging purposes
        //draws green dot at center of screen
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0, 1, 0, 1);
        shape.circle(width / 2, height / 2, 20);
        shape.end();

        //update UI
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
