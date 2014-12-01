package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.balls.MenuBalls;
import com.luminositygames.chainball.util.Assets;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class MainMenu implements Screen {

	private MenuBalls balls;
	private Rectangle playButton;
	private Rectangle moreButton;
	private Rectangle settingsButton;
	
	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		balls = new MenuBalls(Constants.MAIN_MENU_BALLS);
		playButton = new Rectangle(158, 1041, 774,201);
		moreButton = new Rectangle(158, 1345, 774,201);
		settingsButton = new Rectangle(158, 1657, 774,201);
	}

	@Override
	public void render(float delta) {
		update();
		balls.drawBalls();
		Assets.batch.begin();
		Chainball.draw(Assets.mainMenuBackgroundSprite, 0, Constants.HEIGHT / 2);
		Chainball.draw(Assets.mainMenuForegroundSprite, (Constants.WIDTH / 2) - (Assets.mainMenuForegroundSprite.getWidth() / 2), 100);
		Assets.batch.end();
	}

	public void update(){
		balls.updateBalls();
		if (Gdx.input.justTouched()){
			if (Chainball.getY() <= Constants.HEIGHT / 2){
				balls.addBall(Chainball.getX(), Chainball.getY());
			} else if (playButton.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.gameplay);
			} else if (moreButton.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.more);
			} else if (settingsButton.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.settings);
			}
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