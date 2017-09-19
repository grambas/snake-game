package de.hshannover.inform.dunkleit.gruppe12.mainmenu;


import javax.swing.SwingUtilities;

import de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui.MainMenuFrame;
import de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui.MainMenuGUIController;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

/**
 * Enthält Einstiegspunkt des Programms
 * 
 * @author dierschke
 */
public class MainMenuStarter {

	/**
	 * Einstiegsfunktion erstellt das Hauptfenster des HauptmenÃ¼s
	 * 
	 * @param args Ãœbergabeparameter (nicht genutzt)
	 */
	public static void main(String[] args) {
		
		// Hauptmenu anzeigen
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainMenuGUIController controller = new MainMenuGUIController();
				MainMenuFrame view = new MainMenuFrame(MainMenuConsts.MAIN_TITLE);
				
				// Spiele hinzufuegen
				controller.addGame(SnakeConsts.GAME_IDENTIFIER, SnakeConsts.GAME_TITLE);
				
				controller.setView(view);
				view.setController(controller);
				
				controller.showMainMenu();
			}
		});
	}
}
