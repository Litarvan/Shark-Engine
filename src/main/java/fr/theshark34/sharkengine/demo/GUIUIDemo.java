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
import fr.theshark34.sharkengine.ui.ProgressBar;
import fr.theshark34.sharkengine.ui.util.ButtonAction;

/**
 * The UI demo GUI
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class GUIUIDemo extends GUI {

	/**
	 * A simple button
	 */
	private Button simpleButton;
	
	/**
	 * A simple progress bar
	 */
	private ProgressBar simplePB;

	/**
	 * The time to update the progressbar
	 */
	private long time;
	
	/**
	 * Init the GUI
	 */
	@Override
	public void init() {
		simpleButton = new Button(50, 50, 175, 60, "Simple Button", null,
				new ButtonAction() {
					@Override
					public void buttonClicked() {
					}
				});
		simplePB = new ProgressBar(275, 65, 350, 30, null);
		simplePB.setString("This is a String !");
		simplePB.setMaximum(100);
		time = System.currentTimeMillis();
	}

	/**
	 * Draw the GUI
	 */
	@Override
	public void draw() {
		simpleButton.draw();
		simplePB.draw();
		if(System.currentTimeMillis() - time >= 50) {
			if(simplePB.getValue() == 100)
				simplePB.setValue(0);
			else
				simplePB.setValue(simplePB.getValue() + 1);
			time = System.currentTimeMillis();
		}
	}

}
