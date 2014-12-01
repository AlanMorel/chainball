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

public class More implements Screen {
	
	private Rectangle about;
	private Rectangle leaderboards;
	private Rectangle playStore;
	private Rectangle howToPlay;
	
	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		about = new Rectangle(158, 771, 774,201);
		leaderboards = new Rectangle(158, 1041, 774,201);
		playStore = new Rectangle(158, 1345, 774,201);
		howToPlay = new Rectangle(158, 1657, 774,201);
		Gdx.input.setInputProcessor(new KeyProcessor());
	}
	
	@Override
	public void render(float delta) {
		update();
		Assets.batch.begin();
		Chainball.draw(Assets.moreSprite, 0, 0);
		drawStrings();
		Assets.batch.end();
	}

	private void update() {
		if (Gdx.input.justTouched()){
			if (about.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.about);
			} else if (leaderboards.contains(Chainball.getX(), Chainball.getY())){
				//LEADERBOARDS
				sendOk();
			} else if (playStore.contains(Chainball.getX(), Chainball.getY())){
				Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.sharpacex.BalloonFrenzy");
			} else if (howToPlay.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.howToPlay);
			}
		}
	}
	
	private void sendOk(){
		
	}
	
	private void drawStrings() {
		Chainball.centerString("About", 780, Assets.font100);
		Chainball.centerString("Leaderboards", 1100, Assets.font100);
		Chainball.centerString("Play Store", 1400, Assets.font100);
		Chainball.centerString("How to Play", 1725, Assets.font100);
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
