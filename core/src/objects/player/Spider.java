package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Spider extends Enemy{
    public Spider(int locX, int locY, World world) {
        super(Enemies.SPIDER, locX, locY, world);
        super.speed = 3f;
        super.texture = Enemies.SPIDER.getTexture();
    }
}
