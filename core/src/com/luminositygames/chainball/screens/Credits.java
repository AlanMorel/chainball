package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
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

public class Credits implements Screen {

	private Rectangle alanMorel;
	private Rectangle shawnShakir;
	private Rectangle noSoap;
	private Rectangle libGDX;

	private float offset;
	private float lastY;

	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		Gdx.input.setInputProcessor(new KeyProcessor());
		alanMorel = new Rectangle(150, 900, 750, 250);
		shawnShakir = new Rectangle(150, 1250, 750, 250);
		noSoap = new Rectangle(150, 1550, 750, 250);
		libGDX = new Rectangle(150, 1850, 750, 250);
		offset = 0;
		lastY = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS - OFFSET " + offset);
		update();
		Assets.batch.begin();
		Chainball.draw(Assets.creditsSprite, 0, -offset);
		drawShadow();
		Assets.batch.end();
	}

	private void drawShadow() {
		Chainball.draw(Assets.shadow, 0, Constants.HEIGHT - Assets.shadow.getHeight() + (offset / 4));
	}

	private void update() {
		if (Gdx.input.justTouched()){
			if (alanMorel.contains(Chainball.getX(), Chainball.getY() + offset)){
				Gdx.net.openURI("http://alanmorel.com/");
			} else if (shawnShakir.contains(Chainball.getX(), Chainball.getY() + offset)){
				Gdx.net.openURI("http://shawnshakir.com/");
			} else if (noSoap.contains(Chainball.getX(), Chainball.getY() + offset)){
				Gdx.net.openURI("http://www.nosoapradio.us/");
			} else if (libGDX.contains(Chainball.getX(), Chainball.getY() + offset)){
				Gdx.net.openURI("http://libgdx.badlogicgames.com/");
			}
			lastY = Chainball.getY();
		} else if (Gdx.input.isTouched()){
			offset -= Chainball.getY() - lastY;
			lastY = Chainball.getY();
		}
		fixOffset();
	}

	private void fixOffset(){
		if (offset < 0) {
			offset = 0;
		} else if (offset > Assets.creditsSprite.getHeight() - Constants.HEIGHT) {
			offset = Assets.creditsSprite.getHeight() - Constants.HEIGHT;
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
