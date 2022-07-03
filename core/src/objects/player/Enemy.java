package objects.player;

import Helper.BodyHelperService;
import Helper.Constants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameScreen;

import static Helper.Constants.PPM;

public abstract class Enemy extends GameEntity{
    protected Texture texture;
    protected boolean isAlife = true;

    public Enemy(Enemies type, int locX, int locY, World world) {
        super(type.getWidth(), type.getHeight(), BodyHelperService.createBody(locX, locY,
                type.getWidth(), type.getHeight(), false, world, Constants.BIT_SPIDER, (short)(Constants.BIT_PLAYER | Constants.BIT_PLATFORM | Constants.BIT_PROJECTILE)));
        if(GameScreen.rand.nextInt(2) == 0){
            super.velX=0.5f;
        }else{
            super.velX=-0.5f;
        }
    }

    @Override
    public void update() {
        super.x = body.getPosition().x * PPM;     // x i y muszą być aktualizowane do aktualnej pozycji body
        super.y = body.getPosition().y * PPM;
        body.setLinearVelocity(velX*speed, body.getLinearVelocity().y);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(super.getBody().getPosition().y<0){
            this.isAlife = false;
        }
    }

    public boolean isAlife(){
        return this.isAlife;
    }

    public void killEnemy(){
        this.isAlife = false;
    }

    public Body getBody(){
        return super.body;
    }
    public void setBody(Body newBody) {
        this.body = newBody;
    }
}