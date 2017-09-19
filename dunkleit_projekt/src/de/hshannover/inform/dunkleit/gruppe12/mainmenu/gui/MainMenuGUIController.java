package de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import de.hshannover.inform.dunkleit.gruppe12.common.gui.AbstractGameFrame;
import de.hshannover.inform.dunkleit.gruppe12.mainmenu.MainMenuConsts;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.snake.gui.SnakeGameFrame;
import de.hshannover.inform.dunkleit.gruppe12.util.HtmlLoader;


/**
 * Der GUI Controller des Hauptmenüs
 * 
 * @author dierschke
 */
public class MainMenuGUIController {

	private MainMenuFrame view;
	private HashMap<String, String> gameMap; // das Model
	private ActionListener startListener;
	
	/**
	 * Erzeugt einen neuen Controller
	 */
	public MainMenuGUIController() {
		this.gameMap = new HashMap<>();
		startListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				handleStartEvent();
			}
		};
	}
	
	/**
	 * Macht den Controller mit einem zugehörigen View bekannt
	 * @param view das View
	 */
	public void setView(MainMenuFrame view) {
		this.view = view;
	}
	
	/**
	 * Zeigt das Hauptmenü
	 */
	public void showMainMenu() {
		view.setVisible(true);
	}
	
	/**
	 * Versteckt das Hauptmenü
	 */
	public void hideMainMenu() {
		view.setVisible(false);
	}
	
	/**
	 * Fügt ein neues Spiel zum Model hinzu. Dabei muss es ein Resourcen-Package mit dem
	 * entsprechenden Identifier des Spiels geben, welches Icon (Größe:56x56 Name: icon.png) 
	 * und Anleitung (Name: instructions.html) des Spiels enthält.
	 * 
	 * @param identifier Der Identifier des Spiels
	 * @param title Der Titel des Spiels
	 */
	public void addGame(String identifier, String title) {
		gameMap.put(identifier, title);
	}
	
	/**
	 * Gibt das Model welches Identifier und Titel der Spiele enthält in Form einer
	 * <code>HashMap</code> zurück.
	 * 
	 * @return  Die <code>HashMap</code>
	 */
	public HashMap<String, String> getGames() {
		return gameMap;
	}
	
	/**
	 * View kann den Controller über diese Methode benachrichtigen, wenn ein Spiel ausgewählt wird.
	 * Der Controller lädt daraufhin die Anleitung des Spiels und delegiert sie zurück an die View.
	 * 
	 * @param identifier
	 */
	protected void notifySelectionChanged(String identifier) {
		String html = HtmlLoader.getInstance().loadHtmlCode(identifier + "/" + MainMenuConsts.RESOURCE_INSTRUCTIONS_HTML_NAME);
		view.setInstructionText(html);
	}
	
	protected ActionListener getStartActionListener() {
		return startListener;
	}
		
	/**
	 * Regelt welches Spiel gestartet werden soll
	 */
	protected void handleStartEvent() {
		// TODO Hier initialisiert später jeder sein Spielfenster/Controller selbst

		String selected = view.getSelectedGameIdentifier();
  
		if (selected != null) {
			AbstractGameFrame newGameFrame = null;

			switch (selected) {
			case SnakeConsts.GAME_IDENTIFIER:
				newGameFrame = new SnakeGameFrame(this);
				break;				
			default:
			}

			if (newGameFrame != null)
				newGameFrame.setVisible(true);
		}
	}

}
