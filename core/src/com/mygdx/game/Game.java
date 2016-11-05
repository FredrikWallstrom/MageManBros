package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.PlayerMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fredr on 2016-11-05.
 */

public class Game {
    private ArrayList<Entity> gameObjects;

    private List<MovableEntity> objectsToRemove;

    private Vector2 playerSpawnPoint;

    private Player player;
    private PlayerMaker playerMaker;

    private float enemySpawnTimer;
    private float powerUpSpawnTimer;

    private boolean gameOver;

    public Game() {
        this.gameObjects = new ArrayList<Entity>();
        this.playerMaker = new PlayerMaker(40, 40);
        createPlayer();
    }

    private void createPlayer() {
        this.player = playerMaker.createPlayer();
        gameObjects.add(player);
    }

    public Iterable<Entity> getGameObjects() {
        return gameObjects;
    }

    public void updateGame(float delta) {
    }

}
