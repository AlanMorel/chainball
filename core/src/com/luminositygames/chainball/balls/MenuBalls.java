package com.luminositygames.chainball.balls;

import java.util.ArrayList;

import com.luminositygames.chainball.Constants;
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

public class MenuBalls {

	private ArrayList<Ball> balls;

	public MenuBalls(int num){
		balls = new ArrayList<Ball>();
		for (int i = 0; i < num; i++){
			addBall(Assets.rand.nextInt(Constants.WIDTH), Assets.rand.nextInt(Constants.HEIGHT / 2));
		}
	}

	public void addBall(float x, float y){
		balls.add(new Ball(BallType.MENU, x - Constants.RADIUS, y));
	}

	public void updateBalls() {
		for (Ball ball : balls){
			ball.update(ball.getSpeed());
		}
	}

	public void drawBalls() {
		for (Ball ball : balls){
			ball.draw();
		}
	}
}
