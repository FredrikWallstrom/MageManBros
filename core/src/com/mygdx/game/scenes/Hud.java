package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MageManBros;

/**
 * This class will represent the game information.
 * Game information means the status of the player, health bar and also the score the player has.
 */
public class Hud implements Disposable {
    public Stage stage;

    public Hud(SpriteBatch sb){
        // Setup the HUD viewport using a new camera separate from game cam.
        // Define stage using that viewport and games spriteBatch.
        Viewport viewport = new FitViewport(MageManBros.V_WIDTH, MageManBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        // Define a table used to organize hud's labels.
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        // Define the HP-bar and the score label and place them on the table.
        Image healthBar = new Image(new Texture(Gdx.files.internal("rock_bar.png")));
        Label score = new Label("00000000", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(healthBar).padLeft(5).size(100, 20).padTop(10);
        table.add(score).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
