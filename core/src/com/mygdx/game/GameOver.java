package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver extends GameScreen{
    private int score;
    BitmapFont font;
    String gameOver;
    SpriteBatch batch;
    GlyphLayout layout = new GlyphLayout();
    public GameOver(MainGame game) {
        super(game, "map/level_0.tmx");
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.gameOver = "G A M E  O V E R";
        this.font.getData().setScale(20.0f);
//        this.layout.setText(font,this.gameOver);
        this.layout.setText(this.font,this.gameOver, Color.BLACK,Gdx.graphics.getWidth(), Align.center,true);
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

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = 0;
        float y = Gdx.graphics.getHeight()/2 + layout.height/2;

        batch.begin();
        font.draw(batch,layout,x,y);//Center Text
        batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
