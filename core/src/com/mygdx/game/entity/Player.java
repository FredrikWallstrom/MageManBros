package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MageManBros;
import com.mygdx.game.game.Game;

/**
 * Created by fredr on 2016-11-13.
 */

public class Player extends Entity {
    public enum State {JUMPING, STANDING, RUNNING, CROUCHING, SHOOTING}

    public State currentState;
    public State previousState;

    private Body body;
    private Animation megaManRun;
    protected boolean runningRight;
    private float stateTimer;

    /* Texture region that will be applied for the image to the player*/
    private TextureRegion megaManStand;
    private TextureRegion megaManJump;
    private TextureRegion megaManCrouch;
    private TextureRegion megaManShoot;

    public Player(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        /* TODO The animation is working, Need to remove the 3 picture since it is to big
        TODO and will make the animation to look aweful. Dont got time with that now, will do it later.*/
        for (int i = 0; i < 6; i++)
            if (i > 3) frames.add(new TextureRegion(getTexture(), (40 * (i - 4)) + 434, 3, 32, 52));
            else if (i < 3) frames.add(new TextureRegion(getTexture(), (40 * i) + 268, 3, 32, 52));

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

        /* Attach the image to the player, depending on the STATE */
        megaManJump = new TextureRegion(getTexture(), 62, 3, 33, 56);
        megaManStand = new TextureRegion(getTexture(), 762, 3, 37, 53);
        megaManShoot = new TextureRegion(getTexture(), 712, 3, 42, 53);
        megaManCrouch = new TextureRegion(getTexture(), 3, 3, 51, 60);
        megaManCrouch.setRegionHeight(50);
        megaManCrouch.setRegionWidth(50);

        setBounds(0, 0, 16 / MageManBros.PPM, 16 / MageManBros.PPM);
        body.setUserData(this);
    }

    @Override
    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case SHOOTING:
                region = megaManShoot;
                break;
            case JUMPING:
                region = megaManJump;
                break;
            case RUNNING:
                region = megaManRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = megaManStand;
                break;
            case CROUCHING:
                region = megaManCrouch;
                break;
            default:
                region = megaManStand;
                break;

        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            return State.SHOOTING;
        } else if (body.getLinearVelocity().y != 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return State.RUNNING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return State.RUNNING;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            return State.CROUCHING;
        } else if (body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else return State.STANDING;
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    public Body getBody() {
        return body;
    }
}
