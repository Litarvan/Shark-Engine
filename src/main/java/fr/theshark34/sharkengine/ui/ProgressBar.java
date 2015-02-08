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

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * A simple progress bar
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class ProgressBar extends Component {

	/**
	 * The progress bar maximum (100%) value
	 */
	private int maximum;

	/**
	 * The progress bar value
	 */
	private int value;

	/**
	 * The bar main color
	 */
	private Color color;

	/**
	 * The bar foreground color
	 */
	private Color fgColor;

	/**
	 * The text color
	 */
	private Color textColor;

	/**
	 * The string on the bar
	 */
	private String string;

	/**
	 * The base Arial font set by default
	 */
	public static final UnicodeFont BASE_FONT = new UnicodeFont(new Font(
			"Arial", Font.PLAIN, 18), 18, true, false);

	/**
	 * The font of the button's text (by default BASE_FONT)
	 */
	private UnicodeFont font = BASE_FONT;

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
	 * @param textColor
	 *            The text color
	 */
	public ProgressBar(int x, int y, int width, int height, Color textColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textColor = textColor;
		this.color = new Color(1.0F, 1.0F, 1.0F, 0.2F);
		createFgColor();
		this.string = "";
		initFont();
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
	 * @param textColor
	 *            The text color
	 * @param color
	 *            The bar color
	 */
	public ProgressBar(int x, int y, int width, int height, Color textColor,
			Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textColor = textColor;
		this.color = color;
		createFgColor();
		this.string = "";
		initFont();
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
	 * @param textColor
	 *            The text color
	 * @param color
	 *            The bar color
	 * @param fgColor
	 *            The foreground color
	 */
	public ProgressBar(int x, int y, int width, int height, Color textColor,
			Color color, Color fgColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textColor = textColor;
		this.color = color;
		this.fgColor = fgColor;
		this.string = "";
		initFont();
	}

	/**
	 * Draw the bar
	 */
	@Override
	public void draw() {
		// Enabling blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// Being sure that texture is disabled
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Picking the base color
		GL11.glColor4f((float) color.getRed() / 255,
				(float) color.getGreen() / 255, (float) color.getBlue() / 255,
				(float) color.getAlpha() / 255);

		// Drawing the base
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + width, y);
			GL11.glVertex2f(x + width, y + height);
			GL11.glVertex2f(x, y + height);
		}
		GL11.glEnd();

		if (value != 0 && maximum != 0) {
			// Picking the foreground color
			GL11.glColor4f((float) fgColor.getRed() / 255,
					(float) fgColor.getGreen() / 255,
					(float) fgColor.getBlue() / 255,
					(float) fgColor.getAlpha() / 255);

			int fgWidth = (int) (width / ((float) maximum / (float) value));

			// Drawing the foreground
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex2f(x, y);
				GL11.glVertex2f(x + fgWidth, y);
				GL11.glVertex2f(x + fgWidth, y + height);
				GL11.glVertex2f(x, y + height);
			}
			GL11.glEnd();
		}

		// Drawing the text
		this.font.drawString(x + (this.width - this.font.getWidth(string)) / 2,
				y + (this.height - this.font.getHeight(string)) / 2, string);

		// Disabling blending
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Return the bar maximum value (100%)
	 * 
	 * @return The bar maximum
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * Set the bar maximum value (100%)
	 * 
	 * @param maximum
	 *            The new bar maximum
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	/**
	 * Return the bar value
	 * 
	 * @return The bar value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the bar value
	 * 
	 * @param value
	 *            The new bar value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Return the string on the bar
	 * 
	 * @return The string on the bar
	 */
	public String getString() {
		return this.string;
	}

	/**
	 * Set a new string on the bar
	 * 
	 * @param str
	 *            The new string
	 */
	public void setString(String str) {
		this.string = str;
	}

	/**
	 * Return the color of the bar
	 * 
	 * @return The color of the bar
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set a new bar color
	 * 
	 * @param color
	 *            The new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Return the foreground color
	 * 
	 * @return The foreground color
	 */
	public Color getFgColor() {
		return fgColor;
	}

	/**
	 * Set a new foreground color
	 * 
	 * @param fgColor
	 *            The new foreground color
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	/**
	 * Return the text color
	 * 
	 * @return The text color
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * Set a new text color
	 * 
	 * @param textColor
	 *            The new text color
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
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
	 * Try to create a foreground color, it cans sometimes be ugly
	 */
	private void createFgColor() {
		// If the color is white
		if (this.color.getRed() == 255 && this.color.getGreen() == 255
				&& this.color.getBlue() == 255)
			// If the alpha is less than 235
			if (this.color.getAlpha() < 235)
				// Setting the color a little more opaque than the base color
				this.fgColor = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() + 20);
			else
				// Else the color a little more transparent than the base color
				this.fgColor = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() - 20);
		else
			// Else setting the color a little brighter than the base color
			this.fgColor = new Color(color.getRed() + 15,
					color.getGreen() + 15, color.getBlue() + 15);
		// If the red is to high
		if (this.fgColor.getRed() > 255)
			// Reducing it
			this.fgColor = new Color(this.fgColor.getRed() - 30,
					this.fgColor.getGreen(), this.fgColor.getBlue());
		// If the green is to high
		if (this.fgColor.getGreen() > 255)
			// Reducing it
			this.fgColor = new Color(this.fgColor.getRed(),
					this.fgColor.getGreen() - 30, this.fgColor.getBlue());
		// If the blue is to high
		if (this.fgColor.getBlue() > 255)
			// Reducing it
			this.fgColor = new Color(this.fgColor.getRed(),
					this.fgColor.getGreen(), this.fgColor.getBlue() - 30);
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
}
