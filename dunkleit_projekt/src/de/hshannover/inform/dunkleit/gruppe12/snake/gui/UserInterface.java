package de.hshannover.inform.dunkleit.gruppe12.snake.gui;

import javax.swing.*;

import de.hshannover.inform.dunkleit.gruppe12.snake.engine.DisplayListener;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.GameBody;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.GameEvent;
import de.hshannover.inform.dunkleit.gruppe12.snake.engine.Map;
import de.hshannover.inform.dunkleit.gruppe12.util.ImageLoader;
import de.hshannover.inform.dunkleit.gruppe12.snake.SnakeConsts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInterface extends JPanel implements DisplayListener, KeyListener{
	
	private Map game_map;
	private GameBody game_body;
	
	
	/**
	 * UI Constructor 
	 * 
	 * @param map 2D Gameboard array
	 */	
	public UserInterface(Map map){
		this.game_map = map;
		this.game_body = new GameBody(this, map);
		this.setBackground(Color.WHITE);
		//game_body.startNew(levelDialog());
		
		int c = levelDialog();
		System.out.println(c);
		if(c == 0){
			handleGameOver();
		}else{
			game_body.startNew(c);
		}
	
		
		
		this.repaint();
	}
		
	
	/**
	 * Paint map
	 * @param g graphic parameter
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;		
		this.paintMap(g2);
	}
	
	
	/**
	 * Show select level dialog box
	 * 
	 * @return level(adding +1 because showOptionDialog starts from 0)
	 */
	public int levelDialog(){
	    String[] buttons = { "LEVEL 1", "LEVEL 2", "LEVEL 3" };
		int choice = JOptionPane.showOptionDialog(this, "Select level", "Level",
		        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
		return choice+1;
	}
	
	/**
	 * Show gameover dialog box
	 * 
	 * @return JOptionPane.YES_OPTION; JOptionPane.NO_OPTION; else -1
	 */
	public int gameoverDialog() throws IllegalStateException
	{		
	  // display the showOptionDialog
	  Object[] options = { "Play Again", "Return to Menu" };
	  int choice = JOptionPane.showOptionDialog(null, 
	      "Your Score: "+game_body.getScore().getValue(), 
	      "GameOver", 
	      JOptionPane.YES_NO_OPTION, 
	      JOptionPane.QUESTION_MESSAGE, 
	      null, 
	      options, 
	      options[0]);
	  
	  return choice;

	}
	
	public void handleGameOver(){
		this.game_body.GameOver();
    	int shouldStartAgain = gameoverDialog();
    	if (shouldStartAgain == JOptionPane.YES_OPTION)
    	{	
			int c = levelDialog();
			System.out.println(c);
			if(c == 0){
				handleGameOver();
			}else{
				game_body.startNew(c);
			}
    		
    	}else if(shouldStartAgain == JOptionPane.NO_OPTION || shouldStartAgain == -1){
    		game_body.setState(SnakeConsts.STATE_END);
    		this.setVisible(false);
    	}
	}
	
	
	/**
	 * Paint map with Snake body part images, virus e.t.c
	 * 
	 * @param g2 Graphics2D Parameter
	 */
	private void paintMap(Graphics2D g2){

		for(int y = 0; y < this.game_map.getHeight(); y++){
			for(int x = 0; x < this.game_map.getWidth(); x++){
				if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_BODY_DOWN || this.game_map.getMapValue(y,x) == SnakeConsts.MAP_BODY_UP){

					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.BODY_V_IMG).getImage(),
										x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
						
				}else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_BODY_LEFT || this.game_map.getMapValue(y,x) == SnakeConsts.MAP_BODY_RIGHT){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.BODY_H_IMG).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);			
				}
				
				else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_HEAD_UP){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.HEAD_U_IMG).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);			
				}else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_HEAD_DOWN){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.HEAD_D_IMG).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);			
				}else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_HEAD_RIGHT){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.HEAD_R_IMG).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);			
				}else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_HEAD_LEFT){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.HEAD_L_IMG).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);			
				}
				
				
				else if(this.game_map.getMapValue(y,x) == SnakeConsts.MAP_VIRUS){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(game_body.getVirusImg()).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
				}
				
				
				else if(this.game_map.getMapValue(y,x) == 31){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.LD_DL).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
				}else if(this.game_map.getMapValue(y,x) == 32){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.LU_UL).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
				}else if(this.game_map.getMapValue(y,x) == 33){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.RU_UR).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
				}else if(this.game_map.getMapValue(y,x) == 34){
					g2.drawImage(ImageLoader.getInstance().loadImageIcon(SnakeConsts.RD_DR).getImage(),
							x * SnakeConsts.SCALE, y * SnakeConsts.SCALE,null);
				}
			}
		}
	}
	

	/**
	 * This method is called with every snake step.
	 * Checking game status and calling dialog boxes if Game is just started or gameover
	 * @param e GameEvent object
	 */
	public void update(GameEvent e){

		//get map object from GameEvent object
		this.game_map = e.getMap();
		
		
//		//Check if game is just started
//		if(e.getState() == SnakeConsts.STATE_BEGIN){
//			int c = levelDialog();
//			System.out.println(c);
//			if(c == -1){
//				handleGameOver();
//			}else{
//				game_body.startNew(c);
//			}
//		}
		
		//Check if game turned into gameover status in GameEvent and 
		if(e.getState() == SnakeConsts.STATE_GAMEOVER){
			
				handleGameOver();

		}

		this.repaint();
	}
	

	
	/**
	 * Process keybord input
	 * @param e Keybord event
	 */
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			this.game_body.getSnake().changeDirection(SnakeConsts.UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.game_body.getSnake().changeDirection(SnakeConsts.DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.game_body.getSnake().changeDirection(SnakeConsts.LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.game_body.getSnake().changeDirection(SnakeConsts.RIGHT);
		}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			handleGameOver();
		}
	}

	//keyReleased and keyTyped methods mustbe implimentated
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Getter for GameBody object
	 * @return GameBody object
	 */
	public GameBody getGameBody(){ return this.game_body; }

}