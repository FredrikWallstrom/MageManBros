package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by fredr on 2016-11-05.
 */

public abstract class Entity {
    private Sprite sprite;
    private Vector2 position;
    private Vector2 size;


    public abstract GameObject getGameObjectType();

    protected Entity(Sprite sprite, Vector2 position, Vector2 size) {
        this.sprite = sprite;
        this.position = position;
        this.size = size;
        sprite.setPosition(position.x, position.y);
        sprite.setSize(size.x, size.y);
    }

    public void draw(Batch batch){
        sprite.draw(batch);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(final Vector2 pos) {
        this.position = pos;
        sprite.setPosition(position.x, position.y);
    }

    public void setPositionX(float posX){
        this.position = new Vector2(posX, position.y);
        sprite.setPosition(posX, position.y);
    }

    public void setPositionY(float posY){
        this.position = new Vector2(position.x, posY);
        sprite.setPosition(position.x, posY);
    }

    public float getWidth(){
        return size.x;
    }

    public float getHeight(){
        return size.y;
    }

    public void setSize(final Vector2 size) {
        this.size = size;
        sprite.setSize(size.x, size.y);
    }
}
