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

/**
 * A component base
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Component {

	/**
	 * The image x pos
	 */
	protected int x;

	/**
	 * The image y pos
	 */
	protected int y;

	/**
	 * The image width
	 */
	protected int width;

	/**
	 * The image height
	 */
	protected int height;

	/**
	 * Return the x pos
	 * 
	 * @return The x pos
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set a new x pos
	 * 
	 * @param x
	 *            The new x pos
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Return the y pos
	 * 
	 * @return The y pos
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set a new y pos
	 * 
	 * @param y
	 *            The new y pos
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Return the width
	 * 
	 * @return The width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set a new width
	 * 
	 * @param width
	 *            The new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Return the height
	 * 
	 * @return The height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set a new height
	 * 
	 * @param height
	 *            The new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Draw the component
	 */
	public void draw() {
	}

}
