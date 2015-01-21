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

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * A textured quad
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Image extends Component {

	/**
	 * The texture
	 */
	private Texture texture;

	/**
	 * Init the image
	 * 
	 * @param is
	 *            An input stream of a png file HEIGHT AND WIDTH IT NEED TO BE
	 *            2/4/8/16/32/64/128/512/1024/etc... but you can scale it by
	 *            setting a width and a height with the second constructor
	 * @throws IOException
	 *             If it fails to load the image
	 */
	public Image(int x, int y, InputStream is) throws IOException {
		// Check if the input stream isn't null
		if (is == null)
			throw new IllegalArgumentException("is == null !");

		// Setting things
		this.texture = TextureLoader.getTexture("PNG", is);
		this.x = x;
		this.y = y;
		this.width = texture.getTextureWidth();
		this.height = texture.getTextureHeight();
	}

	/**
	 * Init the image width predefined width and height
	 * 
	 * @param is
	 *            An input stream of a png file HEIGHT AND WIDTH IT NEED TO BE
	 *            2/4/8/16/32/64/128/512/1024/etc... but you can scale it by
	 *            setting a width and a height
	 * @throws IOException
	 *             If it fails to load the image
	 */
	public Image(int x, int y, int width, int height, InputStream is)
			throws IOException {
		// Check if the input stream isn't null
		if (is == null)
			throw new IllegalArgumentException("is == null !");

		// Setting things
		this.texture = TextureLoader.getTexture("PNG", is);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Draw it
	 */
	@Override
	public void draw() {
		// Without this, image can render strangely
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		// Enabling blend and texture
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

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

		// Disabling blend and texture
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	/**
	 * Return the texture field
	 * 
	 * @return The texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Set a new texture
	 * 
	 * @param texture
	 *            The new texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
