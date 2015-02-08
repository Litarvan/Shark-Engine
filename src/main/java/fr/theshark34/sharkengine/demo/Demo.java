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

import org.lwjgl.util.Color;

import fr.theshark34.sharkengine.Game;

/**
 * A little Demo
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Demo {

	public static void main(String[] args) {
		// Just create the Game named "Shark Engine Demo" with
		// "Shark Engine Demo" as window title, an icon for the window and a
		// clear color (= background color)
		Game.create("Shark Engine Demo", "Shark Engine Demo", null,
				new GUISplash(), new Color(75, 75, 75));
	}

}
