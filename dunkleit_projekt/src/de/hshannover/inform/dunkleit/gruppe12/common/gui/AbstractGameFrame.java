package de.hshannover.inform.dunkleit.gruppe12.common.gui;

import javax.swing.JFrame;

import de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui.MainMenuGUIController;

/**
 * Abstraktes Frame für die einzelnen konkreten Spielfenster
 * 
 * @author dierschke
 */
public abstract class AbstractGameFrame extends JFrame {
	
	private MainMenuGUIController controller;
	
	/**
	 * Erstellt das Frame mit dem übergebenen Titel
	 * 
	 * @param title
	 */
	public AbstractGameFrame(String title, MainMenuGUIController controller) {
		super(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setPreferredSize(GUIConsts.CONTENT_SIZE);
		
		this.controller = controller;
		
		this.pack();
		setLocationRelativeTo(null);
	}
	
	
	/**
	 * Das Hauptmenü soll versteckt werden, wenn ein GameFrame sichtbar wird.
	 * Wenn ein GameFrame unsichtbar wird, soll das Hauptmenü wieder angezeigt werden.
	 */
	@Override
	public void setVisible(boolean b) {
		if(b) {
			controller.hideMainMenu();
		}
		else {
			controller.showMainMenu();
		}
		
		super.setVisible(b);
	}
	
}
