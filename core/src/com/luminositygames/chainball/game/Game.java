package com.luminositygames.chainball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.balls.Ball;
import com.luminositygames.chainball.balls.BallType;
import com.luminositygames.chainball.balls.Balls;
import com.luminositygames.chainball.lines.Lines;
import com.luminositygames.chainball.screens.GameOver;
import com.luminositygames.chainball.util.Assets;
import com.luminositygames.chainball.util.Stopwatch;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Game {

	private Stopwatch stopwatch;
	private Lines lines;
	private Balls balls;
	private int score;
	private int currentChain;
	private int longestChain;
	private float speed;
	private float offset;
	private boolean started;
	private boolean isPaused;
	
	public Game (){
		stopwatch = new Stopwatch();
		lines = new Lines();
		balls = new Balls();
		score = 0;
		currentChain = 0;
		longestChain = 0;
		speed = 0f;
		offset = 0f;
		started = false;
		isPaused = false;
	}
	
	public Balls getBalls(){
		return balls;
	}
	
	public float getOffset(){
		return offset;
	}
	
	public boolean hasStarted(){
		return started;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getCurrentChainLength(){
		return currentChain;
	}

	public void updateScore(Ball ball){
		BallType type = ball.getType();
		if (type.equals(BallType.RED)){
			score = score * 19 / 20;
		} else if (type.equals(BallType.BLUE)){
			score += 1;
		} else if (type.equals(BallType.GREEN)){
			score += 3;
		}
	}
	
	private void incrementChainLength(){
		currentChain++;
		if (currentChain > longestChain){
			longestChain = currentChain;
		}
	}

	public void start() {
		stopwatch.start();
		started = true;
		lines.touchHead();
		speed = Constants.GAME_SPEED;
		balls.reveal();
		Assets.arrow.setX(-Constants.WIDTH);
		Assets.blocker.setX(-Constants.WIDTH);	
	}
	
	public Stopwatch getStopwatch(){
		return stopwatch;
	}

	public void update() {
		if (!isPaused){
			balls.update(speed);
			lines.update(speed);
			lines.updateCurrentLine(speed);
			offset += speed;
			if (offset % 120 == 0){
				initializeNewRow();
				cleanUpScreen();
			}
		}
	}

	public void draw(){		
		if (hasStarted()){
			lines.drawLines();
			if (Gdx.input.isTouched() && !isPaused){
				lines.drawCurrentLine();
			}
		}
		balls.drawBalls();
	}

	public void processTouch(float x, float y) {
		if (balls.isTouched(x, y)){
			incrementChainLength();
			Ball ball = balls.getTouchedBall(x, y);
			ball.playSound(Constants.VOLUME);
			updateScore(ball);
			lines.addCurrentLine(ball);
			ball.touch();
			if (ball.getType() == BallType.GRAY){
				Gdx.input.vibrate(Constants.VIBRATION_DURATION);
				Screen gameOver = new GameOver(balls, lines, offset, score, longestChain, stopwatch.getElapsedTimeSecs());
				Chainball.setScreen(gameOver);
			} else if (ball.getType() == BallType.RED){
				Gdx.input.vibrate(Constants.VIBRATION_DURATION);
			} 
		}
		lines.updateCurrentLine(x, y);
	}

	private void initializeNewRow() {
		int x = Assets.rand.nextInt(9);
		balls.addBall(balls.getRandomBallType(), x, -1);
	}

	private void cleanUpScreen() {
		balls.cleanUp();
		lines.cleanUp();
	}

	public void pause() {
		isPaused = true;
		//speed = 0f;
	}
	
	public boolean isPaused(){
		return isPaused;
	}
	
	public void unPause(){
	    isPaused = false;
		//speed = Constants.GAME_SPEED;
	}

	public void resetChain() {
		currentChain = 0;
		lines.reset();
	}
}
