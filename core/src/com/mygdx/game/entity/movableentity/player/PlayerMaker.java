package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by fredr on 2016-11-05.
 */

public class PlayerMaker {
    private int width;
    private int height;

    public PlayerMaker(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Player createPlayer(){
        return new Player(new Sprite(new Texture(Gdx.files.internal("BlackButton-Active.png"))), new Vector2(0, 0),
                new Vector2(width, height), new Vector2(0, 0), new Vector2(0, 0));
    }
}
