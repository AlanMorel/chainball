package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.util.Assets;
import com.luminositygames.chainball.util.ChainballTween;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Splash implements Screen {

	private ChainballTween splashAlpha;
	
	@Override
	public void show() {
		Assets.play(Assets.splashSound, Constants.VOLUME);
		initializeTweenManager();
	}
	
	private void initializeTweenManager() {
		splashAlpha = new ChainballTween(0, ChainballTween.SPLASH);
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS | Alpha: " + Assets.splashSprite.getColor().a);
		splashAlpha.update(delta);
		update();
		Assets.batch.begin();
		Assets.splashSprite.setAlpha(splashAlpha.getValue());
		Assets.splashSprite.draw(Assets.batch);
		Assets.batch.end();
	}

	private void update() {
		if (Gdx.input.justTouched()){
			Chainball.setScreen(Assets.mainMenu);
		}
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