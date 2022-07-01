package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

	public static MainGame INSTANCE;
	
	public MainGame() {
		INSTANCE = this;
	}

	@Override
	public void create () {
		this.setScreen(new TitleScreen(INSTANCE));
	}

}
