package objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static Helper.Constants.PPM;

public class Player extends GameEntity {

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;     // x i y muszą być aktualizowane do aktualnej pozycji body
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
