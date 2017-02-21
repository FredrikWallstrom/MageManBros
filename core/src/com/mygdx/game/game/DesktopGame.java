package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Subclass that will be used if the game is running on desktop environment.
 */

public class DesktopGame extends Game {

    /**
     * Constructor, just calling superclass constructor.
     */
    public DesktopGame(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        super(world, map, gameCam, renderer);
    }

    /**
     * First it will handle the inputs from the keyboard.
     * Secondly it will call the superclass method for the game loop.
     * @param delta is the time between the start of the previous and the start of the current call
     *           to render().
     * @param batch used to draw the all gameObjects.
     */
    @Override
    public void updateGame(float delta, SpriteBatch batch){
        handleInput();
        super.updateGame(delta, batch);
    }

    /**
     * Function will handle all the inputs from the keyboard.
     * It will check which button that was pressed and do the appropriate thing to do.
     */
    private void handleInput(){
        // Jump.
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.getBody().applyLinearImpulse(new Vector2(0, 04), player.getBody().getWorldCenter(), true);
        }
        // Run right.
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getBody().getLinearVelocity().x <= 2){
            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        // Run left.
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getBody().getLinearVelocity().x >= -2){
            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        // Shoot.
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            createShoot();
        }
    }
}
