package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MageManBros;

/**
 * Created by fredr on 2016-11-10.
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer score;
    private Label scoreLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public Hud(SpriteBatch sb){
        score = 0;

        viewport = new FitViewport(MageManBros.V_WIDTH, MageManBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("00000000" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("SCORE" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("LIFE" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(marioLabel).padLeft(10).padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).padLeft(10);
        table.add(levelLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
