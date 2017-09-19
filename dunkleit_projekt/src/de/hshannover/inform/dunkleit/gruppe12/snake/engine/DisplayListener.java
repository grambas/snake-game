package de.hshannover.inform.dunkleit.gruppe12.snake.engine;


/**
 * <h1>DisplayListener</h1> Used to proper update gameobject
 * @author Mindaugas Milius
 * @version 1.0
 */

public interface DisplayListener {
	
	/**
	 * Update method must be implemented
	 * @param e GameEvent
	 * @see GameEvent
	 * @see GameBody
	 */
	public void update(GameEvent e);
}