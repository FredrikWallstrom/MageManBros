package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.game.Game;

/**
 * Created by fredr on 2016-11-05.
 */

public abstract class Entity extends Sprite {
  /*  private Sprite sprite;
    private Vector2 position;
    private Vector2 size;
    private World world;
    private TiledMap map;
    private Rectangle bounds;
    private TiledMapTile tile;
    private Body body;*/

    protected World world;
    protected TiledMap map;
    protected TiledMapRenderer tile;
    protected Rectangle bounds;

    public Entity(World world, TiledMap map, Rectangle bounds, Game game) {
        super(game.getAtlas().findRegion("standRight"));
        this.world = world;
        this.map = map;
        this.bounds = bounds;
    }

    public void update(float dt) {
    }


    /*
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

    */
}
