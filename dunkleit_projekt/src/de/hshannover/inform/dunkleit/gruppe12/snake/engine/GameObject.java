/**
 * 
 */
package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

/**
 * <h1>GameObject Model</h1> Class to hold game objects
 * 
 * @author Mindaugas Milius
 * @version 1.0
 */

public abstract class GameObject {

	private boolean isAlive;

	/**
	 * GameObject constructor. By default isAlive is true
	 */
	public GameObject() {
		isAlive = true;
	}

	/**
	 * Check if GameObject is alive
	 * 
	 * @return boolean if Alive = true if not = false
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Set {@link #isAlive} variable
	 * 
	 * @param state	 boolean true if object alive
	 */
	public void setAlive(boolean state) {
		isAlive = state;
	}

}
