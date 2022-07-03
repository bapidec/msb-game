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

import java.util.ArrayList;

public class Summer extends GameScreen{
	private Texture[] seeds;
	private ArrayList<Food> foods;
	private int it=0;
	private int generatedFood = 0, generatedEnemies = 0;
	Music summerTheme;
	public Summer(MainGame game, String mapPath) {
		super(game, mapPath);

		super.background = new Texture("background.png");
		super.backgroundSprite = new Sprite(super.background);

		this.seeds = new Texture[3];
		this.seeds[0] = new Texture("seed_1.png");
		this.seeds[1] = new Texture("seed_2.png");
		this.seeds[2] = new Texture("seed_3.png");

		this.foods = new ArrayList<Food>();

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
		it++;
		if(it%120==0){
			if(GameScreen.rand.nextInt(2)==0){
				super.enemies.add(new Spider(this.rand.nextInt(2496),850, super.world));
			}else{
				super.enemies.add(new Larva(this.rand.nextInt(2496),850, super.world));
			}
			it=0;
		}else if(it%120==60){
			int temp = GameScreen.rand.nextInt(3);
			if(temp==0){
				this.foods.add(new Food(super.world, "",
						GameScreen.rand.nextInt(2496), 850,16, 16, seeds[0]));
			}else if(temp==1){
				this.foods.add(new Food(super.world, "",
						GameScreen.rand.nextInt(2496), 850,16,16, seeds[1]));
			}else{
				this.foods.add(new Food(super.world, "",
						GameScreen.rand.nextInt(2496), 850,16,16, seeds[2]));
			}
		}
		for(int i=0; i<foods.size(); i++) {
			if (foods.get(i).getEaten()) {
				this.world.destroyBody(foods.get(i).getBody());
				foods.get(i).getBody().setUserData(null);
				foods.get(i).setBody(null);
				foods.remove(i);
			}
		}
		for(int i=0; i<enemies.size(); i++){
			if(!enemies.get(i).isAlife()){
				this.world.destroyBody(enemies.get(i).getBody());
				enemies.get(i).getBody().setUserData(null);
				enemies.get(i).setBody(null);
				enemies.remove(i);
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
				player.getBody().getPosition().y*PPM-(sprite.getHeight()/2),(player.getPower()+64)*1.25f,(player.getPower()+64)*1.25f);
		
		for(Food f: foods)
				batch.draw(f.texture, f.getBody().getPosition().x*PPM-(f.texture.getWidth()/2),
						f.getBody().getPosition().y*PPM-(f.texture.getHeight()/2));

		for(Enemy e: super.enemies){
			e.render(super.batch);

		}

		if(super.projectile != null) {
			super.batch.draw(super.venom, super.projectile.getBody().getPosition().x*PPM,
					super.projectile.getBody().getPosition().y*PPM);
		}
		super.batch.end();
		super.orthogonalTiledMapRenderer.render();
//		super.box2DDebugRenderer.render(world, camera.combined.scl(PPM));

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
