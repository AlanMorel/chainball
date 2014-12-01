package com.luminositygames.chainball.util;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.screens.About;
import com.luminositygames.chainball.screens.Credits;
import com.luminositygames.chainball.screens.Gameplay;
import com.luminositygames.chainball.screens.HowToPlay;
import com.luminositygames.chainball.screens.MainMenu;
import com.luminositygames.chainball.screens.More;
import com.luminositygames.chainball.screens.Settings;
import com.luminositygames.chainball.screens.Splash;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Assets {

	//CAMERA
	public static OrthographicCamera camera;

	//SPRITES
	public static Sprite splashSprite;
	public static Sprite mainMenuBackgroundSprite;
	public static Sprite mainMenuForegroundSprite;
	public static Sprite gameplayBackgroundSprite;
	public static Sprite grid;
	public static Sprite arrow;
	public static Sprite blocker;
	public static Sprite moreSprite;
	public static Sprite settingsSprite;
	public static Sprite onSprite;
	public static Sprite offSprite;
	public static Sprite howToPlaySprite;
	public static Sprite aboutSprite;
	public static Sprite creditsSprite;
	public static Sprite UISprite;
	public static Sprite darkener;
	public static Sprite shadow;
	
	//BATCH
	public static SpriteBatch batch;

	//SHAPE RENDERER
	public static ShapeRenderer renderer;

	//FONTS
	public static BitmapFont font75;
	public static BitmapFont font100;
	public static BitmapFont font250;

	//RANDOM
	public static Random rand;

	//SCREENS
	public static Screen mainMenu;
	public static Screen splash;
	public static Screen gameplay;
	public static Screen more;
	public static Screen settings;
	public static Screen about;
	public static Screen howToPlay;
	public static Screen credits;

	//MUSIC
	public static Music currentSong;
	public static Music gamePlaySong;
	public static Music mainMenuSong;

	//SOUNDS
	public static Sound blue;
	public static Sound green;
	public static Sound red;
	public static Sound splashSound;
	public static Sound lose;

	public static void load(){
		loadCamera();
		loadSprites();
		loadBatch();
		loadShapeRenderer();
		loadFonts();
		loadRandom();
		loadScreens();
		loadMusic();
		loadSounds();	
	}

	private static void loadMusic() {
		gamePlaySong = Gdx.audio.newMusic(Gdx.files.internal("gameplay.ogg"));
		gamePlaySong.setLooping(true);
		mainMenuSong = Gdx.audio.newMusic(Gdx.files.internal("mainmenu.wav"));
		mainMenuSong.setLooping(true);
	}

	private static void loadRandom() {
		rand = new Random();
	}

	public static void loadShapeRenderer() {
		renderer = new ShapeRenderer();
	}

	public static void loadBatch() {
		batch = new SpriteBatch();
	}

	public static void loadScreens() {
		splash = new Splash();
		mainMenu = new MainMenu();
		gameplay = new Gameplay();
		settings = new Settings();
		more = new More();
		about = new About();
		howToPlay = new HowToPlay();
		credits = new Credits();
	}

	public static void loadCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.WIDTH, Constants.HEIGHT);
	}

	public static void loadSprites(){
		splashSprite = new Sprite(new Texture("splash.png"));
		splashSprite.flip(false, true);
		mainMenuBackgroundSprite = new Sprite(new Texture("mainmenubackground.png"));
		mainMenuBackgroundSprite.flip(false, true);
		mainMenuForegroundSprite = new Sprite(new Texture("mainmenuforeground.png"));
		mainMenuForegroundSprite.flip(false, true);
		gameplayBackgroundSprite = new Sprite(new Texture("gameplaybackground.png"));
		gameplayBackgroundSprite.flip(false, true);
		arrow = new Sprite(new Texture("arrow.png"));
		arrow.flip(false, true);
		blocker = new Sprite(new Texture("blocker.png"));
		blocker.flip(false, true);
		grid = new Sprite(new Texture("grid.png"));
		grid.flip(false, true);
		moreSprite = new Sprite(new Texture("more.png"));
		moreSprite.flip(false, true);
		settingsSprite = new Sprite(new Texture("settings.png"));
		settingsSprite.flip(false, true);
		onSprite = new Sprite(new Texture("on.png"));
		onSprite.flip(false, true);
		offSprite = new Sprite(new Texture("off.png"));
		offSprite.flip(false, true);
		howToPlaySprite = new Sprite(new Texture("howtoplay.png"));
		howToPlaySprite.flip(false, true);
		aboutSprite = new Sprite(new Texture("about.png"));
		aboutSprite.flip(false, true);
		creditsSprite = new Sprite(new Texture("credits.png"));
		creditsSprite.flip(false, true);
		UISprite = new Sprite(new Texture("ui.png"));
		UISprite.flip(false, true);
		darkener = new Sprite(new Texture("darkener.png"));
		darkener.flip(false, true);
		shadow = new Sprite(new Texture("shadow.png"));
		shadow.flip(false, true);
	}

	public static void loadSounds(){
		blue = Gdx.audio.newSound(Gdx.files.internal("blue.wav"));
		green = Gdx.audio.newSound(Gdx.files.internal("green.wav"));
		red = Gdx.audio.newSound(Gdx.files.internal("red.wav"));
		splashSound = Gdx.audio.newSound(Gdx.files.internal("splash.mp3"));
		lose  = Gdx.audio.newSound(Gdx.files.internal("lose.mp3"));
	}

	public static void loadFonts(){
		FreeTypeFontGenerator font_gen = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		font100 = font_gen.generateFont(parameter);
		font100.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		font100.setColor(Color.BLACK);	
		font100.setScale(1, -1);

		parameter.size = 75;
		font75 = font_gen.generateFont(parameter);
		font75.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		font75.setColor(Color.BLACK);	
		font75.setScale(1, -1);

		parameter.size = 250;	
		font250 = font_gen.generateFont(parameter);
		font250.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		font250.setColor(Color.BLACK);	
		font250.setScale(1, -1);

		font_gen.dispose();
	}

	public static void play(Music song){
		if(!song.isPlaying() && Prefs.music()){
			Assets.stopCurrentSong();
			song.play();
			currentSong = song;
		}
	}

	public static void play(Sound sound, float volume){
		if(Prefs.soundEffects()){
			sound.play(volume);
		}
	}

	public static void stopCurrentSong() {
		if (currentSong != null){
			currentSong.stop();
		}
	}
}
