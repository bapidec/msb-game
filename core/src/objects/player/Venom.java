package objects.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import static Helper.Constants.PPM;

public class Venom extends GameEntity{
    private Texture texture;
    public boolean exists=true;
    public Venom(float width, float height, Body body, boolean direction, Texture texture) {
        super(width, height, body);
        super.speed = 15f;
        this.texture = texture;
        if(!direction)
            super.velX += 1;
        else
            super.velX += -1;
        super.body.setLinearVelocity(super.velX*speed, super.body.getLinearVelocity().y);
        Array<Fixture> fixtures = this.body.getFixtureList();
        for(Fixture fix : fixtures)
            fix.setUserData(this);
    }
    @Override
    public void update() {
        super.x = body.getPosition().x * PPM;     // x i y muszą być aktualizowane do aktualnej pozycji body
        super.y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.body.getPosition().x*PPM,
                this.body.getPosition().y*PPM);
    }

    public void gone() {
        this.exists=false;
    }

}
