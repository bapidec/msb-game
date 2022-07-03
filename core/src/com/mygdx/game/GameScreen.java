package com.mygdx.game;

import Helper.TileMapHelper;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import handlers.ShrewContact;
import objects.player.Food;
import objects.player.Player;
import objects.player.Spider;

import static Helper.Constants.PPM;
import static Helper.Constants.SCALE;

public abstract class GameScreen extends ScreenAdapter {

    OrthographicCamera camera;
    SpriteBatch batch;
    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    int widthScreen, heightScreen;
    MainGame game;
	BitmapFont font;

    OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    TileMapHelper tileMapHelper;

    Texture shrew;
    TextureRegion [] animationFrames;
    Animation<TextureRegion> animation;

    float stateTime;
    TextureRegion reg;
    protected Player player;

    protected Food food;

    protected ShrewContact shrewCollisions;
    protected Texture background;
    protected Sprite backgroundSprite;

    public GameScreen(MainGame game, String mapPath) {
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

//        shrew = new Texture("shrew.jpg");
        this.food = new Food(this.world, "Ziarno", 200,100,64,64);
    }

    void update() {
        world.step(1/60f,6,2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        if(food != null && food.getEaten()) {
            this.world.destroyBody(this.food.getBody());
            this.food.getBody().setUserData(null);
            this.food.setBody(null);
            this.food = null;
        }
    }

    void cameraUpdate() {       // by kamera podążała za graczem
        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }

//    @Override
    public void create () {
        batch = new SpriteBatch();
        shrew = new Texture("shrew.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(shrew,64,64);
        animationFrames = new TextureRegion[2];
        int index = 0;

        for (int i = 0; i < 2; i++){
            animationFrames[index++] = tmpFrames[0][i];
        }
        animation = new Animation<TextureRegion>(0.025f, animationFrames);
        stateTime = 0f;
//        reg = animation.getKeyFrame(0);
    }

    public void dispose() {
//        batch.dispose();
//        shrew.dispose();
    }

    @Override
    public abstract void render(float delta);


    public World getWorld() {       // potrzebny do Body
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width,height);
//    }
}
