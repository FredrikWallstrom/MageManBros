package com.mygdx.game.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.Shoot;

import java.util.ArrayList;

/**
 * Superclass that will act like a game engine.
 * This is a superclass since I will make the game available on both Android and desktop environment.
 * There are different implementations for these two environments, but the most is the same.
 */

public abstract class Game {
    // Tiled map variables
    protected World world;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Camera and Viewport variables
    private OrthographicCamera gameCam;

    // The only player in the game
    public static Player player;
    protected Shoot shoot;

    // List of all objects in the game
    private ArrayList<Entity> gameObjects;

    // TextureAtlas for ths picture sheets
    private TextureAtlas atlas;

    // ContactListener
    private MyContactListener myContactListener = new MyContactListener();

    // Boolean and array used to remove bodies that should be deleted from game.
    private boolean removeBody = false;
    private Array<Body> bodiesToRemove = new Array<Body>();

    /**
     * Constructor.
     */
    public Game(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        this.world = world;
        this.map = map;
        this.gameCam = gameCam;
        this.renderer = renderer;
        this.gameObjects = new ArrayList<Entity>();
        this.atlas = new TextureAtlas("MegaMan.txt");

        // Initialize contactListener and create one player.
        world.setContactListener(myContactListener);
        createPlayer();
    }

    /**
     * Creates one player and add the player object to the list with all gameObjects.
     */
    private void createPlayer() {
        player = new Player(world, map, new Rectangle(32, 32, 32, 32), this);
        gameObjects.add(player);
    }

    /**
     *
     * @return the list with all available gameObjects in the game in this moment.
     */
    private Iterable<Entity> getGameObjects() {
        return gameObjects;
    }

    /**
     * This function is the is the game loop.
     * It will forward the word and check for common updates in the game since last time.
     * @param delta is the time between the start of the previous and the start of the current call
     *           to render().
     * @param batch used to draw the all gameObjects.
     */
    public void updateGame(float delta, SpriteBatch batch) {
        world.step(1/60f, 6, 2);

        // Check if there is any objects to remove from the world, after that clear the array.
        if(removeBody){
            for (Body body : bodiesToRemove) {
                gameObjects.remove(body.getUserData());
                world.destroyBody(body);
            }
            bodiesToRemove.clear();
        }

        // Go through every entity and do an update for them (render them again typically).
        for (Entity entity : getGameObjects()) {
            entity.update(delta);

        }

        // Update the camera.
        gameCam.position.x = player.getBody().getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);

        // Render the attached fixture on the body.
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        for (Entity entity: getGameObjects()) {
            entity.draw(batch);
        }
        batch.end();
    }

    /**
     * This function will create a shoot and add the shoot object to the gameObjects array.
     */
    protected void createShoot() {
        Shoot shoot = new Shoot(world, map, new Rectangle(32, 32, 32, 32), this);
        gameObjects.add(shoot);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Private class for the contactListener functions.
     * This contactListener is to detect contact between objects.
     * For example: If there is contact between one shoot and the wall,
     * beginContact function is called and the shoot needs to be removed.
     */
    private class MyContactListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            // A shoot is colliding with a wall. NULL value is representing the wall.
            if ((fixtureA.getBody().getUserData() == null || fixtureB.getBody().getUserData() == null)){

                if(fixtureA.getBody().getUserData() instanceof Shoot){
                    removeBody = true;
                    bodiesToRemove.add(fixtureA.getBody());

                }else if(fixtureB.getBody().getUserData() instanceof Shoot) {
                    removeBody = true;
                    bodiesToRemove.add(fixtureB.getBody());


                }else removeBody = false;
            }else removeBody = false;

            // TODO: Implement more contact cases. If a shoot is colliding with an enemy
            // TODO: or if an enemy is colliding with the player
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
    }
}

