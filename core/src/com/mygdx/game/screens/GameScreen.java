package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.game.AndroidGame;
import com.mygdx.game.game.DesktopGame;
import com.mygdx.game.game.Game;
import com.mygdx.game.MageManBros;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.tools.B2WorldCreator;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Class that will display the core game screen.
 * Will call on the game engine depend on if the game is running on android device
 * or in desktop mode.
 */

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private MageManBros window;
    private Hud hud;
    private Game currentGame;


    // Camera and Viewport variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;



    public GameScreen(MageManBros window) {
        this.window = window;
        this.batch = new SpriteBatch();

        // Create our game HUD for scores/timers/level info
        this.hud = new Hud(batch);



        // Create a StretchViewport and Camera to adjust the parallel projection.
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(MageManBros.V_WIDTH / MageManBros.PPM, MageManBros.V_HEIGHT / MageManBros.PPM, gameCam);

        // Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MageManBros.PPM);

        // Initially set our gamecam to be centered correctly at the start
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Create our Box2D world, setting no gravity in X and no gravity in Y, allows bodies to sleep
        world = new World(new Vector2(0, -10), true);
        // Allows for debug lines of our Box2D world
        b2dr = new Box2DDebugRenderer();

        // Create an instance of the world creator that render all fixtures
        new B2WorldCreator(world, map);

        // Check if the game is running on Android device
        // Start the game
        if(Gdx.app.getType() == Application.ApplicationType.Android) currentGame = new AndroidGame(world, map, gameCam, renderer);
        else currentGame = new DesktopGame(world, map, gameCam, renderer);
    }

    @Override
    public void render(float delta) {
        // Clear the game screen
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render our game map
        renderer.render();

        // Render our Box2DDebugLines
        b2dr.render(world, gameCam.combined);

        // Set our batch to now draw what the Hud camera sees.
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // Update logic
        currentGame.updateGame(delta, batch);
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
