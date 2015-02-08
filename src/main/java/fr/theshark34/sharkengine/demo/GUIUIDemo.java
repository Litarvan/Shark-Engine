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

import fr.theshark34.sharkengine.ui.Button;
import fr.theshark34.sharkengine.ui.GUI;
import fr.theshark34.sharkengine.ui.util.ButtonAction;

/**
 * The UI demo GUI
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class GUIUIDemo extends GUI {

	private Button simpleButton;

	/**
	 * Init the GUI
	 */
	@Override
	public void init() {
		simpleButton = new Button(50, 50, 150, 60, "UI Demo", null,
				new ButtonAction() {
					@Override
					public void buttonClicked() {
					}
				});
	}

	/**
	 * Draw the GUI
	 */
	@Override
	public void draw() {
		simpleButton.draw();
	}

}
