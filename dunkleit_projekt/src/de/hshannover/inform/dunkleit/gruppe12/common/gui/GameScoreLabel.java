package de.hshannover.inform.dunkleit.gruppe12.common.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Label zum Anzeigen eines Scores
 * 
 * @author dierschke
 */
public class GameScoreLabel extends JLabel {

	private int displayedScore;
	
	/**
	 * Erzeugt ein neues ScoreLabel
	 */
	public GameScoreLabel() {
		super();
		this.displayedScore = 0;
		setText(String.format("%05d", this.displayedScore));
		
		 Font font = getFont();
		 font = font.deriveFont(GUIConsts.HUD_TEXT_SIZE);
		 font = font.deriveFont(Font.BOLD);
		 setFont(font);
		 
		 setOpaque(true);
		 setForeground(GUIConsts.COLOR_MATRIX_GREEN);
		 setBackground(Color.BLACK);
		 
		 setVerticalAlignment(TOP);
	}
	
	/**
	 * Aktualisiert das Label, sodass der übergebene Score angezeigt wird
	 * @param score Der Score
	 */
	public void updateDisplayedScore(int score) {
		this.displayedScore = score;
		setText(String.format("%05d", score));
	}
}
