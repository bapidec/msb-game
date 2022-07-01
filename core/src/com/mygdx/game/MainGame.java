package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setScreen(new TitleScreen(this));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
