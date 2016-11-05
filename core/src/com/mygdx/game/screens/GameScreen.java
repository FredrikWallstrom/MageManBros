package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MageManBros;
import com.mygdx.game.entity.Entity;

/**
 * Created by fredr on 2016-11-03.
 */

public class GameScreen implements Screen {
    private MageManBros window;
    private com.mygdx.game.Game currentGame;
    private Texture texture;
    private OrthographicCamera gameCam;
    private SpriteBatch batch;


    public GameScreen(MageManBros window){
        this.window = window;
        this.currentGame = new com.mygdx.game.Game();
        this.batch = new SpriteBatch();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (Entity object : currentGame.getGameObjects()) {
            object.draw(batch);
        }
        batch.end();
        currentGame.updateGame(delta);
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

    }
}
