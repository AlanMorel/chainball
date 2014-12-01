package com.luminositygames.chainball;

import com.badlogic.gdx.graphics.Color;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Constants {
	
	//TITLE
	public static final String NAME = "Chainball";
	
	//RESOLUTION
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920; 
	
	//FRAMES PER SECOND
	public static final float FRAMES_PER_SECOND = 60;
	
	//VOLUME
	public static final float VOLUME = 1.0f;
	
	//GAME SPEED
	public static final float GAME_SPEED = 15f;
	
	//VIBRATION DURATION
	public static final int VIBRATION_DURATION = 25;
	
	//SETTINGS
	public static final int MAX_NAME_LENGTH = 10;
	public static final String DEFAULT_TEXT = "Enter a name";
	
	//SPACING
	public static int SPACING = 120;
	
	//LINE CONSTANTS
	public static final int LINE_THICKNESS = 6;
	public static final Color LINE_COLOR = Color.valueOf("384960");
	
	//CIRCLE CONSTANTS
	public static final int RADIUS = 50;
	public static final int OFFSET = 10;
	public static final float HEAD_RADIUS = 300;
			
	//INITIAL NUMBER OF BALLS
	public static final int MAIN_MENU_BALLS = 10;
	public static final int GAMEPLAY_BALLS = 10;
}
