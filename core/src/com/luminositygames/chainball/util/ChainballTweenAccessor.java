package com.luminositygames.chainball.util;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class ChainballTweenAccessor implements TweenAccessor<ChainballTween> {

	public static final int VALUE = 0;
	
	@Override
	public int getValues(ChainballTween target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case VALUE:
			returnValues[0] = target.getValue();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(ChainballTween target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case VALUE:
			target.setValue(newValues[0]);
			break;
		default:
			assert false;
		}
	}
}