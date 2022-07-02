package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import static Helper.Constants.PPM;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import objects.player.Enemies;
import objects.player.Food;
import objects.player.Spider;

public class Summer extends GameScreen{
	private Texture[] seeds;
	private Food food;
	public Summer(MainGame game, String mapPath) {
		super(game, mapPath);

		super.background = new Texture("background.png");
		super.backgroundSprite = new Sprite(super.background);

		this.seeds = new Texture[3];
		this.seeds[0] = new Texture("seed_1.png");
		this.seeds[1] = new Texture("seed_2.png");
		this.seeds[2] = new Texture("seed_3.png");

		this.food = new Food(this.world, "Ziarno", 200,100,10,10);
	}
	
	@Override
	public void show() {
	}
	@Override
	public void update() {
		super.update();
		if(this.food != null && this.food.getEaten()) {
			this.world.destroyBody(this.food.getBody());
			this.food.getBody().setUserData(null);
			this.food.setBody(null);
			this.food = null;
		}
	}

	@Override
	public void render(float delta) {
		this.update();
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		orthogonalTiledMapRenderer.render();
		super.batch.begin();
		super.backgroundSprite.setPosition(super.player.getBody().getPosition().x * PPM - super.background.getWidth()/2f,
				super.player.getBody().getPosition().y * PPM-  96);
		super.backgroundSprite.draw(super.batch);
		batch.draw(shrew, player.getBody().getPosition().x*PPM-(shrew.getWidth()/2),
				player.getBody().getPosition().y*PPM-(shrew.getHeight()/2));
		for(int i=0; i<10; i++)
			if(foods[i] != null)
				batch.draw(shrew, foods[i].getBody().getPosition().x*PPM-(shrew.getWidth()/2),
						foods[i].getBody().getPosition().y*PPM-(shrew.getHeight()/2));
		for(int i=0; i<10; i++){
			if(enemies[i] instanceof Spider){
				batch.draw(Enemies.SPIDER.getTexture(), enemies[i].getBody().getPosition().x*PPM- Enemies.SPIDER.getWidth()/2,
						enemies[i].getBody().getPosition().y*PPM - Enemies.SPIDER.getHeight()/2);
			}
		}
		super.batch.draw(super.shrew,player.getBody().getPosition().x*PPM-32,player.getBody().getPosition().y*PPM-32);
		if(this.food != null) {
			batch.draw(seeds[0], this.food.getBody().getPosition().x * PPM - (seeds[0].getWidth() / 2), this.food.getBody().getPosition().y * PPM - (seeds[0].getHeight() / 2));
		}
		super.batch.end();
		super.orthogonalTiledMapRenderer.render();
		super.box2DDebugRenderer.render(world, camera.combined.scl(PPM));

	}
	
	@Override
    public void hide(){
		Gdx.input.setInputProcessor(null);
    }

	@Override
	void cameraUpdate() {       // by kamera podozala za graczem
		Vector3 position = camera.position;
		position.x = Math.round(super.player.getBody().getPosition().x * PPM * 10) / 10f;
		position.y = Math.round(super.player.getBody().getPosition().y * PPM * 10) / 10f + background.getHeight()/2f - 196f;
		camera.position.set(position);
		camera.update();
	}
}
