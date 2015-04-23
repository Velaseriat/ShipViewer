package com.avanti.shipviewer.desktop;

import com.avanti.shipviewer.ShipViewer;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ShipViewer - " + ShipViewer.version;
		config.width = 1600;
		config.height = 900;
		//config.fullscreen = true;
		new LwjglApplication(new ShipViewer(), config);
	}
}
