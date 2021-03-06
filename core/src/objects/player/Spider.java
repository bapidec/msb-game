package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static Helper.Constants.PPM;

public class Spider extends Enemy{
    public Spider(int locX, int locY, World world) {
        super(Enemies.SPIDER, locX, locY, world);
        super.speed = 3f;
        super.texture = Enemies.SPIDER.getTexture();
    }
    public void render(SpriteBatch batch){
        super.render(batch);
        batch.draw(Enemies.SPIDER.getTexture(), this.getBody().getPosition().x*PPM- Enemies.SPIDER.getWidth()/2,
                this.getBody().getPosition().y*PPM - Enemies.SPIDER.getHeight()/2);
    }
}