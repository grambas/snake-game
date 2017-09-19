package de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.hshannover.inform.dunkleit.gruppe12.mainmenu.MainMenuConsts;
import de.hshannover.inform.dunkleit.gruppe12.util.HtmlLoader;
import de.hshannover.inform.dunkleit.gruppe12.util.ImageLoader;

/**
 * Das Panel zur Darstellung der Anleitung
 * 
 * @author dierschke
 */
public class MainMenuInstructionsPanel extends JPanel {

	private JScrollPane scrollPane;
	private JEditorPane editorPane;
	
	/**
	 * Erstellt das Anleitungs Panel
	 */
	public MainMenuInstructionsPanel() {
		super();
		initGUI();
	}

	/**
	 * Initialisiert die GUI Komponenten
	 */
	private void initGUI() {
		// Layout
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		// Border
		this.setBorder(BorderFactory.createTitledBorder(MainMenuConsts.INSTRUCTIONS_BORDER_TITLE));
		
		// Editor Pane
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		
		// Scroll Pane
		scrollPane = new JScrollPane(editorPane);
		this.add(this.scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Setzt den Inhaltstext (HTML-Code) des Anleitungs Panel
	 * 
	 * @param text String mit HTML-Code
	 * @param identifier String mit dem Spiel-Identifier
	 */
	protected void setDisplayText(String text, String identifier) {
		editorPane.setText(text);
		loadImages(identifier);
		editorPane.setCaretPosition(0); // zurück zum Anfang scrollen
	}
	
	/**
	 * Laedt die in instruction-res.txt angebenen Bilder nach
	 * 
	 * @param identifier
	 */
	protected void loadImages(String identifier) {
		try {
			@SuppressWarnings("unchecked")
			Dictionary<URL, Image> cache = (Dictionary<URL, Image>) editorPane.getDocument().getProperty("imageCache");
			if (cache == null) {
				cache = new Hashtable<URL, Image>();
				editorPane.getDocument().putProperty("imageCache", cache);
			}
			
			List<String> resList = HtmlLoader.getInstance().getHtmlResources(identifier + "/instruction-res.txt");
			
			for(String s : resList) {
				URL u = new URL("http:\\\\" + s);
				cache.put(u, ImageLoader.getInstance().loadImageIcon(identifier + "/" + s).getImage());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
