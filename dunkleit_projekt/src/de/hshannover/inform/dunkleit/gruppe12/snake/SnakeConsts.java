package de.hshannover.inform.dunkleit.gruppe12.snake;

/**
 * <h1>Snake game constants</h1>
 * @author Mindaugas Milius
 * @version 1.0
 */


public class SnakeConsts {

	public static final String GAME_IDENTIFIER = "snake";
	public static final String GAME_TITLE = "Snake";
	
	
	//GAME STATES
	public static final int STATE_GAMEOVER = 0;
	public static final int STATE_RUNNING = 1;
	public static final int STATE_BEGIN = 2;
	public static final int STATE_END = 3;


	//SNAKE LEVELS
	public static final int LEVEL1 = 400;
	public static final int LEVEL2 = 200;
	public static final int LEVEL3 = 120;
	
	//MAP ARRAY DIMENSIONS
	public static final int MAP_WIDTH = 20;
	public static final int MAP_HEIGHT = 15;
	//MAP SCALE
	public static final int SCALE = 40;

	//SOUNDS
	public static final String BG_MUSIC = "snake/game.mp3";
	public static final String EAT_SOUND = "snake/eat.mp3";
//http://www.freesfx.co.uk
	public static final String GAMEOVER_SOUND = "snake/gameover.mp3";
//https://www.freesound.org/people/AdamWeeden/sounds/157218/
	
	
	//IMAGE PATHS
	public static final String VIRUS1_IMG = "snake/virus1.png";
	
	public static final String HEAD_L_IMG = "snake/s_head_h_l.png";
	public static final String HEAD_R_IMG = "snake/s_head_h_r.png";
	public static final String HEAD_U_IMG = "snake/s_head_v_u.png";
	public static final String HEAD_D_IMG = "snake/s_head_v_d.png";

	public static final String BODY_V_IMG = "snake/s_body_v.png";
	public static final String BODY_H_IMG = "snake/s_body_h.png";
	
	//https://github.com/rembound/Snake-Game-HTML5
	public static final String LD_DL = "snake/LD_DL.png";
	public static final String LU_UL = "snake/LU_UL.png";
	public static final String RU_UR = "snake/RU_UR.png";
	public static final String RD_DR = "snake/RD_DR.png";

	
	
	//SNAKE MOVE DIRECTIONS
	public static final int LEFT = 4;
	public static final int RIGHT = 3;
	public static final int DOWN = 2;
	public static final int UP = 1;
	public static final int DEFAULT_DIRECTION = 4;
	
	
	
	//********************************************************
	//SNAKE MAP VALUES
	//********************************************************
	
	
	//VIRUS AND EMPTY
	public static final int MAP_EMPTY = 0;
	public static final int MAP_VIRUS = 1;
	
	//SNAKE HEAD
	public static final int MAP_HEAD_DOWN = 3;
	public static final int MAP_HEAD_UP = 	4;
	public static final int MAP_HEAD_LEFT = 5;
	public static final int MAP_HEAD_RIGHT =6;
	
	//SNAKE BODY
	public static final int MAP_BODY_DOWN = 13;
	public static final int MAP_BODY_UP = 	14;
	public static final int MAP_BODY_LEFT = 15;
	public static final int MAP_BODY_RIGHT =16;
	
	//SNAKE CORNERS
	public static final int MAP_FROM_R_TO_D = 24;
	public static final int MAP_FROM_D_TO_R = 24;
	
	public static final int MAP_FROM_D_TO_L = 21;
	public static final int MAP_FROM_L_TO_D = 21;
	
	public static final int MAP_FROM_U_TO_L = 22;
	public static final int MAP_FROM_L_TO_U = 22;
	
	public static final int MAP_FROM_R_TO_U = 23;
	public static final int MAP_FROM_U_TO_R = 23;
	
	

}
