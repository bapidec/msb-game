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
import objects.player.*;

public class Summer extends GameScreen{
	private Texture[] seeds;
	private Food []foods;
	Music summerTheme;
	public Summer(MainGame game, String mapPath) {
		super(game, mapPath);

		super.background = new Texture("background.png");
		super.backgroundSprite = new Sprite(super.background);

		this.seeds = new Texture[3];
		this.seeds[0] = new Texture("seed_1.png");
		this.seeds[1] = new Texture("seed_2.png");
		this.seeds[2] = new Texture("seed_3.png");

		this.foods = new Food[10];
		super.enemies = new Enemy[10];
		for(int i=0; i<10; i++){
			foods[i] = new Food(this.world, "", this.rand.nextInt(2496),850,16,16);
		}
		for(int i=0; i<10; i++){
			if(i%2 == 0){
				super.enemies[i] = new Spider(this.rand.nextInt(2496), 850, this.world);
			}else{
				super.enemies[i] = new Larva(this.rand.nextInt(2496), 850, this.world);
			}
		}

		summerTheme = Gdx.audio.newMusic(Gdx.files.internal("music/Summer Theme.mp3"));
		summerTheme.setLooping(true);
		summerTheme.setVolume(0.25f);
	}
	
	@Override
	public void show() {
		super.show();
		summerTheme.play();
	}
	@Override
	public void update() {
		super.update();
		for(int i=0 ; i<10; i++) {
			if (foods[i] != null && foods[i].getEaten()) {
				this.world.destroyBody(this.foods[i].getBody());
				this.foods[i].getBody().setUserData(null);
				this.foods[i].setBody(null);
				this.foods[i] = null;
			}
		}
		for(Enemy e: super.enemies)
			if(!e.isAlife()){
				e.createEnemy();
				System.out.println("hahahuhujhuj");
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
		batch.draw(shrew, player.getBody().getPosition().x*PPM-(shrew.getWidth()/2),
				player.getBody().getPosition().y*PPM-(shrew.getHeight()/2));
		for(int i=0; i<10; i++)
			if(foods[i] != null)
				batch.draw(shrew, foods[i].getBody().getPosition().x*PPM-(shrew.getWidth()/2),
						foods[i].getBody().getPosition().y*PPM-(shrew.getHeight()/2));

		for(Enemy e: super.enemies){
			e.render(super.batch);
		}
		if(super.projectile != null) {
			super.batch.draw(super.venom, super.projectile.getBody().getPosition().x*PPM,super.projectile.getBody().getPosition().y*PPM);
		}
		super.batch.end();
		super.orthogonalTiledMapRenderer.render();
		super.box2DDebugRenderer.render(world, camera.combined.scl(PPM));

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
