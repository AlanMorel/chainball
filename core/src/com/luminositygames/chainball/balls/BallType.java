package com.luminositygames.chainball.balls;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public enum BallType {
	UNKNOWN(-1), 
	MENU(0), 
	HEAD(1), 
	HIDDEN(2), 
	RED(3), 
	BLUE(4),
	GREEN(5),
	GRAY(6);

	private int type;

	private BallType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
};