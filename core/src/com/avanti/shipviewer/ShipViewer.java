package com.avanti.shipviewer;

import com.avanti.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;

public class ShipViewer extends Game {
	
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
	public static final String version = "0.1";
	public static AssetManager assetManager;
	
	private FPSLogger fpsLogger;
	public static FileHandle selectedShip;
	public static Color selectedColor;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		setScreen(new SplashScreen(this));
		SCREEN_WIDTH = Gdx.graphics.getWidth()/Gdx.graphics.getPpiX();
		SCREEN_HEIGHT = Gdx.graphics.getHeight()/Gdx.graphics.getPpiY();
		fpsLogger = new FPSLogger();
	}

	@Override
	public void render () {
		super.render();
		fpsLogger.log();
	}
}
