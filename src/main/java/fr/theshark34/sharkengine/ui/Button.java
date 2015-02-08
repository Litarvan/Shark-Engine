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
package fr.theshark34.sharkengine.ui;

import java.awt.Color;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import fr.theshark34.sharkengine.ui.util.ButtonAction;

/**
 * The Button component
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Button extends Component {

	/**
	 * The text displayed on the center of the button
	 */
	private String text;

	/**
	 * The button's color (by default white 80% transparent)
	 */
	private Color color;

	/**
	 * The button's color when the button is hover (by default created by the
	 * createColorHover() method)
	 */
	private Color colorHover;

	/**
	 * The text's color
	 */
	private Color textColor;

	/**
	 * A {@link ButtonAction} executed after that the button was clicked
	 */
	private ButtonAction action;

	/**
	 * If the button is clicked
	 */
	private boolean clicked = false;

	/**
	 * The base Arial font set by default
	 */
	public static final UnicodeFont BASE_FONT = new UnicodeFont(new Font(
			"Arial", Font.BOLD, 22), 22, true, false);

	/**
	 * The font of the button's text (by default BASE_FONT)
	 */
	private UnicodeFont font = BASE_FONT;

	/**
	 * Lightest constructor
	 * 
	 * @param x
	 *            The button's x pos
	 * @param y
	 *            The button's y pos
	 * @param width
	 *            The button's width
	 * @param height
	 *            The button's height
	 * @param text
	 *            The button's text displayed at the center of it
	 * @param textColor
	 *            The color of the text
	 * @param action
	 *            A {@link ButtonAction} executed after that the button was
	 *            clicked
	 */
	public Button(int x, int y, int width, int height, String text,
			Color textColor, ButtonAction action) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.textColor = textColor;
		this.action = action;
		this.color = new Color(1.0F, 1.0F, 1.0F, 0.2F);
		createColorHover();
		if (textColor != null)
			this.textColor = textColor;
		else
			this.textColor = new Color(255, 255, 255);
		initFont();
	}

	/**
	 * Light constructor
	 * 
	 * @param x
	 *            The button's x pos
	 * @param y
	 *            The button's y pos
	 * @param width
	 *            The button's width
	 * @param height
	 *            The button's height
	 * @param text
	 *            The button's text displayed at the center of it
	 * @param color
	 *            The color of the button
	 * @param textColor
	 *            The color of the text
	 * @param action
	 *            A {@link ButtonAction} executed after that the button was
	 *            clicked
	 */
	public Button(int x, int y, int width, int height, String text,
			Color color, Color textColor, ButtonAction action) {
		// Setting things
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.textColor = textColor;
		this.action = action;
		this.color = color;

		// Creating a color when the mouse is on the button
		createColorHover();

		// Setting the text color, by default white
		if (textColor != null)
			this.textColor = textColor;
		else
			this.textColor = new Color(255, 255, 255);

		// Initializing the font
		initFont();
	}

	/**
	 * Normal constructor
	 * 
	 * @param x
	 *            The button's x pos
	 * @param y
	 *            The button's y pos
	 * @param width
	 *            The button's width
	 * @param height
	 *            The button's height
	 * @param text
	 *            The button's text displayed at the center of it
	 * @param color
	 *            The color of the button
	 * @param colorHover
	 *            The color of the button when the mouse is on it
	 * @param textColor
	 *            The color of the text
	 * @param action
	 *            A {@link ButtonAction} executed after that the button was
	 *            clicked
	 */
	public Button(int x, int y, int width, int height, String text,
			Color color, Color colorHover, Color textColor, ButtonAction action) {
		// Setting things
		this.x = x;
		this.y = y;
		this.text = text;
		this.textColor = textColor;
		this.color = color;
		this.colorHover = colorHover;
		this.action = action;

		// Creating a color when the mouse is on the button
		createColorHover();

		// Setting the text color, by default white
		if (textColor != null)
			this.textColor = textColor;
		else
			this.textColor = new Color(255, 255, 255);

		// Initializing the font
		initFont();
	}

	/**
	 * Draw the button
	 */
	@Override
	public void draw() {
		// Enabling blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// Check if the mouse is on the button
		if (Mouse.getX() > this.x && Mouse.getX() < this.x + this.width
				&& Mouse.getY() < Display.getHeight() - this.y
				&& Mouse.getY() > Display.getHeight() - this.y - this.height) {

			// Changing button's color to colorHover
			GL11.glColor4f((float) colorHover.getRed() / 255,
					(float) colorHover.getGreen() / 255,
					(float) colorHover.getBlue() / 255,
					(float) colorHover.getAlpha() / 255);

			// If the mouse clicked and clicked is false, executing action
			// and setting clicked to true, then the action will not be
			// repeated
			if (Mouse.isButtonDown(0)) {
				if (!clicked) {
					clicked = true;
					action.buttonClicked();
				}
			} else
				// If mouse isn't on it, setting clicked to false
				clicked = false;
		} else
			// Else, setting the color to the base color
			GL11.glColor4f((float) color.getRed() / 255,
					(float) color.getGreen() / 255,
					(float) color.getBlue() / 255,
					(float) color.getAlpha() / 255);

		// Drawing the button's base (a rectangle)
		GL11.glBegin(GL11.GL_QUADS);
		{

			GL11.glTexCoord2f(0.0f, 0.0f);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(1.0f, 0.0f);
			GL11.glVertex2f(x + width, y);
			GL11.glTexCoord2f(1.0f, 1.0f);
			GL11.glVertex2f(x + width, y + height);
			GL11.glTexCoord2f(0.0f, 1.0f);
			GL11.glVertex2f(x, y + height);
		}
		GL11.glEnd();

		// Drawing the text
		this.font.drawString(x + (this.width - this.font.getWidth(text)) / 2, y
				+ (this.height - this.font.getHeight(text)) / 2, text);

		// Disabling blending
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Set a new font for the text drawed in the center
	 * 
	 * @param font
	 *            The new font
	 */
	public void setFont(UnicodeFont font) {
		this.font = font;
		initFont();
	}

	/**
	 * Return the font of the text drawed in the center
	 * 
	 * @return
	 */
	public UnicodeFont getFont() {
		return this.font;
	}

	/**
	 * Init the font
	 */
	@SuppressWarnings("unchecked")
	private void initFont() {
		this.font.addAsciiGlyphs();
		this.font.addGlyphs(400, 600);
		this.font.getEffects().add(new ColorEffect(textColor));
		try {
			this.font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Try to create a color when the mouse in on the button from the base
	 * button's color, it cans sometimes be ugly
	 */
	private void createColorHover() {
		// If the color is white
		if (this.color.getRed() == 255 && this.color.getGreen() == 255
				&& this.color.getBlue() == 255)
			// If the alpha is less than 235
			if (this.color.getAlpha() < 235)
				// Setting the color a little more opaque than the base color
				this.colorHover = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() + 20);
			else
				// Else the color a little more transparent than the base color
				this.colorHover = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() - 20);
		else
			// Else setting the color a little brighter than the base color
			this.colorHover = new Color(color.getRed() + 15,
					color.getGreen() + 15, color.getBlue() + 15);
		// If the red is to high
		if (this.colorHover.getRed() > 255)
			// Reducing it
			this.colorHover = new Color(this.colorHover.getRed() - 30,
					this.colorHover.getGreen(), this.colorHover.getBlue());
		// If the green is to high
		if (this.colorHover.getGreen() > 255)
			// Reducing it
			this.colorHover = new Color(this.colorHover.getRed(),
					this.colorHover.getGreen() - 30, this.colorHover.getBlue());
		// If the blue is to high
		if (this.colorHover.getBlue() > 255)
			// Reducing it
			this.colorHover = new Color(this.colorHover.getRed(),
					this.colorHover.getGreen(), this.colorHover.getBlue() - 30);
	}

}
