package de.hshannover.inform.dunkleit.gruppe12.common.gui;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

/**
 * Label zum Anzeigen eines Timers
 * 
 * @author dierschke
 */
public class GameTimerLabel extends JLabel {

	private long elapsedMillis;
	private SimpleDateFormat df;
	
	/**
	 * Ereugt ein neue TimerLabel
	 * 
	 * @param showMinutes falls Minuten angezeigt werden sollen true - sonst false
	 * @param showMillis falls Millisekunden angezeigt werden sollen true - sonst false
	 */
	public GameTimerLabel(boolean showMinutes, boolean showMillis) {
		super();
		String formatterString = "ss";
		this.elapsedMillis = 0;
		
		if(showMinutes)
			formatterString = "mm:" + formatterString;
		
		if(showMillis)
			formatterString = formatterString + ":SSS";
		
		df = new SimpleDateFormat(formatterString);
		
		Font font = getFont();
		font = font.deriveFont(GUIConsts.HUD_TEXT_SIZE);
		font = font.deriveFont(Font.BOLD);
		setFont(font);
		
		setOpaque(true);
		setForeground(GUIConsts.COLOR_MATRIX_GREEN);
		setBackground(Color.BLACK);
		
		setVerticalAlignment(TOP);
		
		setText(makeTimerString());
	}
	
	/**
	 * Akualisiert das Label auf die übergebene Zeit
	 * 
	 * @param millis Zeit in Millisekunden
	 */
	public void updateElapsedMillis(long millis) {
		this.elapsedMillis = millis;
		setText(makeTimerString());
	}
	
	/**
	 * Formatiert den Timer-Text 
	 * 
	 * @return Formatierter Timer-Text
	 */
	private String makeTimerString() {
		return this.df.format(new Date(this.elapsedMillis));
	}
}
