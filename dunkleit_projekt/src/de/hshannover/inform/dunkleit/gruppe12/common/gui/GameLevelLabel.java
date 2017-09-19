package de.hshannover.inform.dunkleit.gruppe12.common.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Label zum Anzeigen eines Levels
 * 
 * @author dierschke
 */
public class GameLevelLabel extends JLabel {

	private int displayedLevel;
	
	/**
	 * Erzeugt ein neues LevelLabel
	 */
	public GameLevelLabel() {
		super();
		this.displayedLevel = 0;
		setText(String.format("Level %d", this.displayedLevel));
		
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
	 * Aktualisiert das Label, sodass das übergebene Level angezeigt wird
	 * @param level Das Level
	 */
	public void updateDisplayedLevel(int level) {
		this.displayedLevel = level;
		setText(String.format("Level %d", level));
	}
}
