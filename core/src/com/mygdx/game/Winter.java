package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class Winter extends ScreenAdapter {
	MainGame game;
	
	public Winter(MainGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		
	}
	
	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
