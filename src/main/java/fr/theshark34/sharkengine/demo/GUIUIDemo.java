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

import fr.theshark34.sharkengine.ui.GUI;
import fr.theshark34.sharkengine.ui.components.Button;
import fr.theshark34.sharkengine.ui.components.Checkbox;
import fr.theshark34.sharkengine.ui.components.HorizontalSlider;
import fr.theshark34.sharkengine.ui.components.ProgressBar;
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
	 * A simple checkbox
	 */
	private Checkbox simpleCB;

	/**
	 * A simple horizontal slider
	 */
	private HorizontalSlider simpleHZ;

	/**
	 * The time to update the progressbar
	 */
	private long time;

	/**
	 * Init the GUI
	 */
	@Override
	public void init() {
		// Initializing the simple button
		simpleButton = new Button(50, 50, 175, 60, "Simple Button", null,
				new ButtonAction() {
					@Override
					public void buttonClicked() {
						simpleButton.setText("Clicked !");
					}
				});
		
		// Initializing the simple progress bar
		simplePB = new ProgressBar(50, 125, 350, 30, null);
		simplePB.setString("Simple Progress Bar");
		simplePB.setMaximum(100);
		time = System.currentTimeMillis();
		
		// Initializing the simple checkbox
		simpleCB = new Checkbox(50, 175, "Simple Checkbox", null);
		
		// Initializing the simple horizontal slider
		simpleHZ = new HorizontalSlider(50, 205, 350, 25);
	}

	/**
	 * Draw the GUI
	 */
	@Override
	public void draw() {
		// Drawing the simple button
		simpleButton.draw();

		// Drawing the simple progress bar
		simplePB.draw();
		if (System.currentTimeMillis() - time >= 50) {
			if (simplePB.getValue() == 100)
				simplePB.setValue(0);
			else
				simplePB.setValue(simplePB.getValue() + 1);
			time = System.currentTimeMillis();
		}

		// Drawing the simple checkbox
		simpleCB.draw();
		
		// Drawing the simple horizontal slider
		simpleHZ.draw();
	}

}
