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
package fr.theshark34.sharkengine.gui;

/**
 * The GUI class
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public abstract class GUI {

	/**
	 * Init the GUI, called by the setCurrentGui method of the Game class
	 */
	public abstract void init();

	/**
	 * Draw the GUI, called by the update method of the Game class
	 */
	public abstract void draw();

}
