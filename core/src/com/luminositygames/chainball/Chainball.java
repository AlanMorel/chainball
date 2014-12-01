package com.luminositygames.chainball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
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

public class Chainball extends ApplicationAdapter {

	public static Screen screen;

	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		Assets.load();
		setScreen(Assets.splash);
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Assets.camera.update();
		Assets.batch.setProjectionMatrix(Assets.camera.combined);
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS");
		screen.render(1 / Constants.FRAMES_PER_SECOND);
	}

	public static void setScreen(Screen s){
		screen = s;
		screen.show();
	}
	
	public static Screen getCurrentScreen(){
		return screen;
	}
	
	public static void draw(Sprite sprite){
		Assets.batch.draw(sprite, sprite.getX(), sprite.getY());
	}
	
	public static void draw(Sprite sprite, float x, float y){
		Assets.batch.draw(sprite, x, y);
	}
	
	public static void leftString(String str, float x, float y, BitmapFont font){
		font.draw(Assets.batch, str, x, y);
	}
	
	public static void centerString(String str, float y, BitmapFont font){
		float width = font.getBounds(str).width;
		font.draw(Assets.batch, str, (Constants.WIDTH / 2) - (width / 2), y);
	}
	
	public static void rightString(String str, float x, float y, BitmapFont font){
		float width = font.getBounds(str).width;
		font.draw(Assets.batch, str, x - width, y);
	}
	
	public static float getX(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		Assets.camera.unproject(pos);
		return pos.x;
	}
	
	public static float getY(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		Assets.camera.unproject(pos);
		return pos.y;
	}
	
	public static int [] getUniqueRandomNumbers (int upper, int numbers){
		int [] array = new int [numbers];
		for (int i = 0; i < numbers; i++){
			int randNum = Assets.rand.nextInt(upper);
			array[i] = randNum;
			while (contains(array, randNum)){
				randNum = Assets.rand.nextInt(upper);
				array[i] = randNum;
			}
		}
		return array;
	}
	
	public static boolean contains(int [] array, int num){
		int counter = 0;
		for (int i = 0; i < array.length; i++){
			if (array[i] == num){
				counter ++;
			}
		}
		if (counter > 1){
			return true;
		}
		return false;
	}
}
