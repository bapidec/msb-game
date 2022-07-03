package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen extends ScreenAdapter {
	
	private SpriteBatch batch;
	private Texture img;
	private MainGame game;
	
	public TitleScreen(MainGame game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.img = new Texture(Gdx.files.internal("badlogic.png"));
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new InputAdapter() {
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.SPACE) {
	            	game.setScreen(new Summer(game, "map/level_0.tmx"));
	            }
	            return true;
	        }
	    });
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
