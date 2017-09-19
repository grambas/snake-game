package de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.hshannover.inform.dunkleit.gruppe12.common.gui.GUIConsts;
import de.hshannover.inform.dunkleit.gruppe12.mainmenu.MainMenuConsts;
import de.hshannover.inform.dunkleit.gruppe12.mainmenu.Preloader;
import de.hshannover.inform.dunkleit.gruppe12.util.ImageLoader;

/**
 * Das Frame des Hauptmenüs
 * 
 * @author dierschke
 */
public class MainMenuFrame extends JFrame {

	private MainMenuGUIController controller;
	
	private HashMap<String, String> games;
	
	private JLabel titleLabel;
	
	private JButton startButton;
	
	private MainMenuGamesPanel gamesPanel;
	
	private MainMenuInstructionsPanel instructionPanel;
	
	/**
	 * Erstellt ein neues Hauptmenü Frame mit dem spezifizierten Titel
	 * 
	 * @param windowTitle Der Titel des Frames
	 */
	public MainMenuFrame(String windowTitle) {
		super(windowTitle);
		Preloader.preload();
	}
	
	/**
	 * Macht die View mit einem zugehörigen Controller bekannt
	 * 
	 * @param controller Der Controller
	 */
	public void setController(MainMenuGUIController controller) {
		this.controller = controller;
		this.games = controller.getGames();
		initGUI();
		startButton.addActionListener(controller.getStartActionListener());
	}
		
	/**
	 * Initialisiert die GUI Komponenten
	 */
	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setPreferredSize(GUIConsts.CONTENT_SIZE);
		
		// Layout
		GridBagLayout gbl = new GridBagLayout();
		this.getContentPane().setLayout(gbl);
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(4, 4, 4, 4);
		gc.ipadx = 0;
		gc.ipady = 0;
		
		// Title
		titleLabel = new JLabel(this.getTitle(), SwingConstants.CENTER);
		Font titleFont = titleLabel.getFont();
		titleFont = titleFont.deriveFont(MainMenuConsts.MAIN_TITLE_FONT_SIZE);
		titleFont = titleFont.deriveFont(Font.BOLD);
		titleLabel.setFont(titleFont);
		
		gc.fill = GridBagConstraints.BOTH;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.gridheight = 1;
		this.getContentPane().add(titleLabel, gc);
		
		// Game selection
		MainMenuGameSelectionPanel[] gamePanels = new MainMenuGameSelectionPanel[games.size()];
		for(int i=0; i < games.size(); i++) {
			String identifier = games.keySet().toArray(new String[games.size()])[i];
			ImageIcon icon = ImageLoader.getInstance().loadImageIcon(identifier + "/" + MainMenuConsts.RESOURCE_ICON_NAME);
			gamePanels[i] = new MainMenuGameSelectionPanel(identifier, icon, games.get(identifier));
		}
		gamesPanel = new MainMenuGamesPanel(gamePanels, controller);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		this.getContentPane().add(gamesPanel, gc);
		
		// Instructions
		instructionPanel = new MainMenuInstructionsPanel();
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		gc.gridheight = 2;
		gc.ipadx = (int)(GUIConsts.RESIZE_FACTOR * 550);
		gc.ipady = (int)(GUIConsts.RESIZE_FACTOR * 430);
		this.getContentPane().add(instructionPanel, gc);
		
		// Start Button
		startButton = new JButton(MainMenuConsts.START_BUTTON_TEXT);
		startButton.setFont(startButton.getFont().deriveFont(MainMenuConsts.START_BUTTON_FONT_SIZE));
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.ipadx = 0;
		gc.ipady = 0;
		this.getContentPane().add(startButton, gc);
		
		this.pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Setzt den html-Code zur Anzeige der Anleitung
	 * 
	 * @param htmlCode Der HTML-Code
	 */
	protected void setInstructionText(String htmlCode) {
		instructionPanel.setDisplayText(htmlCode, getSelectedGameIdentifier());
	}
	
	/**
	 * Gibt das derzeit ausgewählte Spiel zurück
	 * 
	 * @return Spiel-Identifier oder null wenn kein Spiel selektiert ist
	 */
	protected String getSelectedGameIdentifier() {
		if(gamesPanel.getSelectedPanel() != null)
			return gamesPanel.getSelectedPanel().getIdentifier();
		
		return null;
	}
}
