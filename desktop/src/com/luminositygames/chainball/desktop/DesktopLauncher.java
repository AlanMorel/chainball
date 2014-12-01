package com.luminositygames.chainball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;

public class DesktopLauncher {
	
	private static final int scale = 50;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Constants.NAME;
		config.width = Constants.WIDTH * scale / 100;
		config.height = Constants.HEIGHT * scale / 100;
		config.initialBackgroundColor = Color.valueOf("FFFFFF");
		config.resizable = false;
		config.vSyncEnabled = true;
		
		//config.vSyncEnabled = false;
		//config.foregroundFPS = 0;
		//config.backgroundFPS = 0;
		
		new LwjglApplication(new Chainball(), config);
	}
}
