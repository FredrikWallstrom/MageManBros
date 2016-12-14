package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MageManBros;
import com.mygdx.game.game.Game;

/**
 * Created by fredr on 2016-11-13.
 */

public class Player extends Entity{
    public enum State { JUMPING, STANDING, RUNNING }
    public State currentState;
    public State previousState;

    private Body body;
    private Animation megaManJump;
    private Animation megaManRun;
    private boolean runningRight;
    private float stateTimer;

    // Texture region that will be applied for the image to the player
    private TextureRegion megaManStand;


    public Player(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MageManBros.PPM, 32 / MageManBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(7 / MageManBros.PPM);

        fdef.shape = shape;
        body.createFixture(fdef);

        // Attach the image to the player
        megaManStand = new TextureRegion(getTexture(), 79, 53, 37, 53);
        setBounds(0, 0, 16 / MageManBros.PPM, 16 / MageManBros.PPM);
        setRegion(megaManStand);


    }

    public void update(float dT){
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }

    public Body getBody() {
        return body;
    }
}
