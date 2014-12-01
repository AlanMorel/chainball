package com.luminositygames.chainball.util;

import com.badlogic.gdx.Screen;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.balls.Ball;
import com.luminositygames.chainball.balls.BallType;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class ChainballTween {

	public static final int SPLASH = 1;
	public static final int ARROW = 2;
	public static final int BALL_ALPHA = 3;
	public static final int BALL_RADIUS = 4;
	public static final int GAMEOVER = 5;
	
	private TweenManager tweenManager;
	private float value;

	public ChainballTween(float value, int type){
		this.value = value;
		this.tweenManager = new TweenManager();
		setupTween(type);
	}
	
	public ChainballTween(float value, int type, Ball ball){
		this.value = value;
		this.tweenManager = new TweenManager();
		setupTween(type, ball);
	}
	
	public float getValue(){
		return value;
	}

	public void setValue(float v){
		value = v;
	}

	public void update(float delta) {
		tweenManager.update(delta);
	}

	public void setupTween(int type){
		Tween.registerAccessor(ChainballTween.class, new ChainballTweenAccessor());
		if (type == ChainballTween.GAMEOVER){
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.5f).target(450).repeatYoyo(Tween.INFINITY, 0).start(tweenManager);
		} else if (type == ChainballTween.ARROW){
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.5f).target(1450).repeatYoyo(Tween.INFINITY, 0).start(tweenManager);
		} else if (type == ChainballTween.SPLASH){
			Tween.to(this, ChainballTweenAccessor.VALUE, 1f).target(1).repeatYoyo(1, 2.5f).setCallback(setScreenTo(Assets.mainMenu)).start(tweenManager);
		}
	}

	public void setupTween(int type, Ball ball){
		if (type == ChainballTween.BALL_ALPHA){
			setAlphaTween(ball);
		} else if (type == ChainballTween.BALL_RADIUS){
			setRadiusTween(ball);
		}
	}
	
	public void setAlphaTween(Ball ball) {
		if (ball.getType().equals(BallType.HEAD)){
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.75f).target(0).repeat(Tween.INFINITY, 0).start(tweenManager);
		} else if (ball.getType().equals(BallType.GRAY)){
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.50f).target(0).repeat(Tween.INFINITY, 0).start(tweenManager);
		} else {
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.75f).target(0).repeatYoyo(0, 0).start(tweenManager);
		}
	}

	public void setRadiusTween(Ball ball) {
		if (ball.getType().equals(BallType.HEAD)){
			float targetRadius = ball.getCircle().radius * 3;
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.75f).target(targetRadius).repeat(Tween.INFINITY, 0).start(tweenManager);
		} else if (ball.getType().equals(BallType.GRAY)){
			float targetRadius = ball.getCircle().radius * 10;
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.50f).target(targetRadius).repeat(Tween.INFINITY, 0).start(tweenManager);
		} else {
			float targetRadius = ball.getCircle().radius * 4;
			Tween.to(this, ChainballTweenAccessor.VALUE, 0.50f).target(targetRadius).repeatYoyo(0, 0).start(tweenManager);
		}
	}

	private TweenCallback setScreenTo(final Screen scr){
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				Chainball.setScreen(scr);
			}
		};
		return tweenCallback;
	}
}
