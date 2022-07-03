package objects.player;

import com.badlogic.gdx.graphics.Texture;

public enum Enemies {
    SPIDER(32, 32, 2f, new Texture("spider.png")),
    BIG_SPIDER(128,128,2f, new Texture("big_spider2.png")),
    LARVA(32,32,0, new Texture("larva.png"));

    private int w, h;
    private float speed;
    private Texture texture;
    Enemies(int w, int h, float speed, Texture texture){
        this.speed = speed;
        this.w = w;
        this.h = h;
        this.texture = texture;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHeight() {
        return h;
    }
    public int getWidth(){
        return w;
    }
    public Texture getTexture(){
        return texture;
    }
}