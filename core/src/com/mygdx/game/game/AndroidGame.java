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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by fredr on 2016-12-10.
 */

public class AndroidGame extends Game {

    // Stage for the Android setup, to add some buttons
    private Stage stage;

    // Variables for detect if the move buttons is pressed
    private boolean isForwardTouched = false;


    public AndroidGame(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        super(world, map, gameCam, renderer);
        renderExtraButtons();
    }

    @Override
    public void updateGame(float delta){
        stage.draw();
        if(isForwardTouched){
            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        super.updateGame(delta);
    }

    private void renderExtraButtons(){
        Texture myTexture;
        TextureRegion myTextureRegion;
        TextureRegionDrawable myTexRegionDrawable;
        final ImageButton forwardButton;

        myTexture = new Texture(Gdx.files.internal("BlueButton-Active.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        forwardButton = new ImageButton(myTexRegionDrawable); //Set the forwardButton up
        forwardButton.setSize(com.mygdx.game.MageManBros. BUTTON_SIZE_WIDTH, com.mygdx.game.MageManBros.BUTTON_SIZE_HEIGHT);
        forwardButton.setPosition(Gdx.graphics.getWidth() - com.mygdx.game.MageManBros.BUTTON_SIZE_WIDTH, 0);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(forwardButton); //Add the playButton to the stage to perform rendering and take input.
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
    }
}
