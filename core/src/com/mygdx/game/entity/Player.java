package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Game;

import static com.mygdx.game.MageManBros.PPM;

/**
 * Subclass to Entity. It will represent the player in the game and their behavior.
 */

public class Player extends Entity {
    // Enum that represent all available states for the player.
    private enum State {JUMPING, STANDING, RUNNING, CROUCHING, SHOOTING}

    private State currentState;
    private State previousState;

    private Body body;
    private Animation megaManRun;
    private boolean runningRight;
    private float stateTimer;

    // Texture region that will be applied for the image to the player.
    private TextureRegion megaManStand;
    private TextureRegion megaManJump;
    private TextureRegion megaManCrouch;
    private TextureRegion megaManShoot;

    public Player(World world, TiledMap map, Rectangle bounds, Game game) {
        super(world, map, bounds, game);

        // Player will standing to the right at initializing point.
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // Array there we can build up animation frames.
        Array<TextureRegion> frames = new Array<TextureRegion>();

        /* TODO The animation is working, Need to remove the third picture since it is to big
        TODO and will make the animation to look aweful. Dont got time with that now, will do it later.*/
        // Build up an animation, add all pictures to the frame.
        for (int i = 0; i < 6; i++)
            if (i > 3) frames.add(new TextureRegion(getTexture(), (40 * (i - 4)) + 434, 3, 32, 52));
            else if (i < 3) frames.add(new TextureRegion(getTexture(), (40 * i) + 268, 3, 32, 52));

        megaManRun = new Animation(0.1f, frames);
        frames.clear();

        // Create Bodydef.
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / PPM, 32 / PPM);

        // Attach the image to the player, depending on the STATE
        megaManJump = new TextureRegion(getTexture(), 62, 3, 33, 56);
        megaManStand = new TextureRegion(getTexture(), 762, 3, 37, 53);
        megaManShoot = new TextureRegion(getTexture(), 712, 3, 42, 53);
        megaManCrouch = new TextureRegion(getTexture(), 3, 3, 51, 60);
        megaManCrouch.setRegionHeight(50);
        megaManCrouch.setRegionWidth(50);

        setBounds(0, 0, 16 / PPM, 16 / PPM);
        body = defineBody(bdef, 7);
        body.setUserData(this);
    }

    /**
     * Overridden function from the superclass. This function will be called by the game loop
     * every time the game should be updated.
     * It will just set the position of the picture image.
     * @param dt is the time between the start of the previous and the start of the current call
     *           to render().
     */
    @Override
    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    /**
     * Helper function that will return the right region for the update function.
     * @param dt dt is the time between the start of the previous and the start of the current call
     *           to render().
     * @return the right frame that is currently represent the player.
     */
    private TextureRegion getFrame(float dt) {
        TextureRegion region;
        // Check with state the player is in.
        switch (getState()) {
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

        // Check if the player is running right or left, and flip the picture so it corresponds to
        // the right option.
        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        // If currentState equal to previousState, increment stateTimer, else start it over.
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    /**
     * Help function that will check which state the player is in for the moment.
     * @return the state for the player.
     */
    private State getState() {
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
