package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.game.Game;
import java.util.HashMap;
import java.util.Map;

import static com.mygdx.game.MageManBros.PPM;

/**
 * Created by fredrikwallstrom on 2017-01-10.
 */

public class Shoot extends Entity {

    // Body for the shoot entity.
    private Body body;

    // Array to store if the active shoots is to the left or right. True if right, false if left.
    private Map<Shoot, Boolean> activeShootsWithDirection = new HashMap<Shoot, Boolean>();

    /**
     * Constructor to initialize the shoot object.
     * Creates a circle body for the shoot, with no gravity.
     */
    public Shoot(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);

        BodyDef bdef = new BodyDef();

        // Check if the player is running left or right, to put the object at the right place.
        if (Game.player.isRunningRight()){
            activeShootsWithDirection.put(this, true);
            bdef.position.set(Game.player.getX() + Game.player.getWidth() + (2 / PPM), Game.player.getY() + (Game.player.getHeight()/2));
        }else{
            activeShootsWithDirection.put(this, false);
            bdef.position.set(Game.player.getX() - (2 / PPM), Game.player.getY() + (Game.player.getHeight()/2));
        }

        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2 / PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setGravityScale(0);

        setBounds(0, 0, 4 / PPM, 4 / PPM);
        body.setUserData(this);
    }

    @Override
    public void update(float dt) {
        setRegion(new TextureRegion(getTexture(), 694, 3, 10, 9));
        // Go through the map with the active shoots, see if they should be renderer to left or right.
        for (Map.Entry<Shoot, Boolean> entry : activeShootsWithDirection.entrySet()) {
            if(entry.getValue()){
                setPosition(body.getPosition().x - getWidth() / 2 , body.getPosition().y - getHeight() / 2);
                body.setLinearVelocity(2.5f, 0);
            }else{
                setPosition(body.getPosition().x, body.getPosition().y - getHeight() / 2);
                body.setLinearVelocity(-2.5f, 0);
            }
        }
    }

    @Override
    public void stopRenderEntity(){
        activeShootsWithDirection.remove(this);

    }




}
