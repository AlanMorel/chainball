package com.luminositygames.chainball.balls;

import java.util.ArrayList;

import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.screens.Gameplay;
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

public class Balls {
	
	private ArrayList<Ball> balls;

	public Balls(){
		this.balls = new ArrayList<Ball>();
		int [] array =  Chainball.getUniqueRandomNumbers(8 * 9, Constants.GAMEPLAY_BALLS);
		for (int i = 0; i < Constants.GAMEPLAY_BALLS; i++){
			int x = array[i] % 9;
			int y = array[i] % 8;
			addBall(BallType.HIDDEN, x, y);
		}
	}
	
	public void reveal(){
		for (Ball ball : balls){
			ball.setValues(getRandomBallType(), false);
		}
	}
	
	public boolean isTouched(float x, float y){
		for (Ball ball:balls){
			if (ball.getCircle().contains(x - Constants.RADIUS, y) && !ball.isTouched()){
				return true;
			}
		}
		return false;
	}
	
	public void addBall(BallType type, int x, int y){
		Ball ball = new Ball(type, x, y);
		balls.add(ball);
	}

	public void drawBalls(){
		Gameplay.headBall.draw();
		Gameplay.headBall.drawBorder();
		for (Ball ball : balls){
			if (ball.isTouched()){
				ball.drawBorder();
			}
			ball.draw();
		}
	}

	public Ball getTouchedBall(float x, float y){
		for (Ball ball:balls){
			if (ball.getCircle().contains(x - Constants.RADIUS, y) && !ball.isTouched()){
				return ball;
			}
		}
		return null;
	}

	public void update(float speed) {
		Gameplay.headBall.update(speed);
		for (Ball ball : balls){
			ball.update(speed);
		}
	}

	public void cleanUp() {
		for (int i = 0; i < balls.size(); i++){
			Ball ball = balls.get(i);
			if (!ball.isVisible()){
				balls.remove(ball);
			}
		}
	}
	
	public BallType getRandomBallType(){
		int rand = Assets.rand.nextInt(100) + 1;
		if (rand <= 25){
			return BallType.RED;
		} else if (rand <= 50){
			return BallType.BLUE;
		} else if (rand <= 75){
			return BallType.GREEN;
		} else if (rand <= 100){
			return BallType.GRAY;
		}
		return null;
	}
}
