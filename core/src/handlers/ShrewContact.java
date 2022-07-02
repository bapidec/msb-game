package handlers;

import com.badlogic.gdx.physics.box2d.*;
import objects.player.Food;
import objects.player.Player;

public class ShrewContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture objA = (Fixture) contact.getFixtureA();
        Fixture objB = (Fixture) contact.getFixtureB();

        if(objA == null || objB == null) return;
        if(objA.getUserData() == null || objB.getUserData() == null) return;

        if(isPlayerContact(objA, objB)) {
            Food food = (Food) objB.getUserData();
            food.eaten();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private boolean isPlayerContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Player && b.getUserData() instanceof Food);
    }
}