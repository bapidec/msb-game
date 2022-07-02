package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import objects.player.Spider;

import static Helper.Constants.PPM;

public class Summer extends GameScreen{
	public Summer(MainGame game, String mapPath) {
		super(game, mapPath);
		super.background = new Texture("map/summer.png");
		super.backgroundSprite = new Sprite(super.background);
	}
	
	@Override
	public void show() {
//		Gdx.input.setInputProcessor(new InputAdapter() {
//	        @Override
//	        public boolean keyDown(int keyCode) {
//	            if (keyCode == Input.Keys.SPACE) {
//	            	game.setScreen(new Winter(game, "map/mapXD.tmx"));
//	            }
//	            return true;
//	        }
//	    });
	}
	
	@Override
	public void render(float delta) {
		super.update();
		Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.batch.begin();
		super.backgroundSprite.setPosition(super.player.getBody().getPosition().x * PPM - super.background.getWidth()/2f, super.player.getBody().getPosition().y * PPM-  96);
		super.backgroundSprite.draw(super.batch);
		super.batch.end();
		super.orthogonalTiledMapRenderer.render();
		super.box2DDebugRenderer.render(world, camera.combined.scl(PPM));

	}
	
	@Override
    public void hide(){
		Gdx.input.setInputProcessor(null);
    }

	@Override
	void cameraUpdate() {       // by kamera podazala za graczem
		Vector3 position = camera.position;
		position.x = Math.round(super.player.getBody().getPosition().x * PPM * 10) / 10f;
		position.y = Math.round(super.player.getBody().getPosition().y * PPM * 10) / 10f + background.getHeight()/2f - 96f;
		camera.position.set(position);
		camera.update();
	}
}
