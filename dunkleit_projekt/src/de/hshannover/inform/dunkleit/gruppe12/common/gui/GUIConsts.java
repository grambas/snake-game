package de.hshannover.inform.dunkleit.gruppe12.common.gui;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Programmübergreifende GUI Konstanten
 * 
 * @author dierschke
 */
public final class GUIConsts {
	
	public static final int CONTENT_WIDTH = 800;
	public static final int CONTENT_HEIGHT = 600;
	public static final float RESIZE_FACTOR = 1.0f;
	
	public static final Dimension CONTENT_SIZE = new Dimension((int)(RESIZE_FACTOR * CONTENT_WIDTH), (int)(RESIZE_FACTOR * CONTENT_HEIGHT));
	
	public static final float HUD_TEXT_SIZE = RESIZE_FACTOR * 24;
	
	public static final Color COLOR_MATRIX_GREEN = new Color(0x00FD00);
}
