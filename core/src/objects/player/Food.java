package objects.player;

import Helper.BodyHelperService;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Food{

    private Body body;
    private String name;

    private boolean isEaten;

    public Food(World world, String name, float x, float y, float width, float height) {
        this.name = name;
        this.isEaten = false;
        this.body = BodyHelperService.createBody(x, y, width, height, true, world);
        Array<Fixture> fixtures = this.body.getFixtureList();
        for(Fixture fix : fixtures) {
            fix.setUserData(this);
        }
    }

    public void eaten() {
        this.isEaten = true;
    }

    public Body getBody() {
        return this.body;
    }
    public void setBody(Body newBody) {
        this.body = newBody;
    }
    public boolean getEaten() { return this.isEaten;}
}