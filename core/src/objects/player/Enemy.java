package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends GameEntity{
    protected Texture texture;

    public Enemy(Enemies type, int locX, int locY, World world) {
        super(type.getWidth(), type.getHeight(), BodyHelperService.createBody(locX, locY,
                type.getWidth(), type.getHeight(), false, world));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
