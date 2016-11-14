package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Game;

/**
 * Created by fredr on 2016-11-13.
 */

public class Player extends Entity{

    private Body body;

    public Player(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Game.PPM, 32 / Game.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Game.PPM);

        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public Body getBody() {
        return body;
    }
}
