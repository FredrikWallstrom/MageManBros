package com.mygdx.game.entity;


import com.badlogic.gdx.graphics.g2d.Animation;
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
    private Animation megaManRun;
    private boolean runningRight;
    private float stateTimer;

    // Texture region that will be applied for the image to the player
    private TextureRegion megaManStand;
    private TextureRegion megaManJump;


    public Player(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;


        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 2; i < 8; i++) {
            frames.add(new TextureRegion(getTexture(), i*38, 1, 36 ,52));
        }
        megaManRun = new Animation(0.1f, frames);
        frames.clear();

        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MageManBros.PPM, 32 / MageManBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(7 / MageManBros.PPM);

        fdef.shape = shape;
        body.createFixture(fdef);

        // Attach the image to the player, depending on the STATE
        megaManJump = new TextureRegion(getTexture(), 39, 1, 33, 56);
        megaManStand = new TextureRegion(getTexture(), 478, 1, 37, 53);
        setBounds(0, 0, 16 / MageManBros.PPM, 16 / MageManBros.PPM);
        setRegion(megaManStand);


    }

    public void update(float dt){
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = megaManJump;
                break;
            case RUNNING:
                region = megaManRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = megaManStand;
                break;
            default:
                region = megaManStand;
                break;

        }

        if((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }else if((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if(body.getLinearVelocity().y != 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }
        else if(body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else{
            return State.STANDING;
        }
    }

    public Body getBody() {
        return body;
    }
}
