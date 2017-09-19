package de.hshannover.inform.dunkleit.gruppe12.snake.controller;

import java.awt.Point;

import de.hshannover.inform.dunkleit.gruppe12.snake.engine.GameObject;

/**
 * <h1>Virus Model</h1> Food for snake
 *
 * @author Mindaugas Milius
 * @version 1.0
 */

public class Virus extends GameObject{
	private Point position;
	private static String currentImg;
	
	/**
	 * Initialize new virus with boolean isAlive {@code FALSE} 
	 * and  no position Point due to NullPointerException
	 * position will be generated in Map class by searching free random Point 
	 */
	public Virus() {
		super();
		setRandomImg();
		setAlive(false);
	}

	//************************************************************************
	// GETTERS
	//***********************************************************************
	
	/**
	 * Returns image path
	 * @return String
	 */
	public String getCurrentImg() {
		return currentImg;
	}
	
	/**
	 * Returns virus position
	 * @return Point
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Returns virus x coord
	 * @return int
	 */
	public int getX(){
		return (int) position.getX();
	}
	
	/**
	 * Returns virus y coord
	 * @return int
	 */
	public int getY(){
		return (int) position.getY();
	}	
	
	//************************************************************************
	// SETTERS
	//************************************************************************

	
	
	/**
	 * Set Virus location and
	 * Check if position  {@code != null} to avoid 
	 * NullPointerException
	 * @param x x Coordinate
	 * @param y y Coordinate
	 */
	public void setLoc(int x, int y){
		if(position != null){
			position.setLocation(x, y);
		}else{
			position = new Point(x,y);
		}
	}
	
	/**
	 * Set Virus location  and
	 * Check if position  {@code != null} to avoid 
	 * NullPointerException
	 * @param p Point
	 */
	public void setLoc(Point p){

		if(position != null){
			position.setLocation(p);
		}else{
			position = new Point(p);
		}
	}
	
	/**
	 * Randomly set currentImg parameter with one of 
	 * 20 virus images path using random function. 
	 */	
	public void setRandomImg(){
		int randomVirusNumber = (int) (Math.random() * 20) ;
	 currentImg = "snake/virus/virus_" + randomVirusNumber + ".jpg"; //   0/1/2...19
	}
	
	/**
	 * Randomly set #currentImg parameter with one of 
	 * 20 virus images path using random function. 
	 */	
	public void clear(){
		setRandomImg();
		setAlive(false);
	}
}
