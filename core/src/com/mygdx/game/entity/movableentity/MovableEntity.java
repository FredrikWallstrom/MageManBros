package com.mygdx.game.entity.movableentity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;

/**
 * Created by fredr on 2016-11-05.
 */

public abstract class MovableEntity extends Entity {

    protected Vector2 velocity;
    protected Vector2 acceleration;
    protected int hitPointsLeft;

    public abstract void doAction(GameObject type);

    protected MovableEntity(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration) {
        super(sprite, position, size);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void update(float dt) {}

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(final Vector2 velocity) {
        this.velocity = velocity;
    }

    public void moveLeft(final float dt) {
        setPositionX(getPosition().x - velocity.x * dt);
    }

    public void moveRight(final float dt) {
        setPositionX(getPosition().x + velocity.x * dt);
    }



}
