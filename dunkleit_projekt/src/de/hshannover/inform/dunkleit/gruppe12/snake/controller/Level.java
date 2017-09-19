package de.hshannover.inform.dunkleit.gruppe12.snake.controller;

import java.util.Observable;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

/**
 * <h1>Level Model</h1> 3 Difficulties based on snake speed
 *
 * @author Mindaugas Milius
 * @version 1.0
 */

public class Level extends Observable {

	private int level;

	/**
	 * Game level constructor with parameter level
	 * 
	 * @param level Game level
	 **/
	public Level(int level) {
		this.level = level;
	}

	/**
	 * Game level constructor without parameters. By Default = 0
	 **/
	public Level() {
		this.level = 0;
	}

	/**
	 * Set level using {@link SnakeConsts} and notify Observers
	 * 
	 * @param level  Game level
	 **/
	public void setValue(int level) {
		this.level = level;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return current level number
	 **/
	public int getValue() {
		return level;
	}

	/**
	 * 
	 * @return Speed for javax.swing.Timer from {@link SnakeConsts}
	 *         <p>
	 *         Note: By default or wrong {@link #level}, returns
	 *         #SnakeConsts.LEVEL1
	 *         </p>
	 * @see javax.swing.Timer
	 **/
	public int getLevelDelay() {
		if (level == 1)
			return SnakeConsts.LEVEL1;
		if (level == 2)
			return SnakeConsts.LEVEL2;
		if (level == 3)
			return SnakeConsts.LEVEL3;

		return SnakeConsts.LEVEL1;
	}

}
