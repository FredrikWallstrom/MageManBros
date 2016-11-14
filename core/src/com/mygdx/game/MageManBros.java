package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MenuScreen;

public class MageManBros extends Game {

	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public static final float BUTTON_SIZE_WIDTH = 100;
	public static final float BUTTON_SIZE_HEIGHT = 50;

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
