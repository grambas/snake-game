package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

import java.awt.Point;

import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Snake;
import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Virus;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

/**
 * <h1>Map Model</h1> Holds gameboard 2D int array
 *
 * @author Mindaugas Milius
 * @version 1.0
 */

public class Map {
	private int map[][];

	/**
	 * Contructs gameboard with size
	 * 
	 * @param x	int first dimension array size
	 * @param y	int second dimension array size
	 *
	 */

	public Map(int x, int y) {
		map = new int[x][y];
	}

	/**
	 * Returns gameboard coordinate value Value reflects image id
	 * 
	 * @see SnakeConsts
	 * @param x	 int first dimension array value
	 * @param y	 int second dimension array value
	 * @return int[][] map
	 */
	public int getMapValue(int x, int y) {
		return map[x][y];
	}

	/*
	 * public int getMidWidth() { return map[0].length / 2; }
	 */
	/**
	 * @return int map length center
	 */
	public int getMidHeight() {
		return map.length / 2;
	}

	/**
	 * @return int map width
	 */
	public int getWidth() {
		return map[0].length;
	}

	/**
	 * @return int map length
	 */
	public int getHeight() {
		return map.length;
	}

	/**
	 * Cleans map. Leaves empty board
	 */
	public void clear() {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				map[y][x] = SnakeConsts.MAP_EMPTY;
			}
		}
	}

	/**
	 * Fills map with proper values. These values will be important at painting
	 * function
	 * 
	 * @param virus	 Virus object
	 * @param snake	 Snake object
	 */
	public void update(Virus virus, Snake snake) {
		int y, x;

		// filled with empty first
		clear();

		// filled with Virus
		if (virus != null) {
			map[virus.getY()][virus.getX()] = SnakeConsts.MAP_VIRUS;
		}

		// and then filled with snake's body
		for (SnakeBody sb : snake.getBody()) {
			x = (int) sb.getPoint().getX();
			y = (int) sb.getPoint().getY();
			map[y][x] = sb.getImgId();
		}

		// snake's head
		SnakeBody head = snake.getHead();
		x = (int) head.getPoint().getX();
		y = (int) head.getPoint().getY();
		map[y][x] = head.getImgId();
	}

	/**
	 * Generates random Point in map
	 * 
	 * @return Point random cordinate in gameboard
	 */
	public Point randomPoint() {
		int x = (int) (Math.random() * map[0].length);
		int y = (int) (Math.random() * map.length);
		return new Point(x, y);
	}

	/**
	 * Looks and sets Virus position on map while random generated position not
	 * collides with snake. After found free spot on map, virus state will be
	 * changed to true;
	 * 
	 * @param virus Virus object
	 * @param snake	Snake object
	 */
	public void positionVirus(Virus virus, Snake snake) {

		while (!virus.isAlive()) {

			Point pCandidate = randomPoint();

			if (snake.checkCollision(new SnakeBody(pCandidate, -1)) == false) {
				virus.setLoc(pCandidate);
				virus.setAlive(true);
			}
		}
	}

}
