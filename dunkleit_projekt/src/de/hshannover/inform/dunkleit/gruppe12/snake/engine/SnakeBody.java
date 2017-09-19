package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

import java.awt.Point;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

/**
 * <h1>Snake body part Class</h1> Holds data structure for snake body part
 *
 * @author Mindaugas Milius
 * @version 1.0
 * 
 */
public class SnakeBody {
	private Point p;
	private int imgId;

	/**
	 * Constructs class with Parameters
	 *
	 * @param p	  Point snake position in map
	 * @param imgId int image id
	 * 
	 */
	public SnakeBody(Point p, int imgId) {
		this.p = p;
		this.imgId = imgId;
	}

	/**
	 * returns snake's body part position in
	 * 
	 * @see Map
	 * @return Point
	 * 
	 */
	public Point getPoint() {
		return p;
	}

	/**
	 * set Point value which will be represent in Map class
	 * 
	 * @see Map
	 * @param p	Point
	 * 
	 */
	public void setPoint(Point p) {
		this.p = p;
	}

	/**
	 * Returns current body part image id
	 * 
	 * @return int
	 * @see SnakeConsts
	 */
	public int getImgId() {
		return imgId;
	}

	/**
	 * Set current body part image id
	 * 
	 * @param imgId int image id
	 * @see SnakeConsts
	 */
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	/**
	 * Custom imlempented hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + imgId;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}

	/**
	 * Custom imlempented equals method. Checks if this Point == other
	 * Snakebody's Point
	 * 
	 * @param obj SnakeBody object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnakeBody other = (SnakeBody) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

}
