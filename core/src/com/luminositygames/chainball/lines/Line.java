package com.luminositygames.chainball.lines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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

public class Line {

	private float x1;
	private float y1;
	private float x2;
	private float y2;

	public Line (float x1, float y1, float x2, float y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public float getX1(){
		return x1;
	}
	
	public float getY1(){
		return y1;
	}
	
	public void update(float speed) {
		y1 += speed;
		y2 += speed;
	}
	
	public boolean isVisible(){
		if (y1 < Constants.HEIGHT || y2 < Constants.HEIGHT){
			return true;
		}
		return false;
	}
	
	public void draw(){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl20.glLineWidth(Constants.LINE_THICKNESS);
		Assets.renderer.setProjectionMatrix(Assets.camera.combined);
		Assets.renderer.begin(ShapeType.Line);
		Assets.renderer.identity();
		Assets.renderer.setColor(Constants.LINE_COLOR);
		Assets.renderer.line(x1, y1, x2, y2);
		Assets.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}