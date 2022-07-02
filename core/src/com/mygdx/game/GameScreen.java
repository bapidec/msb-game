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
import handlers.ShrewContact;
import objects.player.Food;
import objects.player.Player;
import objects.player.Spider;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
    Texture shrew;
    OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    TileMapHelper tileMapHelper;
    TextureRegion[] shrewAnimationFrames;
    Animation shrewAnimation;
    float elapsedTime;

    protected Player player;

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

        this.shrew = new Texture("shrew_1.png");
    }

    public void update() {
        world.step(1/60f,6,2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
    }

    void cameraUpdate() {       // by kamera podążała za graczem
        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }

    @Override
    public abstract void render(float delta); //{
//        this.update();
//        Gdx.gl.glClearColor(0,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        orthogonalTiledMapRenderer.render();
//        batch.begin();
//        batch.draw(shrew, player.getBody().getPosition().x*PPM-(shrew.getWidth()/2),player.getBody().getPosition().y*PPM-(shrew.getHeight()/2));
//        if(food != null)
//            batch.draw(shrew, food.getBody().getPosition().x*PPM-(shrew.getWidth()/2),food.getBody().getPosition().y*PPM-(shrew.getHeight()/2));
//        batch.end();
//        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
//    }

    public World getWorld() {       // potrzebny do Body
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
