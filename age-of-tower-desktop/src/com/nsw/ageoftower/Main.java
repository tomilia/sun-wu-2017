package com.nsw.ageoftower;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nsx.ageoftower.AgeOfTower;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SunWuFantasy";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
 
		new LwjglApplication(new AgeOfTower(), cfg);
	}
}
