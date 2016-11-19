package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;

import java.util.ArrayList;

/**
 * Created by fredr on 2016-11-05.
 */

public class Game {
    // Tiled map variables
    private World world;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Camera and Viewport variables
    private OrthographicCamera gameCam;

    // Stage for the Android setup, to add some buttons
    private Stage stage;

    // Variables for detect if the move buttons in android mode is pressed
    private boolean isForwardTouched = false;

    private Player player;
    private ArrayList<Entity> gameObjects;

    public Game(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        this.world = world;
        this.map = map;
        this.gameCam = gameCam;
        this.renderer = renderer;
        this.gameObjects = new ArrayList<Entity>();

        // Check if the game is running on Android device, in that case, create extra buttons
        if(Gdx.app.getType() == Application.ApplicationType.Android) createButtons();

        createPlayer();
    }

    private void createPlayer() {
        player = new Player(world, map, new Rectangle(32, 32, 32, 32));
        gameObjects.add(player);
    }

    public Iterable<Entity> getGameObjects() {
        return gameObjects;
    }

    public void updateGame(float delta) {
        // Check if the game is running on Android device, in that case, render extra buttons
        if(Gdx.app.getType() == Application.ApplicationType.Android){
            stage.draw();
            if(isForwardTouched){
                player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
            }
        }else{
            handleInput(delta); // The game is running on desktop mode
        }

        world.step(1/60f, 6, 2);
        gameCam.position.x = player.getBody().getPosition().x;

        gameCam.update();
        renderer.setView(gameCam);
    }

    private void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.getBody().applyLinearImpulse(new Vector2(0, 04), player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getBody().getLinearVelocity().x <= 2){
            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getBody().getLinearVelocity().x >= -2){
            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        }
    }

    private void createButtons(){
        Texture myTexture;
        TextureRegion myTextureRegion;
        TextureRegionDrawable myTexRegionDrawable;
        final ImageButton forwardButton;

        myTexture = new Texture(Gdx.files.internal("BlueButton-Active.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        forwardButton = new ImageButton(myTexRegionDrawable); //Set the forwardButton up
        forwardButton.setSize(MageManBros. BUTTON_SIZE_WIDTH, MageManBros.BUTTON_SIZE_HEIGHT);
        forwardButton.setPosition(Gdx.graphics.getWidth() - MageManBros.BUTTON_SIZE_WIDTH, 0);

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
