package de.hshannover.inform.dunkleit.gruppe12.snake.controller;

import java.awt.Point;
import java.util.LinkedList;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.GameObject;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.SnakeBody;

/**
 * <h1>Snake Model</h1> Implemented with LinkedList data structure
 *
 * @author Mindaugas Milius
 * @see SnakeBody
 * @version 1.0
 */

public class Snake extends GameObject {

	private LinkedList<SnakeBody> body = new LinkedList<SnakeBody>();
	private int current_direction;
	private boolean dirChanged;

	/**
	 * Snake object constructor
	 * 
	 * @param x Coordinate for x
	 * @param y Coordinate for y
	 * @param direction current moving direction
	 * 
	 **/
	public Snake(int x, int y, int direction) {
		addBoddyPart(x, y);// add Head at mid
		this.current_direction = direction;
	}

	/**
	 * Snake object constructor
	 * 
	 */
	public Snake() {
		super();
		dirChanged = false;
		clear();
	}

	// ************************************************************************
	// OTHER FUNCTIONS
	// ************************************************************************

	/**
	 * Delete Tail. Removes last snake's body part
	 * 
	 */
	public void deleteTail() {
		body.removeLast();
	}

	/**
	 * <ul>
	 * <li>Removes all elements from snake's body</li>
	 * <li>Initiate snake to state alive</li>
	 * <li>Inserts one body part for snake's head</li>
	 * <li>Set head direction to default direction</li>
	 * </ul>
	 */
	public void clear() {
		body.clear();
		setAlive(true);
		body.addFirst(new SnakeBody(new Point(SnakeConsts.MAP_WIDTH, SnakeConsts.MAP_HEIGHT/2), SnakeConsts.MAP_HEAD_RIGHT));
		current_direction = SnakeConsts.DEFAULT_DIRECTION;
	}

	/**
	 * Checks if snake contains SnakeBody part in LinkedList
	 * 
	 * @param sb SnakeBody element
	 * @return boolean true if collides
	 */
	public boolean checkCollision(SnakeBody sb) {
		return body.contains(sb);
	}

	/**
	 * Method to increase snake's size by 1. Dublicates last element and adds to end.
	 */
	public void grow() {
		body.addLast(body.getLast());
	}

	/**
	 * Method to add snake's body part at the begin Dublicates last element and	adds to end.
	 * @param x x coord
	 * @param y y coord
	 */
	public void addBoddyPart(int x, int y) {
		body.addFirst(new SnakeBody(new Point(x, y), SnakeConsts.DEFAULT_DIRECTION));
	}

	/**
	 * Moves snakes forward to map in +1 considering snake direction and
	 * assigns correct image id for painting map. if collision TRUE, state = {@code DEAD} else current state.
	 * if collision TRUE, state set to DEAD else leave current state
	 * <pre>
	 *    Creates new Point considering current Direction and
	 *    removes 1 Point from the end (tail) and
	 *    Checks collision
	 * </pre>
	 * 
	 */
	public void move() {

		SnakeBody oldHead = getHead();

		int bodySize = body.size();
		int headX = (int) oldHead.getPoint().getX();
		int headY = (int) oldHead.getPoint().getY();

		SnakeBody newHead = new SnakeBody(new Point(), -1);

		// check if body contains not only head
		if (bodySize > 1) {
			oldHead.setImgId(oldHead.getImgId() + 10);// turn head to body body
														// part
		}
		// add 1 body part to moving direction and assign proper image id for
		// snake head
		switch (current_direction) {
		case SnakeConsts.UP:
			newHead.getPoint().setLocation(headX, --headY);
			newHead.setImgId(SnakeConsts.MAP_HEAD_UP);
			break;
		case SnakeConsts.DOWN:
			newHead.getPoint().setLocation(headX, ++headY);
			newHead.setImgId(SnakeConsts.MAP_HEAD_DOWN);
			break;
		case SnakeConsts.LEFT:
			newHead.getPoint().setLocation(--headX, headY);
			newHead.setImgId(SnakeConsts.MAP_HEAD_LEFT);
			break;
		case SnakeConsts.RIGHT:
			newHead.getPoint().setLocation(++headX, headY);
			newHead.setImgId(SnakeConsts.MAP_HEAD_RIGHT);
			break;
		default:
			break;
		}

		// Grow snake if new head point not colliding with rest body
		if (checkCollision(newHead) == false) {
			body.addFirst(newHead);
		} else {
			setAlive(false);// set snake status to dead
		}

		// if snake not dead remove one snake body from end to
		deleteTail();
	}

	/**
	 * Rotates snake to given direction
	 * 
	 * <pre>
	 *  Snake can be rotated only 90 degree
	 * </pre>
	 *
	 * @param direction {@code int} Direction to rotate snake
	 */
	public void changeDirection(int direction) {

		// checks if new direction is not same and
		// also checks if snake direction are not changed 2 times in one update
		// statment
		if (direction != this.current_direction && dirChanged == false) {

			SnakeBody head = getHead();

			paintCorners(head, this.current_direction, direction);

			// set current directio if its 90 degree and set dirChanged to true
			// to secure multiple
			// direction changes in one update statment
			switch (direction) {
			case SnakeConsts.UP:
				if (this.current_direction != SnakeConsts.DOWN) {
					this.current_direction = direction;
					this.dirChanged = true;
				}
				break;
			case SnakeConsts.DOWN:
				if (this.current_direction != SnakeConsts.UP) {
					this.current_direction = direction;
					this.dirChanged = true;
				}
				break;
			case SnakeConsts.LEFT:
				if (this.current_direction != SnakeConsts.RIGHT) {
					this.current_direction = direction;
					this.dirChanged = true;
				}
				break;
			case SnakeConsts.RIGHT:
				if (this.current_direction != SnakeConsts.LEFT) {
					this.current_direction = direction;
					this.dirChanged = true;
				}
				break;
			default:
				break;
			}
		}

	}

	/**
	 *Set BodySnake img id for snake corner image
	 *
	 * @param head
	 *            SnakeBody object to be analysed
	 * @param current_direction
	 *            old snake direction
	 * @param direction
	 *            new snake direction
	 * 
	 */
	private void paintCorners(SnakeBody head, int current_direction, int direction) {
		// IMAGE FOR CORNERS
		if (current_direction == SnakeConsts.LEFT) {
			if (direction == SnakeConsts.UP) {
				// from RIGHT to UP
				head.setImgId(SnakeConsts.MAP_FROM_R_TO_U);
			} else if (direction == SnakeConsts.DOWN) {
				// from RIGHT to DOWN
				head.setImgId(SnakeConsts.MAP_FROM_R_TO_D);
			}
		} else if (this.current_direction == SnakeConsts.RIGHT) {
			if (direction == SnakeConsts.UP) {
				// from LEFT to UP
				head.setImgId(SnakeConsts.MAP_FROM_L_TO_U);
			} else if (direction == SnakeConsts.DOWN) {
				// from RIGHT to down
				head.setImgId(SnakeConsts.MAP_FROM_L_TO_D);
			}
		} else if (this.current_direction == SnakeConsts.DOWN) {
			if (direction == SnakeConsts.LEFT) {
				// from UP TO LEFT
				head.setImgId(SnakeConsts.MAP_FROM_U_TO_L);
			} else if (direction == SnakeConsts.RIGHT) {
				// img from UP TO RIGHT
				head.setImgId(SnakeConsts.MAP_FROM_U_TO_R);
			}
		} else if (this.current_direction == SnakeConsts.UP) {
			if (direction == SnakeConsts.LEFT) {
				// from DOWN TO LEFT
				head.setImgId(SnakeConsts.MAP_FROM_D_TO_L);
			} else if (direction == SnakeConsts.RIGHT) {
				// img from DOWN TO RIGHT
				head.setImgId(SnakeConsts.MAP_FROM_D_TO_R);
			}
		}
	}

	// ************************************************************************
	// GETTERS
	// ************************************************************************

	/**
	 * Returns current snake object first element in Lineked List (Head)
	 * @return SnakeBody snake's head as SnakeBody object
	 * @see SnakeBody
	 */
	public SnakeBody getHead() {
		return body.getFirst();
	}

	/**
	 * Returns snake's body as LinekedList 
	 * @return snake's body as LinkedList object
	 * @see LinkedList
	 */
	public LinkedList<SnakeBody> getBody() {
		return body;
	}

	/**
	 * Returns  snake's object last element in Lineked List (tail)
	 * @return snake's tail as SnakeBody object
	 * @see SnakeBody
	 */
	public SnakeBody getTail() {
		return body.getLast();
	}

	/**
	 * Returns snake's body size in int
	 * @return int snake body element size
	 * @see SnakeBody
	 */
	public int getSnakeSize() {
		return body.size();
	}

	/**
	 * Returns current snake's direction
	 * @return int current snake's direction
	 * @see SnakeConsts
	 */
	public int getCurrentDirection() {
		return current_direction;
	}

	// ************************************************************************
	// SETTERS
	// ************************************************************************

	/**
	 * Set new direction using constants from {@link SnakeConsts}
	 * 
	 * @param direction new snake's direction
	 */
	public void setCurrentDirection(int direction) {
		this.current_direction = direction;
	}

	/**
	 * Set snake direction changed variable. Use to prevent for multiple
	 * direction change in one move update statmen
	 * 
	 * @param val true or false boolean
	 */
	public void dirChanged(boolean val) {
		this.dirChanged = val;
	}

}
