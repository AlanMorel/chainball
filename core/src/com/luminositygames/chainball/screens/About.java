package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
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

public class About implements Screen {
	
	private Rectangle lgSite;
	private Rectangle fb;
	private Rectangle gplus;
	private Rectangle credits;
	
	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		lgSite = new Rectangle(158, 771, 774,201);
		fb = new Rectangle(158, 1041, 774,201);
		gplus = new Rectangle(158, 1345, 774,201);
		credits = new Rectangle(158, 1657, 774,201);
		Gdx.input.setInputProcessor(new KeyProcessor());
	}
	 
	@Override
	public void render(float delta) {
		update();
		Assets.batch.begin();
		Chainball.draw(Assets.aboutSprite, 0, 0);
		drawStrings();
		Assets.batch.end();
	}
	
	private void update() {
		if (Gdx.input.justTouched()){
			if (lgSite.contains(Chainball.getX(), Chainball.getY())){
				Gdx.net.openURI("http://luminositygames.com/");
			} else if (fb.contains(Chainball.getX(), Chainball.getY())){
				Gdx.net.openURI("https://www.facebook.com/LuminosityGames");
			} else if (gplus.contains(Chainball.getX(), Chainball.getY())){
				Gdx.net.openURI("https://plus.google.com/+LuminosityGames");
			} else if (credits.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.credits);
			}
		}
	}
	
	private void drawStrings() {
		Chainball.centerString("LuminosityGames.com", 780, Assets.font75);
		Chainball.centerString("Luminosity Games FB", 1115, Assets.font75);
		Chainball.centerString("Luminosity Games G+", 1425, Assets.font75);
		Chainball.centerString("Credits", 1725, Assets.font100);
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
