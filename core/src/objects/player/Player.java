package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static Helper.Constants.PPM;

public class Player extends GameEntity {

    private int jumpcounter=0;
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
    }

    private void checkUserInput(){
//        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
//            super.velX = -1;
//        }else if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
//            super.velX = 1;
//        }else if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
//            super.velY = 1;
//        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
//            super.velY = -1;
//        }else{
//            super.velX=0;
//            super.velY=0;
//        }
//
//        body.setLinearVelocity(super.velX+super.speed, super.body.getLinearVelocity().y);
        super.velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            super.velX += 1;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            super.velX += -1;
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && this.jumpcounter <1) {
            float force = body.getMass() * 18;
            body.applyLinearImpulse(new Vector2(0,force),body.getPosition(),true);
            this.jumpcounter++;
        }

        if(body.getLinearVelocity().y == 0) {
            jumpcounter = 0;
        }

        body.setLinearVelocity(velX*speed, body.getLinearVelocity().y);
    }

    @Override
    public void update() {
        super.x = body.getPosition().x * PPM;     // x i y muszą być aktualizowane do aktualnej pozycji body
        super.y = body.getPosition().y * PPM;
        this.checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
