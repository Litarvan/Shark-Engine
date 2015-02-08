/*
 * Copyright 2015 TheShark34
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package fr.theshark34.sharkengine.demo;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.ResourceLoader;

import fr.theshark34.sharkengine.Game;
import fr.theshark34.sharkengine.ui.GUI;
import fr.theshark34.sharkengine.ui.Image;
import fr.theshark34.sharkengine.util.ErrorUtil;

/**
 * A simple splash GUI for the demo
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class GUISplash extends GUI {

	/**
	 * The splash image
	 */
	private Image splash;

	/**
	 * The time in milliseconds saved when the GUI was initialized
	 */
	private long time;

	@Override
	public void init() {
		// Initializing the big splash logo
		try {
			splash = new Image(0, 0,
					ResourceLoader.getResourceAsStream("demo/splash.png"));
			splash.setX(Display.getWidth() / 2 - splash.getWidth() / 2);
			splash.setY(Display.getHeight() / 2 - splash.getHeight() / 2);
		} catch (IOException e) {
			ErrorUtil.catchError(e);
		}

		// Saving time
		time = System.currentTimeMillis();
	}

	@Override
	public void draw() {
		// Drawing the big splash logo
		splash.draw();

		// Checking if we displayed this GUI 2.5 second ago so we would display
		// the main menu
		if (System.currentTimeMillis() - time >= 2500L)
			Game.setCurrentGUI(new GUIMenu());
	}

}
