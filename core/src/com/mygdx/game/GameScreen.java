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

    Texture shrew;

    protected Player player;

    protected Food []foods;
    protected Enemy[]enemies;

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

        this.foods = new Food[10];
        this.enemies = new Enemy[10];
        int temp=0;
        for(int i=0; i<10; i++){
            foods[i] = new Food(this.world, "", this.rand.nextInt(2496),850,16,16);
        }
        for(int i=0; i<10; i++){
            temp = this.rand.nextInt(1);
            if(temp%2 == 0){
                this.enemies[i] = new Spider(this.rand.nextInt(2496), 850, this.world);
            }else{

            }
        }
        this.foods[1] = new Food(this.world, "", 200,100,64,64);
        this.shrew = new Texture("shrew_1.png");
    }

    public void update() {
        world.step(1/60f,6,2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        for(int i=0 ; i<10; i++) {
            if (foods[i] != null && foods[i].getEaten()) {
                this.world.destroyBody(this.foods[i].getBody());
                this.foods[i].getBody().setUserData(null);
                this.foods[i].setBody(null);
                this.foods[i] = null;
            }
        }
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
