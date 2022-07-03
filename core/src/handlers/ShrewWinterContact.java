package handlers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameScreen;
import jdk.tools.jlink.internal.Platform;
import objects.player.*;

public class ShrewWinterContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture objA = (Fixture) contact.getFixtureA();
        Fixture objB = (Fixture) contact.getFixtureB();

        if(objA == null || objB == null) return;
        if(objA.getUserData() == null || objB.getUserData() == null) return;

        if(isProjectileEnemyContact(objA, objB)) {
            Venom venom = (Venom) objA.getUserData();
            Enemy enemy = (Enemy) objB.getUserData();
            if(enemy instanceof Spider) {
                Spider s = (Spider) objB.getUserData();
                GameScreen.player.eat(5);
                s.killEnemy();
            }
            else if(enemy instanceof BigSpider) {
//                Larva s = (Larva) objB.getUserData();
//                GameScreen.player.hit(2);
//                s.killEnemy();
            }
            venom.gone();
        }

        if(isEnemyProjectileContact(objA, objB)) {
            Venom venom = (Venom) objB.getUserData();
            Enemy enemy = (Enemy) objA.getUserData();
            if(enemy instanceof Spider) {
                Spider s = (Spider) objA.getUserData();
                GameScreen.player.eat(5);
                s.killEnemy();
            }
            else if(enemy instanceof BigSpider) {
//                Larva s = (Larva) objA.getUserData();
//                GameScreen.player.hit(2);
//                s.killEnemy();
            }
            venom.gone();
        }

        if(isPlayerSpiderContact(objA, objB)) {
            Player player = (Player) objA.getUserData();
            if(objB.getUserData() instanceof Spider) {
                Spider s = (Spider) objB.getUserData();
                player.hit();
                s.killEnemy();
            }
            else if(objB.getUserData() instanceof BigSpider) {
                BigSpider s = (BigSpider) objB.getUserData();
                player.hit(25);
            }
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

    private boolean isProjectileEnemyContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Venom && b.getUserData() instanceof Enemy);
    }
    private boolean isEnemyProjectileContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Enemy && b.getUserData() instanceof Venom);
    }

    private boolean isPlayerSpiderContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof Player && b.getUserData() instanceof Enemy);
    }
}
