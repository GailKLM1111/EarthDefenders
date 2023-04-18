package com.gailgames.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gailgames.game.EarthDefenders;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Earth Defenders");
		config.setWindowedMode(1080, 1920);
		config.useVsync(true);
		config.setForegroundFPS(60);
//		new Lwjgl3Application(new EarthDefenders(), config);
		new Lwjgl3Application(new Main(), config);
	}
}
