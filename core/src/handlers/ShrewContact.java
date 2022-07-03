package handlers;

import com.badlogic.gdx.physics.box2d.*;
import objects.player.*;

public class ShrewContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture objA = (Fixture) contact.getFixtureA();
        Fixture objB = (Fixture) contact.getFixtureB();

        if(objA == null || objB == null) return;
        if(objA.getUserData() == null || objB.getUserData() == null) return;

        if(isPlayerFoodContact(objA, objB)) {
            Player player = (Player) objA.getUserData();
            Food food = (Food) objB.getUserData();
            food.eaten();
            player.eat();
        }

        if(isProjectileEnemyContact(objA, objB)) {
            Venom venom = (Venom) objA.getUserData();
            Enemy enemy = (Enemy) objB.getUserData();
            //enemy.killEnemy();
            venom.gone();
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

    private boolean isPlayerFoodContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Player && b.getUserData() instanceof Food);
    }

    private boolean isProjectileEnemyContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Venom && b.getUserData() instanceof Enemy);
    }
    private boolean isProjectileContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Venom || b.getUserData() instanceof Venom);
    }
}
