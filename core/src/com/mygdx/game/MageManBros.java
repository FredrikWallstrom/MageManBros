package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.GameScreen;

public class MageManBros extends Game {

	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
