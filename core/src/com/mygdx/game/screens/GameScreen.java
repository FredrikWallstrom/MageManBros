package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MageManBros;
import com.mygdx.game.scenes.Hud;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Created by fredr on 2016-11-03.
 */

public class GameScreen implements Screen {
    private com.mygdx.game.Game currentGame;
    private SpriteBatch batch;
    private MageManBros window;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Hud hud;


    public GameScreen(MageManBros window){
        this.window = window;
        this.batch = new SpriteBatch();

        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(MageManBros.V_WIDTH, MageManBros.V_HEIGHT, gameCam);
        hud = new Hud(batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    private void handleInput(float dt){
        if (Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;
        }
    }

    private void updateGame(float delta) {
        handleInput(delta);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateGame(delta);

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.render();
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //    batch.begin();
        //    for (Entity object : currentGame.getGameObjects()) {
        //        object.draw(batch);
        //    }
        //    batch.end();
        //    updateGame(delta);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
