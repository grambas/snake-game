package de.hshannover.inform.dunkleit.gruppe12.mainmenu;

import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.util.MusicLoader;

/**
 * Klasse um kritische Ressourcen nicht erst zur Laufzeit eines Spiels laden zu
 * muessen
 * 
 * @author dierschke
 */
public class Preloader {

	/**
	 * Preloaded kritische Resourcen
	 */
	public static void preload() {
		System.out.println("Loading...");
		preloadMusic();
		preloadImages();
		preloadHTML();
		System.out.println("...finished");
	}

	/**
	 * Musik preloaden
	 */
	private static void preloadMusic() {


		// Snake
		MusicLoader.getInstance().loadMP3Clip(SnakeConsts.BG_MUSIC);
		MusicLoader.getInstance().loadMP3Clip(SnakeConsts.GAMEOVER_SOUND);
		MusicLoader.getInstance().loadMP3Clip(SnakeConsts.EAT_SOUND);

	}

	/**
	 * Bilder preloaden
	 */
	private static void preloadImages() {
		//
	}

	/**
	 * HTML preloaden
	 */
	private static void preloadHTML() {
		//
	}
}
