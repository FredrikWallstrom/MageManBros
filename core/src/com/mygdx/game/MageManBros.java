package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MenuScreen;

/**
 * Class that will be run first on startup.
 * It will make a call on the MenuScreen so it will be populated first.
 */
public class MageManBros extends Game {

    // Some constants for the game.
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public static final float BUTTON_SIZE_WIDTH = 200;
	public static final float BUTTON_SIZE_HEIGHT = 100;

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}