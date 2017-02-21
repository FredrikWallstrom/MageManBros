package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.game.Game;

/**
 * Class that represent all available entity types.
 * An entity could either be a Player, a Shoot or an Enemy.
 */

public abstract class Entity extends Sprite {

    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;

    public Entity(World world, TiledMap map, Rectangle bounds, Game game) {
        super(game.getAtlas().findRegion("standRight"));
        this.world = world;
        this.map = map;
        this.bounds = bounds;
    }


    /**
     * Update function that can be overridden by the subclasses.
     * @param dt is the time between the start of the previous and the start of the current call
     *           to render().
     */
    public void update(float dt) {
    }
}
