package com.luminositygames.chainball.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.luminositygames.chainball.Constants;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Prefs {

	public static final Preferences preferences = Gdx.app.getPreferences("preferences");
	public static final String SOUND = "sound";
	public static final String MUSIC = "music";
	public static final String NAME = "name";

	public static boolean soundEffects() {
		if (!preferences.contains(SOUND)){
			setSoundEffects(true);
		}
		return preferences.getBoolean(SOUND, true);
	}

	public static void setSoundEffects(boolean bool) {
		preferences.putBoolean(SOUND, bool);
		preferences.flush();
	}

	public static boolean music() {
		if (!preferences.contains(MUSIC)){
			setMusic(true);
		}
		return preferences.getBoolean(MUSIC, true);
	}

	public static void setMusic(boolean bool) {
		preferences.putBoolean(MUSIC, bool);
		preferences.flush();
	}

	public static String name() {
		if (!preferences.contains(NAME)){
			setName(Constants.DEFAULT_TEXT);
		}
		return preferences.getString(NAME);
	}

	public static void setName(String name) {
		preferences.putString(NAME, name);
		preferences.flush();
	}
}