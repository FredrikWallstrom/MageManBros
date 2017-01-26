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
 * This class will display the menu screen when the game is started up.
 */
public class MenuScreen implements Screen {

    // The current window that the game is running on.
    private MageManBros window;

    // This stage will handle viewport and distribute input events.
    // So basically buttons can be added to this stage to represent them.
    private Stage stage;

    /**
     * Constructor, exists so we can create an instance of this menuscreen.
     */
    public MenuScreen(MageManBros window) {
        this.window = window;
    }

    /**
     * InterfaceMethod
     * called when screen is set with SetScreen
     */
    @Override public void show() {
        // Create the playButton, set image, size and position.
        Texture myTexture = new Texture(Gdx.files.internal("playbtn.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton playButton = new ImageButton(myTexRegionDrawable);
        playButton.setSize(MageManBros.BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - (MageManBros.BUTTON_SIZE_WIDTH / 2),
                            Gdx.graphics.getHeight() / 2 - (MageManBros.BUTTON_SIZE_HEIGHT / 2) );

        // Set up the stage for the UI
        stage = new Stage(new ScreenViewport());

        // Add the playButton to the stage to perform rendering and take input.
        stage.addActor(playButton);

        // Start taking input from the UI.
        Gdx.input.setInputProcessor(stage);

        // ClickListener for the play button, switch screen to GameScreen when button is pushed.
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                window.setScreen(new GameScreen(window) {
                });
            }
        });
    }

    /**
     * This method will be called when the application should render itself.
     * @param delta is the time between the start of the previous and the start of the current call
     *              to render().
     */
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

    /**
     * Called when he application is destroyed
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
