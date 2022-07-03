package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import static Helper.Constants.PPM;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import objects.player.*;

import java.util.ArrayList;

public class Winter extends GameScreen{
	private int it=0, jt=0;
	Music summerTheme;
	public Winter(MainGame game, String mapPath) {
		super(game, mapPath);

		super.background = new Texture("background_winter.png");
		super.backgroundSprite = new Sprite(super.background);

		summerTheme = Gdx.audio.newMusic(Gdx.files.internal("music/Winter Theme.mp3"));
		summerTheme.setLooping(true);
		summerTheme.setVolume(0.5f);
		this.enemies.add(new BigSpider(1280,1500, world ));
	}

	@Override
	public void show() {
		super.show();
		summerTheme.play();
	}
	@Override
	public void update() {
		if(player.getPower() <= -50)
			super.game.setScreen(new GameOver(super.game));
		super.update();
		it++;
		if(it%120==0){
			super.enemies.add(new Spider(this.rand.nextInt(2496),850, super.world));
			it=0;
		}

		for(int i=0; i<enemies.size(); i++){
			if(!enemies.get(i).isAlife()){
				this.world.destroyBody(enemies.get(i).getBody());
				enemies.get(i).getBody().setUserData(null);
				enemies.get(i).setBody(null);
				enemies.remove(i);
			}
		}

		for(int i=0; i<venoms.size(); i++) {
			if(venoms.get(i).getBody().getLinearVelocity().x==0 && venoms.get(i).getBody().getLinearVelocity().y==0)
				venoms.get(i).gone();
			if (!venoms.get(i).isExists()) {
				this.world.destroyBody(venoms.get(i).getBody());
				venoms.get(i).getBody().setUserData(null);
				venoms.get(i).setBody(null);
				venoms.remove(i);
			}
		}
	}

	@Override
	public void render(float delta) {
		this.update();

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//orthogonalTiledMapRenderer.render();
		super.batch.begin();
		super.backgroundSprite.setPosition(super.player.getBody().getPosition().x * PPM - super.background.getWidth()/2f,
				super.player.getBody().getPosition().y * PPM-  96);
		super.backgroundSprite.draw(super.batch);

		Sprite sprite = new Sprite(shrew);
		sprite.flip(player.isDirection(),false);
		batch.draw(sprite, player.getBody().getPosition().x*PPM-(sprite.getWidth()/2),
				player.getBody().getPosition().y*PPM-(sprite.getHeight()/2),(player.getPower()+64)*1.25f,
				(player.getPower()+64)*1.25f);

		for(Enemy e: super.enemies){
			Sprite s;
			if(e instanceof Spider){
				s = new Sprite(Enemies.SPIDER.getTexture());
				s.flip(!e.leftDirection(),false);
				batch.draw(s, e.getBody().getPosition().x*PPM-(s.getWidth()/2),
						e.getBody().getPosition().y*PPM-(s.getHeight()/2),Enemies.SPIDER.getWidth(),
						Enemies.SPIDER.getHeight());
			}else if(e instanceof BigSpider){
				s = new Sprite(Enemies.BIG_SPIDER.getTexture());
				s.flip(!e.leftDirection(),false);

				BigSpider bs = (BigSpider)e;
				batch.draw(s, bs.getBody().getPosition().x*PPM-(Enemies.BIG_SPIDER.getWidth()/2),
						bs.getBody().getPosition().y*PPM-(Enemies.BIG_SPIDER.getHeight()/2),(bs.getPower()+64)*1.25f,
						(bs.getPower()+64)*1.25f);
//				if(jt%60==0){
//					bs.jump();
//					jt=0;
//				}
			}
		}

		for(Venom v: venoms){
			v.render(super.batch);
		}

		super.batch.end();
		super.orthogonalTiledMapRenderer.render();
		//super.box2DDebugRenderer.render(world, camera.combined.scl(PPM));

	}

	@Override
	public void hide(){
		Gdx.input.setInputProcessor(null);
		summerTheme.stop();
	}

	@Override
	void cameraUpdate() {       // by kamera podozala za graczem
		Vector3 position = camera.position;
		position.x = Math.round(super.player.getBody().getPosition().x * PPM * 10) / 10f;
		position.y = Math.round(super.player.getBody().getPosition().y * PPM * 10) / 10f + background.getHeight()/2f - 196f;
		camera.position.set(position);
		camera.update();
	}
}
