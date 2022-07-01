package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;

public class TitleScreen extends ScreenAdapter {
	MainGame game;
	
	public TitleScreen(MainGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new InputAdapter() {
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.SPACE) {
	                Gdx.app.exit();
	            	//game.setScreen(new GameScreen(game));
	            }
	            return true;
	        }
	    });
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		game.batch.begin();
		game.batch.draw(game.img, 0, 0);
		game.batch.end();
	}
	
	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
