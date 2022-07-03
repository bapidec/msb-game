package com.mygdx.game;

import Helper.TileMapHelper;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import handlers.ShrewContact;
import objects.player.Enemy;
import objects.player.Food;
import objects.player.Player;
import objects.player.Spider;

import java.util.Random;

import static Helper.Constants.SCALE;

public abstract class GameScreen extends ScreenAdapter {

    OrthographicCamera camera;
    SpriteBatch batch;
    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    int widthScreen, heightScreen;
    MainGame game;
	BitmapFont font;
    Texture shrew;
    OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    TileMapHelper tileMapHelper;

    protected Player player;

    protected Enemy[] enemies;

    protected ShrewContact shrewCollisions;
    protected Texture background;
    protected Sprite backgroundSprite;
    public static Random rand;

    public GameScreen(MainGame game, String mapPath) {
        this.rand = new Random();
    	this.font = new BitmapFont();
    	this.game = game;
    	this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, widthScreen/SCALE, heightScreen/SCALE);
        this.batch = new SpriteBatch();

        this.world = new World(new Vector2(0,-25), false);
        this.shrewCollisions = new ShrewContact();
        this.world.setContactListener(shrewCollisions);

        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap(mapPath);
    }

    public void update() {
        world.step(1/60f,6,2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        for(int i=0 ; i<10; i++){
            if(enemies[i]!=null){
                enemies[i].update();
            }
        }
    }

    void cameraUpdate() {       // by kamera podążała za graczem
        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }

    @Override
    public abstract void render(float delta);

    public World getWorld() {       // potrzebny do Body
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
