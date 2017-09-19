package de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.hshannover.inform.dunkleit.gruppe12.mainmenu.MainMenuConsts;

/**
 * Das Panel welches ein Spiel im Hauptmenü darstellt
 * 
 * @author dierschke
 */
public class MainMenuGameSelectionPanel extends JPanel {

	private boolean selected;
	
	private String identifier;
	private ImageIcon gameImage;
	private String gameTitle;
	
	/**
	 * Erstellt ein Spiel Panel mit zugehörigem Icon und Titel
	 * 
	 * @param identifier Identifier des Spiels
	 * @param gameImage 56x56 PNG-Icon des Spiels
	 * @param gameTitle Titel des Spiels
	 */
	public MainMenuGameSelectionPanel(String identifier, ImageIcon gameImage, String gameTitle) {
		super();
		this.identifier = identifier;
		this.gameImage = gameImage;
		this.gameTitle = gameTitle;
		this.selected = false;
		initGUI();
	}

	/**
	 * Initialisiert die GUI Komponenten
	 */
	private void initGUI() {
		// Layout
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(2, 2, 2, 1);
		gc.gridx = 0;
		gc.gridy = 0;
		
		this.setLayout(gbl);
		
		// Border
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		// Image
		JLabel imageLabel = new JLabel(this.gameImage);
		this.add(imageLabel, gc);
		
		// Text
		JLabel textLabel = new JLabel(gameTitle);
		Font textFont = textLabel.getFont();
		
		if(gameTitle.length() < 12) {
			textFont = textFont.deriveFont(MainMenuConsts.GAME_SELECTION_FONT_SIZE);
		} else {
			textFont = textFont.deriveFont(MainMenuConsts.GAME_SELECTION_FONT_SIZE - (gameTitle.length() * MainMenuConsts.GAME_SELECTION_FONT_AUTO_RESIZE));
		}
		textLabel.setFont(textFont);
		
		gc.insets = new Insets(2, 1, 2, 2);
		gc.gridx = 1;
		gc.weightx = 1.0;
		this.add(textLabel, gc);
	}
	
	/**
	 * Prüft ob das Panel derzeit ausgewählt ist
	 * 
	 * @return true - Panel ausgewählt, false - Panel nicht ausgewählt
	 */
	protected boolean isSelected() {
		return selected;
	}
	
	/**
	 * Gibt den Identifier des Spiels zurück, zu welchem dieses Panel gehört
	 * 
	 * @return Identifier des Spiels
	 */
	protected String getIdentifier() {
		return identifier;
	}
	
	/**
	 * Legt fest ob das Panel derzeit ausgewählt ist und markiert dieses entsprechend
	 * 
	 * @param selected true - ausgewählen, false - nicht ausgewählen
	 */
	protected void setSelected(boolean selected) {
		if(selected) {
			this.setBackground(Color.GRAY);
		} else {
			this.setBackground(UIManager.getColor("Panel.background"));
		}
		this.selected = selected;
	}

}
