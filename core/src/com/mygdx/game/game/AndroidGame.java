package com.mygdx.game.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MageManBros;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by fredr on 2016-12-10.
 */

public class AndroidGame extends Game{

    // Stage for the Android setup, to add some buttons
    private Stage stage;

    // Variables for detect if the move buttons is pressed
    private boolean isForwardTouched = false;
    private boolean isBackwardTouched = false;


    public AndroidGame(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        super(world, map, gameCam, renderer);
        renderExtraButtons();
    }

    @Override
    public void updateGame(float delta){
        stage.draw();
        if(isForwardTouched) player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        if(isBackwardTouched) player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        super.updateGame(delta);
    }

    private void renderExtraButtons(){
        TextureRegionDrawable moveRegionDrawable;
        TextureRegionDrawable jumpRegionDrawable;
        TextureRegionDrawable shootRegionDrawable;
        final ImageButton forwardButton;
        final ImageButton backwardButton;
        final ImageButton jumpButtonRight;
        final ImageButton jumpButtonLeft;
        final ImageButton shootButtonRight;
        final ImageButton shootButtonLeft;

        jumpRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenButton-Active.png"))));
        moveRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlackButton-Active.png"))));
        shootRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("RedButton-Active.png"))));
        forwardButton = new ImageButton(moveRegionDrawable);
        backwardButton = new ImageButton(moveRegionDrawable);
        jumpButtonRight = new ImageButton(jumpRegionDrawable);
        jumpButtonLeft = new ImageButton(jumpRegionDrawable);
        shootButtonRight = new ImageButton(shootRegionDrawable);
        shootButtonLeft = new ImageButton(shootRegionDrawable);

        forwardButton.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        forwardButton.setPosition(Gdx.graphics.getWidth() - MageManBros.BUTTON_SIZE_WIDTH, 0);
        backwardButton.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        backwardButton.setPosition(0, 0);
        jumpButtonRight.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        jumpButtonRight.setPosition(Gdx.graphics.getWidth() - MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT + 20);
        jumpButtonLeft.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        jumpButtonLeft.setPosition(0, MageManBros.BUTTON_SIZE_HEIGHT + 20);
        shootButtonRight.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        shootButtonRight.setPosition(Gdx.graphics.getWidth() - MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT * 2 + 40);
        shootButtonLeft.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        shootButtonLeft.setPosition(0, MageManBros.BUTTON_SIZE_HEIGHT * 2 + 40);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        // Add the buttons to the stage to perform rendering and take input.
        stage.addActor(forwardButton);
        stage.addActor(backwardButton);
        stage.addActor(jumpButtonRight);
        stage.addActor(jumpButtonLeft);
        stage.addActor(shootButtonLeft);
        stage.addActor(shootButtonRight);
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        forwardButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                isForwardTouched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                isForwardTouched = false;
            }
        });

        backwardButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                isBackwardTouched = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                isBackwardTouched = false;
            }
        });

        ClickListener jumpListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.getBody().applyLinearImpulse(new Vector2(0, 04), player.getBody().getWorldCenter(), true);
            }
        };
        jumpButtonLeft.addListener(jumpListener);
        jumpButtonRight.addListener(jumpListener);

        ClickListener shootListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO, Shoot function should be implemented here.
            }
        };
        shootButtonLeft.addListener(shootListener);
        shootButtonRight.addListener(shootListener);
    }
}
