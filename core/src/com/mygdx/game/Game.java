package com.mygdx.game;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;

import java.util.ArrayList;

/**
 * Created by fredr on 2016-11-05.
 */

public class Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;

    private World world;
    private TiledMap map;

    private Player player;
    private ArrayList<Entity> gameObjects;

    public Game(World world, TiledMap map) {
        this.world = world;
        this.map = map;
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



}
