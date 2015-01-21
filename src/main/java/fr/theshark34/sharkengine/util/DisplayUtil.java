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
package fr.theshark34.sharkengine.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * A util class for display
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class DisplayUtil {

	/**
	 * All modes sorted
	 */
	private static ArrayList<DisplayMode> sortedModes;

	public static void setDisplayModeAndFullscreen() throws LWJGLException {
		// Sorting display modes
		sortDisplayModes();

		// Picking the highest display mode in fullscreen
		DisplayMode displayMode = sortedModes.get(sortedModes.size() - 1);
		Display.setDisplayModeAndFullscreen(displayMode);
	}

	/**
	 * Sorts all display modes
	 * 
	 * @throws LWJGLException
	 *             Can be throwed by Display.getAvailableDisplayModes()
	 */
	private static void sortDisplayModes() throws LWJGLException {
		// Creating an array list containing all display modes
		ArrayList<DisplayMode> modes = new ArrayList<DisplayMode>(
				Arrays.asList(Display.getAvailableDisplayModes()));

		// Initializing the final array list
		sortedModes = new ArrayList<DisplayMode>();

		// While the modes list isn't empty
		while (true) {
			DisplayMode littlestDisplayMode = null;
			int littlestDisplayModeIndex = -1;

			// For each display modes in the non-sorted list, it will find the
			// littlest display mode
			for (int i = 0; i < modes.size(); i++) {
				if (littlestDisplayMode == null) {
					littlestDisplayMode = modes.get(i);
					littlestDisplayModeIndex = i;
				} else if (modes.get(i).getWidth() < littlestDisplayMode
						.getWidth()) {
					littlestDisplayMode = modes.get(i);
					littlestDisplayModeIndex = i;
				} else if (modes.get(i).getWidth() == littlestDisplayMode
						.getWidth())
					if (modes.get(i).getHeight() < littlestDisplayMode
							.getHeight()) {
						littlestDisplayMode = modes.get(i);
						littlestDisplayModeIndex = i;
					} else if (modes.get(i).getHeight() == littlestDisplayMode
							.getHeight())
						if (modes.get(i).getBitsPerPixel() < littlestDisplayMode
								.getBitsPerPixel()) {
							littlestDisplayMode = modes.get(i);
							littlestDisplayModeIndex = i;
						} else if (modes.get(i).getBitsPerPixel() == littlestDisplayMode
								.getBitsPerPixel())
							if (modes.get(i).getFrequency() < littlestDisplayMode
									.getFrequency()) {
								littlestDisplayMode = modes.get(i);
								littlestDisplayModeIndex = i;
							}
			}

			// Checking if the list isn't finished
			if (littlestDisplayModeIndex == -1)
				break;

			// Adding the display mode to the sorted list and remove it of the
			// non-sorted list
			modes.remove(littlestDisplayModeIndex);
			sortedModes.add(littlestDisplayMode);
			littlestDisplayMode = null;
		}
	}

	/**
	 * Return an {@link ArrayList} of sorted display modes
	 * 
	 * @return An ArrayList of sorted display modes
	 */
	public static ArrayList<DisplayMode> getDisplayModes() {
		return sortedModes;
	}

}
