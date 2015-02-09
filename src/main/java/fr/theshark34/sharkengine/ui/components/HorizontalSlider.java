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
package fr.theshark34.sharkengine.ui.components;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * The horizontal slider component
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class HorizontalSlider extends Component {

	/**
	 * The slider background color
	 */
	private Color color;

	/**
	 * The slider color
	 */
	private Color sliderColor;

	/**
	 * The slider width
	 */
	private int sliderWidth;

	/**
	 * The slider x pos
	 */
	private int sliderX;

	/**
	 * If the mouse clicked the slider
	 */
	private boolean clicked;

	/**
	 * The mouse click point
	 */
	private int mouseClick;

	/**
	 * Lightest constructor
	 * 
	 * @param x
	 *            The x pos
	 * @param y
	 *            The y pos
	 * @param width
	 *            The width
	 * @param height
	 *            The height
	 */
	public HorizontalSlider(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color(1.0F, 1.0F, 1.0F, 0.2F);
		createSliderColor();
		this.sliderWidth = 150;
	}

	/**
	 * Normal constructor
	 * 
	 * @param x
	 *            The x pos
	 * @param y
	 *            The y pos
	 * @param width
	 *            The width
	 * @param height
	 *            The height
	 * @param color
	 *            The slider background color
	 */
	public HorizontalSlider(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		createSliderColor();
		this.sliderWidth = 150;
	}

	/**
	 * Full constructor
	 * 
	 * @param x
	 *            The x pos
	 * @param y
	 *            The y pos
	 * @param width
	 *            The width
	 * @param height
	 *            The height
	 * @param color
	 *            The slider background color
	 * @param sliderColor
	 *            The slider color
	 */
	public HorizontalSlider(int x, int y, int width, int height, Color color,
			Color sliderColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.sliderColor = sliderColor;
		this.sliderWidth = 150;
	}

	/**
	 * Try to create a color when the mouse in on the button from the base
	 * button color, it cans sometimes be ugly
	 */
	private void createSliderColor() {
		// If the color is white
		if (this.color.getRed() == 255 && this.color.getGreen() == 255
				&& this.color.getBlue() == 255)
			// If the alpha is less than 235
			if (this.color.getAlpha() < 235)
				// Setting the color a little more opaque than the base color
				this.sliderColor = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() + 20);
			else
				// Else the color a little more transparent than the base color
				this.sliderColor = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() - 20);
		else
			// Else setting the color a little brighter than the base color
			this.sliderColor = new Color(color.getRed() + 15,
					color.getGreen() + 15, color.getBlue() + 15);
		// If the red is to high
		if (this.sliderColor.getRed() > 255)
			// Reducing it
			this.sliderColor = new Color(this.sliderColor.getRed() - 30,
					this.sliderColor.getGreen(), this.sliderColor.getBlue());
		// If the green is to high
		if (this.sliderColor.getGreen() > 255)
			// Reducing it
			this.sliderColor = new Color(this.sliderColor.getRed(),
					this.sliderColor.getGreen() - 30,
					this.sliderColor.getBlue());
		// If the blue is to high
		if (this.sliderColor.getBlue() > 255)
			// Reducing it
			this.sliderColor = new Color(this.sliderColor.getRed(),
					this.sliderColor.getGreen(),
					this.sliderColor.getBlue() - 30);
	}

	/**
	 * Draw the slider
	 */
	@Override
	public void draw() {
		// Enabling blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// Being sure that texturing is disabled
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Check if the mouse is on the slider
		if (Mouse.getX() > this.x + this.sliderX
				&& Mouse.getX() < this.x + this.sliderX + this.sliderWidth
				&& Mouse.getY() < Display.getHeight() - this.y
				&& Mouse.getY() > Display.getHeight() - this.y - this.height) {
			// If the mouse clicked and clicked is false, settings clicked to
			// true and saving mouse initial click
			if (Mouse.isButtonDown(0)) {
				if (!clicked) {
					clicked = true;
					mouseClick = Mouse.getX();
				}
			} else
				// If mouse isn't on it, setting clicked to false
				clicked = false;
		}
		// Setting clicked to false only when the mouse stop clicking
		else if (!Mouse.isButtonDown(0))
			clicked = false;

		// If mouse clicked
		if (clicked) {
			// If slider isn't on the minimum / maximum
			if (sliderX >= 0 && sliderX + sliderWidth <= width)
				if (sliderX + Mouse.getX() - mouseClick >= 0)
					if (sliderX + sliderWidth + Mouse.getX() - mouseClick <= width) {
						sliderX += Mouse.getX() - mouseClick;
						mouseClick = Mouse.getX();
					} else
						sliderX = width - sliderWidth;
				else
					sliderX = 0;
		}

		// Picking the background color
		GL11.glColor4f((float) color.getRed() / 255,
				(float) color.getGreen() / 255, (float) color.getBlue() / 255,
				(float) color.getAlpha() / 255);

		// Drawing the slider background
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + width, y);
			GL11.glVertex2f(x + width, y + height);
			GL11.glVertex2f(x, y + height);
		}
		GL11.glEnd();

		// Picking the slider color
		GL11.glColor4f((float) sliderColor.getRed() / 255,
				(float) sliderColor.getGreen() / 255,
				(float) sliderColor.getBlue() / 255,
				(float) sliderColor.getAlpha() / 255);

		// Drawing the slider
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x + sliderX, y);
			GL11.glVertex2f(x + sliderX + sliderWidth, y);
			GL11.glVertex2f(x + sliderX + sliderWidth, y + height);
			GL11.glVertex2f(x + sliderX, y + height);
		}
		GL11.glEnd();
	}

	/**
	 * Return the slider background color
	 * 
	 * @return The slider background color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set a new background color
	 * 
	 * @param color
	 *            The new background color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Return the slider color
	 * 
	 * @return The slider color
	 */
	public Color getSliderColor() {
		return sliderColor;
	}

	/**
	 * Set a new slider color
	 * 
	 * @param sliderColor
	 *            The new slider color
	 */
	public void setSliderColor(Color sliderColor) {
		this.sliderColor = sliderColor;
	}

	/**
	 * Return the slider width (by default 100)
	 * 
	 * @return The slider width
	 */
	public int getSliderWidth() {
		return sliderWidth;
	}

	/**
	 * Set a new slider width (by default 100)
	 * 
	 * @param sliderWidth
	 *            The new slider width
	 */
	public void setSliderWidth(int sliderWidth) {
		this.sliderWidth = sliderWidth;
	}

	/**
	 * Return the slider X from 0 to width - sliderWidth
	 * 
	 * @return The slider X
	 */
	public int getSliderX() {
		return sliderX;
	}

}
