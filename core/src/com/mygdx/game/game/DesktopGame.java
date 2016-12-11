package com.mygdx.game.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by fredr on 2016-12-10.
 */

public class DesktopGame extends Game {

    public DesktopGame(World world, TiledMap map, OrthographicCamera gameCam, OrthogonalTiledMapRenderer renderer) {
        super(world, map, gameCam, renderer);
    }

    @Override
    public void updateGame(float delta){
        handleInput(delta);
        super.updateGame(delta);
    }
}
