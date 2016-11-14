package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;

import java.util.ArrayList;

/**
 * Created by fredr on 2016-11-05.
 */

public class Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;

    // Tiled map variables
    private World world;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Camera and Viewport variables
    private OrthographicCamera gameCam;

    private Player player;
    private ArrayList<Entity> gameObjects;

    public Game(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        this.world = world;
        this.map = map;
        this.gameCam = gameCam;
        this.renderer = renderer;
        this.gameObjects = new ArrayList<Entity>();
        createPlayer();
    }

    private void createPlayer() {
        player = new Player(world, map, new Rectangle(32, 32, 32, 32));
        gameObjects.add(player);
    }

    public Iterable<Entity> getGameObjects() {
        return gameObjects;
    }

    public void updateGame(float delta) {
        handleInput(delta);

        world.step(1/60f, 6, 2);
        gameCam.position.x = player.getBody().getPosition().x;

        gameCam.update();
        renderer.setView(gameCam);
    }

    private void handleInput(float dt){
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
