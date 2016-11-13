package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;

/**
 * Created by fredr on 2016-11-05.
 */

public abstract class AbstractEnemy extends Entity {
    public AbstractEnemy(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }


    /*
    protected AbstractEnemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
        super(sprite, position, size, velocity, acceleration);
    }

    @Override public void doAction(GameObject type) {}

    @Override public void update(float dt) {}

    */
}
