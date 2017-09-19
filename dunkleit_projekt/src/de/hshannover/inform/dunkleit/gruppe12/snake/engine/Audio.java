package de.hshannover.inform.dunkleit.gruppe12.snake.engine;

import javax.sound.sampled.Clip;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.util.MusicLoader;

/**
 * <h1>Audio Loader</h1> Use public Audio loader with cache functionality to
 * load music
 *
 * @author Mindaugas Milius
 * @see MusicLoader
 * @version 1.0
 */

public class Audio {

	private static Clip music;
	private static Clip over;
	private static Clip eat;

	/**
	 * Loads music files / or gets them from cache
	 *
	 */

	public Audio() {
		music = MusicLoader.getInstance().loadMP3Clip(SnakeConsts.BG_MUSIC);
		eat = MusicLoader.getInstance().loadMP3Clip(SnakeConsts.EAT_SOUND);
		over = MusicLoader.getInstance().loadMP3Clip(SnakeConsts.GAMEOVER_SOUND);

	}

	/**
	 * Plays Background music
	 *
	 */

	public void playBG() {
		
		music.stop();
		music.loop(Clip.LOOP_CONTINUOUSLY);
		music.start();
	}

	/**
	 * Stops music file
	 *
	 */
	public void stopBG() {
		music.stop();
	}

	/**
	 * Plays eat sound effect
	 *
	 */
	public void playEat() {
		eat.stop();// stop eat if any plays
		eat.setMicrosecondPosition(0);// set cursor to file effect sound start
		eat.start();
	}

	/**
	 * Plays gameover sound effect
	 *
	 */
	public void playGameOver() {
		//over.stop();
		over.setMicrosecondPosition(0);
		over.start();
	}

}