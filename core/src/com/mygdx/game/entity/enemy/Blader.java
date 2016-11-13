package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by fredr on 2016-11-05.
 */

public class Blader extends AbstractEnemy {
    public Blader(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }

    /*
    private final static int ENEMY_WIDTH = 32;
    private final static int ENEMY_HEIGHT = 32;

    protected Blader(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
        super(sprite, position, size, velocity, acceleration);
    }

    public static Vector2 getEnemySize() {
        return new Vector2(ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    @Override public GameObject getGameObjectType() {
        return GameObject.ENEMY;
    }

    */
}
