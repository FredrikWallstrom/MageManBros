package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
 * Created by fredr on 2016-11-05.
 */

public abstract class Game {
    // Tiled map variables
    protected World world;
    private TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    // Camera and Viewport variables
    protected OrthographicCamera gameCam;

    // The only player in the game
    public static Player player;
    protected Shoot shoot;

    // List of all objects in the game
    private ArrayList<Entity> gameObjects;

    // TextureAtlas for ths picture sheets
    private TextureAtlas atlas;

    // ContactListener
    private MyContactListener myContactListener = new MyContactListener();

    private boolean removeBody = false;
    private Array<Body> bodiesToRemove = new Array<Body>();


    public Game(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        this.world = world;
        this.map = map;
        this.gameCam = gameCam;
        this.renderer = renderer;
        this.gameObjects = new ArrayList<Entity>();
        this.atlas = new TextureAtlas("MegaMan.txt");

        world.setContactListener(myContactListener);
        createPlayer();
    }


    private void createPlayer() {
        player = new Player(world, map, new Rectangle(32, 32, 32, 32), this);

        gameObjects.add(player);
    }

    public Iterable<Entity> getGameObjects() {
        return gameObjects;
    }

    public void updateGame(float delta, SpriteBatch batch) {


        world.step(1/60f, 6, 2);

        if(removeBody){
            for (Body body : bodiesToRemove) {
                gameObjects.remove(body.getUserData());
                world.destroyBody(body);
            }
            bodiesToRemove.clear();
        }



        for (Entity entity : getGameObjects()) {
            entity.update(delta);

        }
        gameCam.position.x = player.getBody().getPosition().x;
        gameCam.update();

        renderer.setView(gameCam);

        // Render the attached fixture on the body
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        for (Entity entity: getGameObjects()) {
            entity.draw(batch);
        }
        batch.end();
    }

    protected void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.getBody().applyLinearImpulse(new Vector2(0, 04), player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getBody().getLinearVelocity().x <= 2){
            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getBody().getLinearVelocity().x >= -2){
            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            createShoot();
        }
        }

    private void createShoot() {
        Shoot shoot = new Shoot(world, map, new Rectangle(32, 32, 32, 32), this);
        gameObjects.add(shoot);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }



    private class MyContactListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            // A shoot is colliding with a wall.
            if ((fixtureA.getBody().getUserData() == null || fixtureB.getBody().getUserData() == null)){

                if(fixtureA.getBody().getUserData() instanceof Shoot){
                    removeBody = true;
                    bodiesToRemove.add(fixtureA.getBody());

                }else if(fixtureB.getBody().getUserData() instanceof Shoot) {
                    removeBody = true;
                    bodiesToRemove.add(fixtureB.getBody());


                }else removeBody = false;
            }else removeBody = false;
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

