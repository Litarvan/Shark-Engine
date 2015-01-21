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
package fr.theshark34.sharkengine;

import java.awt.image.BufferedImage;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import fr.theshark34.sharkengine.gui.GUI;
import fr.theshark34.sharkengine.util.DisplayUtil;
import fr.theshark34.sharkengine.util.ErrorUtil;

/**
 * The main Game class
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Game {

	/**
	 * If the Game is running
	 */
	private static boolean running;

	/**
	 * The Game's name
	 */
	private static String name;

	/**
	 * The title of the Game's window
	 */
	private static String title;

	/**
	 * The icon of the Game's window
	 */
	private static BufferedImage icon;

	/**
	 * The current displayed {@link GUI}
	 */
	private static GUI currentGUI;

	/**
	 * The clear color (= background color)
	 */
	private static Color clearColor;

	/**
	 * Create the Game
	 * 
	 * @param name
	 *            The name of the Game
	 * @param title
	 *            The title of the Game window
	 * @param icon
	 *            The icon of the Game window
	 * @param firstGUI
	 *            The first displayed GUI
	 * @param clearColor
	 *            The clear color (= background color)
	 */
	public static void create(final String name, final String title,
			final BufferedImage icon, GUI firstGUI, Color clearColor) {
		try {
			// Setting up things
			Game.name = name;
			Game.title = title;
			Game.icon = icon;
			Game.clearColor = clearColor;
			running = true;

			// Creating the Display, the Mouse and the Keyboard
			DisplayUtil.setDisplayModeAndFullscreen();
			Display.setTitle(title);
			Display.create();
			Mouse.create();
			Keyboard.create();

			// Initializing OpenGL
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			// Displaying the first GUI
			setCurrentGUI(firstGUI);

			// Start the main loop
			while (running)
				update();

			// Destroy all
			Display.destroy();
			Mouse.destroy();
			Keyboard.destroy();
		} catch (LWJGLException e) {
			ErrorUtil.catchError(e);
		}
	}

	/**
	 * The current clear color (= background color)
	 * 
	 * @return The current clear color
	 */
	public static Color getClearColor() {
		return clearColor;
	}

	/**
	 * Set a new clear color (= background color)
	 * 
	 * @param clearColor
	 *            The new clear color
	 */
	public static void setClearColor(Color clearColor) {
		Game.clearColor = clearColor;
	}

	/**
	 * Update the window, called by the main loop of the create() method
	 */
	private static void update() {
		// Clear all
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor((float) clearColor.getRed() / 255,
				(float) clearColor.getGreen() / 255,
				(float) clearColor.getBlue() / 255,
				(float) clearColor.getAlpha() / 255);

		// Draw the current GUI
		if (currentGUI != null)
			currentGUI.draw();

		// Check if user requested close
		if (Display.isCloseRequested())
			stop();

		// Update the Display
		Display.update();
	}

	/**
	 * Stop the Game by setting running to false
	 */
	public static void stop() {
		running = false;
	}

	/**
	 * The Game's name set in the create() method
	 * 
	 * @return The Game's name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * The title of the Game's window set in the create() method
	 * 
	 * @return The title of the Game's window
	 */
	public static String getTitle() {
		return title;
	}

	/**
	 * Set a new title for the Game's window
	 * 
	 * @param title
	 *            The new title
	 */
	public static void setTitle(String title) {
		Game.title = title;
		Display.setTitle(title);
	}

	/**
	 * The icon of the Game's window set in the create() method
	 * 
	 * @return The icon of the Game's window
	 */
	public static BufferedImage getIcon() {
		return icon;
	}

	/**
	 * If the game is running
	 * 
	 * @return If the game is running
	 */
	public static boolean isRunning() {
		return running;
	}

	/**
	 * Display a new GUI
	 * 
	 * @param g
	 *            The new GUI to display
	 */
	public static void setCurrentGUI(GUI g) {
		if (g == null)
			throw new IllegalArgumentException("g == null !");
		g.init();
		currentGUI = g;
	}

}
