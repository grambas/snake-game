package de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.hshannover.inform.dunkleit.gruppe12.mainmenu.MainMenuConsts;

/**
 * Das Panel zur Auswahl aller Spiele im Hauptmenü
 * 
 * @author dierschke
 */
public class MainMenuGamesPanel extends JPanel {
	
	private MainMenuGUIController controller;
	
	private MainMenuGameSelectionPanel[] selectionPanels;
	
	private MainMenuGameSelectionPanel selectedPanel;
	
	private final MouseAdapter clickAdapter;

	/**
	 * Erstellt das Spieleauswahl Panel
	 */
	public MainMenuGamesPanel(MainMenuGameSelectionPanel[] selectionPanels, MainMenuGUIController controller) {
		super();
		this.selectionPanels = selectionPanels;
		this.controller = controller;
		selectedPanel = null;
		this.clickAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				handleSelectionChange((MainMenuGameSelectionPanel)e.getSource());
			}
		};
		initGUI();
	}

	/**
	 * Initialisiert die GUI Komponenten
	 */
	private void initGUI() {
		// Layout
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.ipadx = 0;
		gc.ipady = 0;
		gc.insets = new Insets(1, 2, 1, 2);
		gc.gridx = 0;
		gc.gridy = 0;
		
		// Border
		this.setBorder(BorderFactory.createTitledBorder(MainMenuConsts.GAME_SELECTION_BORDER_TITLE));
		
		// Mouse Click
		for(MainMenuGameSelectionPanel p : selectionPanels) {
			p.addMouseListener(this.clickAdapter);
			this.add(p, gc);
			gc.gridy++;
		}
	}

	/**
	 * Verwaltet eine Neuauswahl eines Spiel Panels und benachricht den
	 * Controller. Das zuvor ausgewählte Panel wird ggf. deselektiert.
	 * 
	 * @param newSelection
	 *            Panel welches neu ausgewählt werden soll
	 */
	private void handleSelectionChange(MainMenuGameSelectionPanel newSelection) {
		if(selectedPanel != null) {
			selectedPanel.setSelected(false);
		}
		newSelection.setSelected(true);
		selectedPanel = newSelection;
		
		controller.notifySelectionChanged(newSelection.getIdentifier());
	}
	
	/**
	 * Gibt das zur Zeit ausgewählte Spiele Panel zurück
	 * 
	 * @return Das ausgewählte Panel
	 */
	protected MainMenuGameSelectionPanel getSelectedPanel() {
		return selectedPanel;
	}
}
