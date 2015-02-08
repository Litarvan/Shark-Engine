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
import java.awt.Font;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import fr.theshark34.sharkengine.util.ErrorUtil;

/**
 * The Checkbox component
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Checkbox extends Component {

	/**
	 * The text of the checkbox
	 */
	private String text;

	/**
	 * The color of the text
	 */
	private Color textColor;

	/**
	 * The color of the checkbox
	 */
	private Color color;

	/**
	 * If the mouse clicked it
	 */
	private boolean clicked;

	/**
	 * If the checkbox is coched
	 */
	private boolean coched;

	/**
	 * The base Arial font set by default
	 */
	public static final UnicodeFont BASE_FONT = new UnicodeFont(new Font(
			"Arial", Font.PLAIN, 18), 18, true, false);

	/**
	 * The font of the text (by default BASE_FONT)
	 */
	private UnicodeFont font = BASE_FONT;

	private static Texture cochedcb;

	/**
	 * Light constructor
	 * 
	 * @param x
	 *            The x pos
	 * @param y
	 *            The y pos
	 * @param text
	 *            The text
	 * @param textColor
	 *            The color of the text
	 */
	public Checkbox(int x, int y, String text, Color textColor) {
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		this.text = text;
		this.textColor = textColor;
		this.color = Color.WHITE;
		initFont();
		if (cochedcb == null)
			try {
				cochedcb = TextureLoader.getTexture("PNG", ResourceLoader
						.getResourceAsStream("res/ui/cbcoched.png"));
			} catch (IOException e) {
				ErrorUtil.catchError(e);
			}
	}

	/**
	 * Full constructor
	 * 
	 * @param x
	 *            The x pos
	 * @param y
	 *            The y pos
	 * @param text
	 *            The text
	 * @param textColor
	 *            The color of the text
	 * @param color
	 *            The color of the checkbox
	 */
	public Checkbox(int x, int y, String text, Color textColor, Color color) {
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		this.text = text;
		this.textColor = textColor;
		this.color = color;
		initFont();
		if (cochedcb == null)
			try {
				cochedcb = TextureLoader.getTexture("PNG", ResourceLoader
						.getResourceAsStream("res/ui/cbcoched.png"));
			} catch (IOException e) {
				ErrorUtil.catchError(e);
			}
	}

	/**
	 * Draw the checkbox
	 */
	public void draw() {
		// Enabling blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// Being sure that texturing is disabled
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Picking white color
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		// Drawing the checkbox
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + width, y);
			GL11.glVertex2f(x + width, y + height);
			GL11.glVertex2f(x, y + height);
		}
		GL11.glEnd();

		// Drawing the text
		this.font.drawString(x + 25,
				y + (this.height - this.font.getHeight(text)) / 2, text);

		// Being sure that texturing is enabled
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// If the mouse is on the checkbox
		if (Mouse.getX() > this.x && Mouse.getX() < this.x + this.width
				&& Mouse.getY() < Display.getHeight() - this.y
				&& Mouse.getY() > Display.getHeight() - this.y - this.height)
			// If the mouse clicked and clicked is false, setting coched
			// to its oposite and setting clicked to true, so it will do
			// it one time
			if (Mouse.isButtonDown(0)) {
				if (!clicked) {
					clicked = true;
					coched = !coched;
				}
			} else
				// If mouse isn't on it, setting clicked to false
				clicked = false;

		// Checking if the checkbox is coched
		if (coched) {
			// Drawing coched checkbox texture
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, cochedcb.getTextureID());

			// Drawing the image
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
		}

		// Disabling texture
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		// Disabling blending
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Return the checkbox text
	 * 
	 * @return The checkbox text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set a new checkbox text
	 * 
	 * @param text
	 *            The new checkbox text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Return the text color
	 * 
	 * @return The new text color
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
	 * Return the checkbox color
	 * 
	 * @return The checkbox color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set a new checkbox color
	 * 
	 * @param color
	 *            The new checkbox color
	 */
	public void setColor(Color color) {
		this.color = color;
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
	 * @return The font of the text
	 */
	public UnicodeFont getFont() {
		return this.font;
	}

	/**
	 * Return if the checkbox is coched
	 * 
	 * @return If the checkbox is coched
	 */
	public boolean isCoched() {
		return coched;
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
