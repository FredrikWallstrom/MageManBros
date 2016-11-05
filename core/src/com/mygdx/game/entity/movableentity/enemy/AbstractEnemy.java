package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by fredr on 2016-11-05.
 */

public abstract class AbstractEnemy extends MovableEntity {

    protected AbstractEnemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
        super(sprite, position, size, velocity, acceleration);
    }

    @Override public void doAction(GameObject type) {}

    @Override public void update(float dt) {}
}
