package de.hshannover.inform.dunkleit.gruppe12.snake.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hshannover.inform.dunkleit.gruppe12.common.gui.AbstractGameFrame;
import de.hshannover.inform.dunkleit.gruppe12.common.gui.GUIConsts;
import de.hshannover.inform.dunkleit.gruppe12.common.gui.GameLevelLabel;
import de.hshannover.inform.dunkleit.gruppe12.common.gui.GameScoreLabel;
import de.hshannover.inform.dunkleit.gruppe12.mainmenu.gui.MainMenuGUIController;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.Map;

/**
 * <h1>SnakeGameFrame</h1> extends main game menu
 * 
 * @author Mindaugas Milius
 * @version 1.0
 */

public class SnakeGameFrame extends AbstractGameFrame implements Observer {

	private JLabel scoreLabel;
	private JLabel levelLabel;
	private UserInterface ui;

	/**
	 * Starts game from main menu by calling {@link #startGame()}
	 * 
	 * @param controller object from main menu
	 */
	public SnakeGameFrame(MainMenuGUIController controller) {
		super(SnakeConsts.GAME_TITLE, controller);
		startGame();
		
		this.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	ui.getGameBody().GameOver();
				setVisible(false);
				dispose();
		    }
		});

	}

	/**
	 * Main snake game start function
	 * <p>
	 * Creates map, init user interface and organise swing objects
	 * </p>
	 */
	public void startGame() {

		Map map = new Map(SnakeConsts.MAP_HEIGHT, SnakeConsts.MAP_WIDTH);
		ui = new UserInterface(map);

		// add observers
		ui.getGameBody().getScore().addObserver(this);
		ui.getGameBody().getLevel().addObserver(this);
		ui.getGameBody().addObserver(this);

		addKeyListener(ui);

		// Creates top menu labels
		scoreLabel = new GameScoreLabel();
		levelLabel = new GameLevelLabel();
		((GameLevelLabel) levelLabel).updateDisplayedLevel(ui.getGameBody().getLevel().getValue());

		// Organise Panels and other gui objects
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(Color.BLACK);
		northPanel.add(levelLabel, BorderLayout.WEST);
		northPanel.add(scoreLabel, BorderLayout.EAST);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(ui, BorderLayout.CENTER);

		getContentPane().add(mainPanel);
		getContentPane().setBackground(Color.WHITE);

		pack();
		setVisible(true);

	}

	/**
	 * Observer interface's implemented method
	 * 
	 */
	@Override
	public void update(Observable o, Object data) {
		((GameScoreLabel) scoreLabel).updateDisplayedScore(ui.getGameBody().getScore().getValue());
		((GameLevelLabel) levelLabel).updateDisplayedLevel(ui.getGameBody().getLevel().getValue());

		if (ui.getGameBody().getState() == SnakeConsts.STATE_END) {
			setVisible(false);
			dispose();
		}
	}

	/**
	 * Adjust snake game window size to main menu implementation
	 */
	public Dimension getPreferredSize() {
		return new Dimension(GUIConsts.CONTENT_WIDTH + 3, GUIConsts.CONTENT_HEIGHT + 62);
	}
}
