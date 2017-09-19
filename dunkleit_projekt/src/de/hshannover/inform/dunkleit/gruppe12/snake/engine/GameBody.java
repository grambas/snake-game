package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Level;
import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Score;
import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Snake;
import de.hshannover.inform.dunkleit.gruppe12.snake.controller.Virus;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.Audio;

/**
 * <h1>GameBody</h1> Communicates beetween Models and GUI
 * 
 * @author Mindaugas Milius
 * @version 1.0
 */

public class GameBody extends Observable implements ActionListener {

	private Virus virus;
	private Map map;
	private Snake snake;
	private DisplayListener displayListener;
	private Timer timer;// jawa.swing.timer
	private Level level;
	private Score score;
	private int state;
	private static Audio musicPlayer;

	/**
	 * Constructs GameBody object with listener and map
	 * <p>
	 * Note: other important objects and values are processed at
	 * {@link #initGame(Map)}
	 * </p>
	 * 
	 * @param listener DisplayListener object
	 * @param screen Map Object (gameboard)
	 * @see #initGame(Map)
	 */
	public GameBody(DisplayListener listener, Map screen) {
		this.displayListener = listener;
		this.level = new Level();
		this.map = screen;
		this.initGame(map);
	}

	/**
	 * Init all other important game objects and values
	 * <ul><li>Loads Music Player and starts</li>
	 * <li>init Score</li>
	 * <li>init Virus</li>
	 * <li>init Snake</li>
	 * <li>init Timer</li>
	 * <li>State = STATE_BEGIN</li>
	 * <li>position Virus</li>
	 * <li>clear map</li></ul>
	 * 
	 * @param map Map Object (gameboard)
	 */
	public void initGame(Map map) {
		musicPlayer = new Audio();
		musicPlayer.playBG();

		this.score = new Score();
		this.virus = new Virus();
		this.state = SnakeConsts.STATE_BEGIN;
		this.snake = new Snake();
		map.positionVirus(virus, snake);
		this.timer = new Timer(level.getLevelDelay(), this);
		map.clear();
	}

	/**
	 * Init new Game after GameOver Works similiar as {@link #initGame(Map)}
	 * 
	 * @param level	to be played
	 */
	public void startNew(int level) {

		this.level.setValue(level);
		timer.setDelay(this.level.getLevelDelay());
		timer.restart();
		snake.clear();
		score.clear();
		virus.clear();
		map.clear();
		map.positionVirus(virus, snake);
		this.state = SnakeConsts.STATE_RUNNING;
		musicPlayer.playBG();
		setChanged();
		notifyObservers();
	}

	/**
	 * Stops background music and timer. Notify Observers too.
	 */
	public void stop() {
		musicPlayer.stopBG();
		timer.stop();
		setChanged();
		notifyObservers();
	}

	/**
	 * Starts timer and change State to "running" also notify Observers.
	 */
	public void start() {
		timer.setDelay(this.level.getLevelDelay());
		timer.start();
		this.state = SnakeConsts.STATE_RUNNING;
		setChanged();
		notifyObservers();
	}

	// ************************************************************************
	// Snake & Virus related functions
	// ************************************************************************

	/**
	 * Firstly checks if virus coords == Snake head coords.
	 * <ul>
	 * <li>set virus alive = false</li>
	 * <li>Generate new random image for virus</li> 
	 * <li>Grow the snake and play eat sound effect</li>
	 * <li>Add score point</li>
	 * </ul>
	 */
	private void growSnakeIfVirusEated() {
		if (snake.getHead().getPoint().equals(virus.getPosition())) {
			virus.setAlive(false);
			virus.setRandomImg();
			snake.grow();
			musicPlayer.playEat();
			score.addPoint();
		}
	}

	/**
	 * Method to init gamover
	 * <p>
	 * plays gameover sound then change game state to gameover and stop the timer
	 * </p>
	 */
	public void GameOver() {
		musicPlayer.playGameOver();
		this.state = SnakeConsts.STATE_GAMEOVER;
		this.stop();

	}

	/**
	 * This function is called every swing Timer step.
	 * Responsible for calling positioning virus, snake movement and other functions every timer step
	 * ArrayIndexOutOfBoundsException if snake collides map boundaries and  GameOver method called
	 * @param e	 ActionEvent (must be implemented)
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			
			//System.out.println(snake.getHead().getPoint().toString());
			map.positionVirus(virus, snake);
			snake.move();
			growSnakeIfVirusEated();
			map.update(virus, snake);
			if (snake.isAlive() == false) {
				this.GameOver();
			}
		} catch (ArrayIndexOutOfBoundsException ee) {
			snake.setAlive(false);
			this.GameOver();
		} finally {
			this.displayListener.update(new GameEvent(this.map, this.state));
			snake.dirChanged(false);// realase lock to change direction
		}
	}

	// ************************************************************************
	// GETTERS
	// ************************************************************************

	/**
	 * Returns current game state
	 * 
	 * @return int state
	 * @see SnakeConsts
	 */
	public int getState() {
		return state;
	}

	/**
	 * Returns snake object
	 * 
	 * @return Snake
	 * @see Snake
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * Returns score object
	 * 
	 * @return Score
	 * @see Score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * Returns level object
	 * 
	 * @return Level
	 * @see Level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Interface for access to Virus image path
	 * 
	 * @return String current Virus image path
	 * @see Score
	 */
	public String getVirusImg() {
		return virus.getCurrentImg();
	}

	// ************************************************************************
	// SETTERS
	// ************************************************************************

	/**
	 * Change game state and notify Observers
	 * 
	 * @param state int
	 * @see SnakeConsts
	 */
	public void setState(int state) {
		this.state = state;
		setChanged();
		notifyObservers();
	}
}