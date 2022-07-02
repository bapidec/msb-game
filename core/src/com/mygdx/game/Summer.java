package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import static Helper.Constants.PPM;

public class Summer extends GameScreen{
	
	public Summer(MainGame game, String mapPath) {
		super(game, mapPath);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new InputAdapter() {
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.SPACE) {
	            	game.setScreen(new Winter(game, "map/mapXD.tmx"));
	            }
	            return true;
	        }
	    });
	}
	
//	@Override
//	public void render(float delta) {
//		Gdx.gl.glClearColor(0,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		font.draw(batch, "SUMMER", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//		batch.end();
//	}
	
	@Override
    public void hide(){
		Gdx.input.setInputProcessor(null);
    }

	@Override
	void cameraUpdate() {       // by kamera podążała za graczem
		Vector3 position = camera.position;
		position.x = Math.round(super.player.getBody().getPosition().x * PPM * 10) / 10f;
		position.y = Math.round(super.player.getBody().getPosition().y * PPM * 10) / 10f;
		camera.position.set(position);
		camera.update();
	}
}
