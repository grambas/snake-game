package de.hshannover.inform.dunkleit.gruppe12.util;

import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * Primitiver ImageLoader zum Laden von Grafiken aus zip-/jar-Dateien (inkl. Chaching).
 * <br>Copyright:     Copyright by Andreas Holitschke, 2016
 * <br>Organisation:  HS Hannover  Fak. IV/ Abt. Informatik
 * @author       Andreas Holitschke
 */
public class ImageLoader {
  private HashMap<String, ImageIcon> mImgHash= new HashMap<String, ImageIcon>();
  
	//############################################################################	
	/**
	 * SINGLETON
	 */
  protected static volatile transient ImageLoader mInstance;
	
	//############################################################################
	/**
	 * SINGLETON
	 */
	public static synchronized ImageLoader getInstance() {
		if (mInstance == null)
			mInstance = new ImageLoader();
		return mInstance;
	}

	//############################################################################
	/**
	 * SINGLETON
	 */
	protected ImageLoader() {}
	
  //############################################################################
	/**
	 * Laden einer Grafik aus einer Datei und Caching innerhalb einer 
	 * <code>HashMap</code>.
	 */
  public ImageIcon loadImageIcon(String name){
  	ImageIcon img = mImgHash.get(name);
    if (img==null){
      final ClassLoader cl = this.getClass().getClassLoader();
			img = new ImageIcon(cl.getResource(name));
      mImgHash.put(name, img);
    }
    return img;
  }
}