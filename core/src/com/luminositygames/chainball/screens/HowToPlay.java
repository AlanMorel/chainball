package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.util.Assets;
import com.luminositygames.chainball.util.KeyProcessor;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class HowToPlay implements Screen {

	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		Gdx.input.setInputProcessor(new KeyProcessor());
	}
	
	@Override
	public void render(float delta) {
		update();
		Assets.batch.begin();
		Chainball.draw(Assets.howToPlaySprite, 0, 0);
		Assets.batch.end();
	}
	
	private void update() {

	}
	
	@Override
	public void resize(int width, int height) {

	}


	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
