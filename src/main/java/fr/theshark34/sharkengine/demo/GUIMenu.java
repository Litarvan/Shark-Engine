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

import fr.theshark34.sharkengine.ui.Button;
import fr.theshark34.sharkengine.ui.GUI;
import fr.theshark34.sharkengine.ui.Image;
import fr.theshark34.sharkengine.ui.util.ButtonAction;
import fr.theshark34.sharkengine.util.ErrorUtil;

/**
 * The main menu GUI of the Demo
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class GUIMenu extends GUI {

	/**
	 * The big logo
	 */
	private Image logo;

	/**
	 * The "UI Demo" button
	 */
	private Button uiDemoBtn;

	@Override
	public void init() {
		// Initializing the logo
		try {
			logo = new Image(0, 0,
					ResourceLoader
							.getResourceAsStream("demo/logosharkengine.png"));
		} catch (IOException e) {
			ErrorUtil.catchError(e);
		}
		logo.setX(Display.getWidth() / 4 - logo.getWidth() / 2);
		logo.setY(Display.getHeight() / 2 - logo.getHeight() / 2);

		// Initializing the "UI Demo" button
		uiDemoBtn = new Button(Display.getWidth() / 4 + Display.getWidth() / 2
				- 50, Display.getHeight() / 6 - 30, 150, 60, "UI Demo", null,
				new ButtonAction() {
					public void buttonClicked() {
						System.out.println("clicked");
					}
				});
	}

	@Override
	public void draw() {
		// Drawing all components
		logo.draw();
		uiDemoBtn.draw();
	}

}
