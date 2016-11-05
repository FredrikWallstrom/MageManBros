package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by fredr on 2016-11-05.
 */

public class Player extends MovableEntity {
    private final static int ALLOWED_CONSECUTIVE_JUMPS = 2;
    private int score;
    private float powerUpTimer;
    private int jumpsCount = ALLOWED_CONSECUTIVE_JUMPS;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
        super(sprite, position, size, velocity, acceleration);
        score = 0;
        this.powerUpTimer = 0;
    }

    @Override public void doAction(GameObject type) {
    }

    @Override public void update(float dt) {
        super.update(dt);
    }

    public void setPowerUpTimer(float time) {
        powerUpTimer = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    @Override public GameObject getGameObjectType() {
        return GameObject.PLAYER;
    }
}
