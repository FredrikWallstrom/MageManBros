package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MageManBros;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;


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
    protected Player player;

    // List of all objects in the game
    private ArrayList<Entity> gameObjects;

    private TextureAtlas atlas;

    public Game(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        this.world = world;
        this.map = map;
        this.gameCam = gameCam;
        this.renderer = renderer;
        this.gameObjects = new ArrayList<Entity>();
        this.atlas = new TextureAtlas("MegaMan.txt");

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
        player.update(delta);
        gameCam.position.x = player.getBody().getPosition().x;
        gameCam.update();

        renderer.setView(gameCam);

        // Render the attached fixture on the body
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        player.draw(batch);
        batch.end();
    }

    public TextureAtlas getAtlas() {
        return atlas;
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
    }




}
