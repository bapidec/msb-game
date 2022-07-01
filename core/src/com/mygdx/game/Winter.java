package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Winter extends GameScreen {
	
	public Winter(MainGame game, String mapPath) {
		super(game, mapPath);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new InputAdapter() {
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.SPACE) {
	            	game.setScreen(new TitleScreen(game));
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
//		font.draw(batch, "WINTER", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//		batch.end();
//	}
	
	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
