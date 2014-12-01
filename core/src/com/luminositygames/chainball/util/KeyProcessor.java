package com.luminositygames.chainball.util;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.game.Game;
import com.luminositygames.chainball.screens.Gameplay;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class KeyProcessor implements InputProcessor {
	
	private Game game;
	
	public KeyProcessor(Game game) {
		this.game = game;
	}

	public KeyProcessor() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
			if (Chainball.getCurrentScreen().equals(Assets.credits)){
				Chainball.setScreen(Assets.about);
			} else if (Chainball.getCurrentScreen().equals(Assets.howToPlay)){
				Chainball.setScreen(Assets.more);
			} else if (Chainball.getCurrentScreen().equals(Assets.about)){
				Chainball.setScreen(Assets.more);
			} else if (Chainball.getCurrentScreen().equals(Assets.more)){
				Chainball.setScreen(Assets.mainMenu);
			} else if (Chainball.getCurrentScreen().equals(Assets.gameplay)){
				Gameplay.handlePause(game);
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
