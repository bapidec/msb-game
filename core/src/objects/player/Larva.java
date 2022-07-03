package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static Helper.Constants.PPM;

public class Larva extends Enemy{
    public Larva(int locX, int locY, World world) {
        super(Enemies.LARVA, locX, locY, world);
        super.speed = Enemies.LARVA.getSpeed();
        super.texture = Enemies.LARVA.getTexture();
    }
    public void render(SpriteBatch batch){
        super.render(batch);
        batch.draw(Enemies.LARVA.getTexture(), this.getBody().getPosition().x*PPM- Enemies.LARVA.getWidth()/2,
                this.getBody().getPosition().y*PPM - Enemies.LARVA.getHeight()/2);
    }

}