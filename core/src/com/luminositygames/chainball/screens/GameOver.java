package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Screen;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.balls.Balls;
import com.luminositygames.chainball.lines.Lines;
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

public class GameOver implements Screen {
	
	private ChainballTween height;
	private Balls balls;
	private Lines lines;
	private Rectangle tryAgain;
	private Rectangle exit;
	private float offset;
	private int score;
	private int chain;
	private long time;
	
	public GameOver(Balls b, Lines l, float o, int s, int c, long t){
		this.time = t;
		this.balls = b;
		this.lines = l;
		this.offset = o;
		this.score = s;
		this.chain = c;
	}

	@Override
	public void show() {
		Assets.stopCurrentSong();
		Assets.play(Assets.lose, Constants.VOLUME);
		height = new ChainballTween(350, ChainballTween.GAMEOVER);
		tryAgain = new Rectangle(0, 1310, Constants.WIDTH/2, 100);
		exit = new Rectangle(Constants.WIDTH/2, 1310, Constants.WIDTH/2, 100);
	}
	
	@Override
	public void render(float delta) {
		height.update(delta);
		updateGame();

		Assets.batch.begin();
		Chainball.draw(Assets.grid, 0, offset % 240 - 240);
		Assets.batch.end();

		Assets.batch.begin();
		drawGame();
		Assets.batch.end();

		Assets.batch.begin();
		drawStrings();
		Assets.batch.end();
	}

	public void drawGame(){		
		lines.drawLines();
		balls.drawBalls();
		drawRectangle(0, height.getValue(), Constants.WIDTH, 1075, 0.15f, Color.BLACK);
		drawRectangle(0, 450, Constants.WIDTH, 250, 0.25f, Color.BLACK);
		drawRectangle(0, 925, Constants.WIDTH, 275, 0.25f, Color.BLACK);
		drawRectangle(140, 1275, 470, 150, 0.6f, Color.valueOf("37ba96"));
		drawRectangle(725, 1275, 250, 150, 0.6f, Color.valueOf("f63f6a"));
	}
	
	private void drawStrings() {
		Assets.font250.setColor(Color.WHITE);
		Assets.font100.setColor(Color.WHITE);
		Assets.font75.setColor(Color.WHITE);
		Chainball.centerString(score + "", Constants.HEIGHT/4, Assets.font250);
		Chainball.centerString("Longest chain " + chain, Constants.HEIGHT/2, Assets.font75);
		Chainball.centerString("Played for " + time + " secs" , Constants.HEIGHT/2+125, Assets.font75);
		Chainball.centerString("Play Again         Exit", Constants.HEIGHT/2 + 350, Assets.font100);
		Assets.font250.setColor(Color.BLACK);
		Assets.font100.setColor(Color.BLACK);
		Assets.font75.setColor(Color.BLACK);
	}

	private void updateGame() {
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Chainball.setScreen(Assets.mainMenu);
		}
		if (Gdx.input.justTouched()){
			if (tryAgain.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.gameplay);
			} else if (exit.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.mainMenu);
			}
		}
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
