package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MageManBros;

/**
 * This class will build the world. It will go through the map objects and build them.
 */

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        // Body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // Create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MageManBros.PPM, (rect.getY() + rect.getHeight() / 2) / MageManBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MageManBros.PPM, (rect.getHeight() / 2) / MageManBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Create pipe bodies/fixtures
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MageManBros.PPM, (rect.getY() + rect.getHeight() / 2) / MageManBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MageManBros.PPM, (rect.getHeight() / 2) / MageManBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Create brick bodies/fixtures
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MageManBros.PPM, (rect.getY() + rect.getHeight() / 2) / MageManBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MageManBros.PPM, (rect.getHeight() / 2) / MageManBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MageManBros.PPM, (rect.getY() + rect.getHeight() / 2) / MageManBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MageManBros.PPM, (rect.getHeight() / 2) / MageManBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
