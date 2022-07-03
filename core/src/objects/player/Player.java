package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameScreen;

import static Helper.Constants.PPM;

public class Player extends GameEntity {

    private int jumpcounter=0;

    private int power=0;
    private boolean direction=false; //true-lewo    false-prawo
    private GameScreen gameScreen;

    Sound eatingSound = Gdx.audio.newSound(Gdx.files.internal("music/ryjowka_je.mp3"));
    Sound walkingSound = Gdx.audio.newSound(Gdx.files.internal("music/ryjowka_ruch.mp3"));
    Sound jumpingSound = Gdx.audio.newSound(Gdx.files.internal("music/ryjowka_skok.mp3"));
    Sound hitSound = Gdx.audio.newSound(Gdx.files.internal("music/Zgon.mp3"));
    private int walkDuration=0;

    public Player(float width, float height, Body body, GameScreen gameScreen) {
        super(width, height, body);
        super.speed = 4f;
        this.gameScreen = gameScreen;
    }

    private void checkUserInput(){
        super.velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            super.velX += 1;
            this.direction = false;
            if(this.walkDuration<=0) {
                this.walkingSound.play(5f);
                this.walkDuration = 60;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            super.velX += -1;
            this.direction = true;
            if(this.walkDuration<=0) {
                this.walkingSound.play(5f);
                this.walkDuration = 60;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && this.jumpcounter < 1) {
            float force = body.getMass() * 12;
            body.applyLinearImpulse(new Vector2(0,force),body.getPosition(),true);
            this.jumpcounter++;
            jumpingSound.play(10f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gameScreen.setProjectile(this.direction);
        }

        if(body.getLinearVelocity().y == 0) {
            this.jumpcounter = 0;
        }

        if(body.getLinearVelocity().x == 0) {
            this.walkDuration = 0;
            this.walkingSound.stop();
        }

        body.setLinearVelocity(velX*speed, body.getLinearVelocity().y);
    }

    @Override
    public void update() {
        super.x = body.getPosition().x * PPM;   // x i y muszą być aktualizowane do aktualnej pozycji body
        super.y = body.getPosition().y * PPM;
        this.checkUserInput();
        if(this.walkDuration > 0)
            this.walkDuration--;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    public void eat() {
        this.power++;
        eatingSound.play(5.0f);
    }

    public void hit() {
        this.power-=10;
        hitSound.play(0.5f);
    }
    public void eat(int l) {
        this.power+=l;
        eatingSound.play(5.0f);
    }

    public void hit(int l) {
        this.power-=l;

    }
    public int getPower() {
        return this.power;
    }

    public boolean isDirection() {
        return direction;
    }
}
