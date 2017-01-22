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
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.Shoot;
import com.mygdx.game.tools.MyContactListener;


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
}
