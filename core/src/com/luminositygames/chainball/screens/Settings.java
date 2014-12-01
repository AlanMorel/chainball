package com.luminositygames.chainball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.util.Assets;
import com.luminositygames.chainball.util.Prefs;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Settings implements Screen {

	private Rectangle nickname;
	private Rectangle sound;
	private Rectangle music;
	private Rectangle save;

	private String name;
	private String title = "Enter a name";
	private String textInitial = "";

	@Override
	public void show() {
		Assets.play(Assets.mainMenuSong);
		nickname = new Rectangle(158, 771, 774,201);
		sound = new Rectangle(158, 1041, 774,201);
		music = new Rectangle(158, 1345, 774,201);
		save = new Rectangle(158, 1657, 774,201);
		if (Prefs.name().length() > 0){
			name = Prefs.name();
		} else {
			name = Constants.DEFAULT_TEXT;
		}
	}

	@Override
	public void render(float delta) {
		update();
		Assets.batch.begin();
		Chainball.draw(Assets.settingsSprite, 0, 0);
		drawStrings();
		drawSettings();
		Assets.batch.end();
	}

	private void drawSettings() {
		if(Prefs.soundEffects()){
			Chainball.draw(Assets.onSprite, 911, 1041);
		} else {
			Chainball.draw(Assets.offSprite, 911, 1041);
		}
		if(Prefs.music()){
			Chainball.draw(Assets.onSprite, 911, 1345);
		} else {
			Chainball.draw(Assets.offSprite, 911, 1345);
		}
	}

	private void drawStrings() {
		Chainball.centerString(name, 780, Assets.font100);
		Chainball.leftString("Sound " + (Prefs.soundEffects() ? "On" : "Off"), 325, 1100, Assets.font100);
		Chainball.leftString("Music " + (Prefs.music() ? "On" : "Off"), 325, 1400, Assets.font100);
	}

	private void update() {
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Chainball.setScreen(Assets.mainMenu);
		}
		if (Gdx.input.justTouched()){
			if (nickname.contains(Chainball.getX(), Chainball.getY())){
				name = "";
				Gdx.input.getTextInput(new TextInputListener() {

					@Override
					public void input(String text) {
						System.out.println("Text length: " + text.length());
						if (text.length() > Constants.MAX_NAME_LENGTH){
							text = text.substring(0, Constants.MAX_NAME_LENGTH);
							Prefs.setName(text);
						} else if (text.length() < 1){
							text = Constants.DEFAULT_TEXT; 
						} else {
							Prefs.setName(text);
						}
						name = text;
					}

					@Override
					public void canceled() {
						name = "Enter a name";
					}
				}, title, textInitial);
			} else if (sound.contains(Chainball.getX(), Chainball.getY())){
				if (Prefs.soundEffects()){
					Prefs.setSoundEffects(false);
				} else {
					Assets.green.play(Constants.VOLUME);
					Prefs.setSoundEffects(true);
				}
			} else if (music.contains(Chainball.getX(), Chainball.getY())){
				if (Prefs.music()){
					Assets.stopCurrentSong();
					Prefs.setMusic(false);
				} else {
					Assets.green.play(Constants.VOLUME);
					Prefs.setMusic(true);
					Assets.play(Assets.mainMenuSong);
				}
			} else if (save.contains(Chainball.getX(), Chainball.getY())){
				Chainball.setScreen(Assets.mainMenu);
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
