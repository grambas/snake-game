package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

/**
 * <h1>GameEvent</h1> Holds map and current state.
 * Will be created for DisplayListener
 * @author Mindaugas Milius
 * @version 1.0
 */

public class GameEvent {
	private Map m_map;
	private int m_state;
	
	
	/**
	 * Constructs new GameEvent with game map and state parameters
	 * Will be created in DisplayListener
	 * @param map current Map object
	 * @param state current game state object
	 */
	public GameEvent(Map map, int state){
		this.m_map = map;
		this.m_state = state;
	}
	
	/**
	 * Returns Map current object
	 * @return Map
	 */
	public Map getMap(){
		return this.m_map;
	}
	
	/**
	 * Returns Game current object
	 * @return int
	 * @see SnakeConsts
	 */
	public int getState(){
		return this.m_state;
	}
}