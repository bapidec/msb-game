package objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import static Helper.Constants.PPM;

public class BigSpider extends Enemy{

    private int power = 20;

    public BigSpider(int locX, int locY, World world) {
        super(Enemies.BIG_SPIDER, locX, locY, world);
        super.texture = Enemies.BIG_SPIDER.getTexture();
        super.speed = 2f;
    }
    public void render(SpriteBatch batch){
        super.render(batch);
        batch.draw(Enemies.BIG_SPIDER.getTexture(), this.getBody().getPosition().x*PPM- Enemies.BIG_SPIDER.getWidth()/2,
                this.getBody().getPosition().y*PPM - Enemies.BIG_SPIDER.getHeight()/2);
    }

    public int getPower(){
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
