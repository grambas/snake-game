package de.hshannover.inform.dunkleit.gruppe12.snake.controller;

import java.util.Observable;

/**
 * <h1>Score Model</h1> Purpose: hold current game score
 *
 * @author Mindaugas Milius
 * @version 1.0
 */

public class Score extends Observable {

	private int score;

	/**
	 * Game score constructor without parameters. Initiate score value 0
	 **/
	public Score() {
		super();
		score = 0;
	}

	/**
	 * Get current game score as Integer
	 * 
	 * @return int current game score
	 **/
	public int getValue() {
		return score;
	}

	/**
	 * Method to increase score by 1 and notify observers. Used by snake's grow function
	 **/
	public void addPoint() {
		score++;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to clear game score. Score = 0;
	 **/
	public void clear() {
		score = 0;
	}

}
