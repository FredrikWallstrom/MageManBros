package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MageManBros;
import com.mygdx.game.game.Game;

/**
 * Created by fredrikwallstrom on 2017-01-10.
 */

public class Shoot extends Entity {

    Body body;

    public Shoot(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);

        BodyDef bdef = new BodyDef();

        bdef.position.set(Game.player.getX() + Game.player.getWidth(), Game.player.getY() + (Game.player.getHeight()/2));
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2 / MageManBros.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setGravityScale(0);

        setBounds(0, 0, 4 / MageManBros.PPM, 4 / MageManBros.PPM);
    }

    @Override
    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(new TextureRegion(getTexture(), 694, 3, 10, 9));
        body.setLinearVelocity(2.5f, 0);
    }
}
