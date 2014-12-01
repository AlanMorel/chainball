package com.luminositygames.chainball.balls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
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

public class Ball {

	private BallType type;
	private Circle circle;
	private Color color;
	private Sound sound;
	private ChainballTween alpha;
	private ChainballTween radius;
	private boolean touched;
	private float angle;
	private float speed;
	private float initialAlpha;
	
	public Ball (BallType t, float x, float y){
		this.type = t;
		this.touched = false;
		this.circle = new Circle();
		this.circle.x = x;
		this.circle.y = y;
		setValues(t, true);
	}

	public Circle getCircle(){
		return circle;
	}

	public void touch() {
		touched = true;
		alpha = new ChainballTween(initialAlpha, ChainballTween.BALL_ALPHA, this);
		radius = new ChainballTween(getCircle().radius, ChainballTween.BALL_RADIUS, this);
	}

	public boolean isTouched() {
		return touched;
	}

	public float getSpeed(){
		return speed;
	}

	public BallType getType(){
		return type;
	}

	public void playSound(float volume) {
		Assets.play(sound, Constants.VOLUME);
	}

	public boolean isVisible(){
		if (circle.y - Constants.RADIUS < Constants.HEIGHT){
			return true;
		}
		return false;
	}

	public void setValues(BallType type, boolean setup){
		this.type = type;
		if (type.equals(BallType.HIDDEN)){
			setHiddenValues(setup);
		} else if (type.equals(BallType.MENU)){
			setMenuValues();
		} else if (type.equals(BallType.HEAD)){
			setHeadValues(setup);
		} else if (type.equals(BallType.RED)){
			setRedValues(setup);
		} else if (type.equals(BallType.BLUE)){
			setBlueValues(setup);
		} else if (type.equals(BallType.GREEN)){
			setGreenValues(setup);
		} else if (type.equals(BallType.GRAY)){
			setGrayValues(setup);
		}
	}

	private void setMenuValues() {
		this.color = Color.valueOf("f63f6a");
		this.color.a = 0.2f;
		this.sound = Assets.green;
		this.angle = Assets.rand.nextInt(360);
		this.speed = Assets.rand.nextInt(10) + 5;
		this.circle.radius = Constants.RADIUS;
		this.initialAlpha = 0.5f;
	}
	
	private void setHeadValues(boolean setup) {
		this.color = Color.valueOf("f63f6a");
		this.sound = Assets.green;
		this.circle.radius = Constants.HEAD_RADIUS;
		this.initialAlpha = 1.0f;
	}

	private void setHiddenValues(boolean setup) {
		this.color = Color.valueOf("FFFFFF");
		this.sound = Assets.red;
		setUpBall(setup);
	}

	private void setRedValues(boolean setup) {
		this.color = Color.valueOf("f63f6a");
		this.sound = Assets.red;
		setUpBall(setup);
	}

	private void setBlueValues(boolean setup) {
		this.color = Color.valueOf("3e95df");
		this.sound = Assets.blue;
		setUpBall(setup);
	}

	private void setGreenValues(boolean setup) {
		this.color = Color.valueOf("37ba96");
		this.sound = Assets.green;
		setUpBall(setup);
	}

	private void setGrayValues(boolean setup) {
		this.color = Color.valueOf("7d8a8b");
		this.sound = Assets.red;
		setUpBall(setup);
	}

	private void setUpBall(boolean setup) {
		this.circle.radius = Constants.RADIUS;
		this.initialAlpha = 0.75f;
		if (setup){
			getCircle().x = (getCircle().x * Constants.SPACING) + Constants.OFFSET;
			getCircle().y = (getCircle().y * Constants.SPACING) + Constants.RADIUS + Constants.OFFSET;
		}
	}
	
	public void update(float speed) {
		if (type.equals(BallType.MENU)){
			float radius = getCircle().radius;
			double dy = speed * Math.sin(Math.toRadians(angle));
			double dx = speed * Math.sin(Math.toRadians(90 - angle));
			getCircle().x += dx;
			getCircle().y += dy;
			if (getCircle().x + (radius * 2) < 0){
				getCircle().x = Constants.WIDTH;
			} else if (getCircle().x > Constants.WIDTH){
				getCircle().x = -(radius * 2);
			}
			if (getCircle().y + (radius) < 0){
				getCircle().y = Constants.HEIGHT / 2 + (radius);
			} else if (getCircle().y > Constants.HEIGHT / 2 + (radius)){
				getCircle().y = -(radius);
			}
		} else {
			circle.y += speed;
		}
	}
	
	public void draw(){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Assets.renderer.setProjectionMatrix(Assets.camera.combined);
		Assets.renderer.begin(ShapeType.Filled);
		Assets.renderer.identity();
		Assets.renderer.setColor(color);
		Assets.renderer.circle(circle.x + circle.radius, circle.y, circle.radius);
		Assets.renderer.identity();
		Assets.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	public void drawBorder(){
		alpha.update(1 / Constants.FRAMES_PER_SECOND);
		radius.update(1 / Constants.FRAMES_PER_SECOND);
		Color borderColor = new Color(color);
		borderColor.a = alpha.getValue();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Assets.renderer.setProjectionMatrix(Assets.camera.combined);
		Assets.renderer.begin(ShapeType.Filled);
		Assets.renderer.identity();
		Assets.renderer.setColor(borderColor);
		Assets.renderer.circle(getCircle().x + getCircle().radius, getCircle().y, radius.getValue());
		Assets.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}

