package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MageManBros;

/**
 * Created by fredr on 2016-11-11.
 */

public class MenuScreen implements Screen {
    private MageManBros window;

    private Stage stage;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton playButton;

    public MenuScreen(MageManBros window) {
        this.window = window;
    }

    /**
     * interfaceMethod
     * called when screen is set with SetScreen
     */
    @Override public void show() {
        myTexture = new Texture(Gdx.files.internal("playbtn.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        playButton = new ImageButton(myTexRegionDrawable); //Set the playButton up
        playButton.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - (MageManBros.BUTTON_SIZE_WIDTH / 2),
                            Gdx.graphics.getHeight() / 2 - (MageManBros.BUTTON_SIZE_HEIGHT / 2) );

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(playButton); //Add the playButton to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                window.setScreen(new GameScreen(window) {
                });
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
