package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.balls.Ball;
import com.luminositygames.chainball.balls.BallType;
import com.luminositygames.chainball.game.Game;
import com.luminositygames.chainball.util.Assets;
import com.luminositygames.chainball.util.ChainballTween;
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

public class Gameplay implements Screen {

	public static Ball headBall;
	
	private Game game;
	private ChainballTween height;
	private ChainballTween arrowHeight;
	private Rectangle keepPlaying;
	private Rectangle exit;

	@Override
	public void show() {
		Assets.play(Assets.gamePlaySong);
		game = new Game();
		keepPlaying = new Rectangle(0, Constants.HEIGHT/2, Constants.WIDTH/2, 100);
		exit = new Rectangle(Constants.WIDTH/2, Constants.HEIGHT/2, Constants.WIDTH/2, 100);
		arrowHeight = new ChainballTween(1200, ChainballTween.ARROW);
		height = new ChainballTween(350, ChainballTween.GAMEOVER);
		headBall = new Ball(BallType.HEAD, Constants.WIDTH/2 - Constants.HEAD_RADIUS, Constants.HEIGHT);
		headBall.touch();
		initializePositions();
		Gdx.input.setInputProcessor(new KeyProcessor(game));
	}

	private void initializePositions(){
		Assets.arrow.setPosition(Constants.WIDTH/2 - Assets.arrow.getWidth()/2, 1200);
		Assets.gameplayBackgroundSprite.setY(Constants.HEIGHT - Assets.gameplayBackgroundSprite.getHeight());
		Assets.blocker.setX(0);
	}

	@Override
	public void render(float delta) {
		long time = game.getStopwatch().getElapsedTimeSecs();
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS - TIME " + time + " - CR " + headBall.getCircle().radius);
		arrowHeight.update(delta);
		height.update(delta);
		updateGame();

		Assets.batch.begin();
		Chainball.draw(Assets.blocker, Assets.blocker.getX(), 0);
		Chainball.draw(Assets.grid, 0, game.getOffset() % 240 - 240);
		Assets.batch.end();

		Assets.batch.begin();
		drawGame();
		Assets.batch.end();

		Assets.batch.begin();
		Chainball.draw(Assets.arrow, Assets.arrow.getX(), arrowHeight.getValue());
		drawStrings();
		Assets.batch.end();
	}

	private void drawGame() {
		game.draw();
		if (game.isPaused()){
			displayPaused2();
		}
	}


	private void drawStrings() {
		Chainball.leftString("Score: " + game.getScore(), 50, 25, Assets.font75);
		Chainball.rightString("Chain: " + game.getCurrentChainLength(), Constants.WIDTH - 50, 25, Assets.font75);
		Chainball.draw(Assets.UISprite, 0, 0);
		if (game.isPaused()){
			displayPaused();
		}
	}

	private void displayPaused() {
		//Chainball.draw(Assets.darkener, 0, height.getValue());
		Assets.font250.setColor(Color.WHITE);
		Assets.font100.setColor(Color.WHITE);
		Chainball.centerString("Paused", Constants.HEIGHT/3, Assets.font250);
		Chainball.centerString("Keep Playing      Exit", Constants.HEIGHT/2, Assets.font100);
		Assets.font250.setColor(Color.BLACK);
		Assets.font100.setColor(Color.BLACK);
	}
	
	private void displayPaused2() {
		drawRectangle(0, height.getValue() + 150, Constants.WIDTH, 575, 0.15f, Color.BLACK);
		drawRectangle(0, 600, Constants.WIDTH, 250, 0.4f, Color.BLACK);
		drawRectangle(0, 925, Constants.WIDTH, 150, 0.4f, Color.BLACK);
	}
	private void drawRectangle(float x, float y, float width, float height, float alpha, Color color){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Assets.renderer.setProjectionMatrix(Assets.camera.combined);
		Assets.renderer.begin(ShapeType.Filled);
		Assets.renderer.identity();
		color = new Color(color);
		color.a = alpha;
		Assets.renderer.setColor(color);
		Assets.renderer.rect(x, y, width, height);
		Assets.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	private void updateGame() {
		if (!game.hasStarted()){
			if (Gdx.input.justTouched()){
				boolean headTouched = headBall.getCircle().contains(Chainball.getX() - headBall.getCircle().radius, Chainball.getY());
				if (headTouched){
					game.start();
				}
			}
		} else {
			game.update();
			if (Gdx.input.isTouched()){
				if (game.isPaused()){
					if (Gdx.input.justTouched()){
						if (keepPlaying.contains(Chainball.getX(), Chainball.getY())){
							game.unPause();
						} else if (exit.contains(Chainball.getX(), Chainball.getY())){
							Chainball.setScreen(Assets.mainMenu);
						}
					}
				} else {
					game.processTouch(Chainball.getX(), Chainball.getY());
				}
			} else {
				if (!game.isPaused()){
					game.resetChain();
				}
			}
		}
	}

	public static void handlePause(Game game2) {
		if (game2.hasStarted()){
			if (game2.isPaused()){
				Chainball.setScreen(Assets.mainMenu);
			} else {
				game2.pause();
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
