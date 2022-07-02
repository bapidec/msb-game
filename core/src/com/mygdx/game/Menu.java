package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Menu extends MainGame {

    public TextureAtlas butonAtlas;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    public Stage stage;
    public TextButton newGameButton;

    public Skin skin;

    public Menu(MainGame game) {
        super();
    }

    public void render(float deltaTime) {

        this.batch.begin();
        this.sprite.draw(this.batch);

        this.batch.end();
        this.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
    }

    public void show() {
        butonAtlas = new TextureAtlas(
                Gdx.files.internal("buttons/buttons.pack"));
        stage = new Stage();
        skin = new Skin();
        skin.addRegions(butonAtlas);

        Gdx.input.setInputProcessor(stage);

        newGameButton.addListener(new EventListener() {

            public boolean handle(Event event) {
                return false;
            }
        });

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
    }

    public void hide() {

        batch.dispose();
        butonAtlas.dispose();
        skin.dispose();
    }

    @Override
    public void pause() {
    }
}