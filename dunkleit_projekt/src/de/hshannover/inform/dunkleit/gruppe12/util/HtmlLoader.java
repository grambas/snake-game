package de.hshannover.inform.dunkleit.gruppe12.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Primitiver HtmlLoader zum Laden von Html-Dateien aus zip-/jar-Dateien (inkl. Chaching).
 * <br>Basiert auf <code>ImageLoader</code>
 * @author	dierschke
 */
public class HtmlLoader {
  private HashMap<String, String> mHtmlHash= new HashMap<String, String>();
  
	//############################################################################	
	/**
	 * SINGLETON
	 */
  protected static volatile transient HtmlLoader mInstance;
	
	//############################################################################
	/**
	 * SINGLETON
	 */
	public static synchronized HtmlLoader getInstance() {
		if (mInstance == null)
			mInstance = new HtmlLoader();
		return mInstance;
	}

	//############################################################################
	/**
	 * SINGLETON
	 */
	protected HtmlLoader() {}
	
  //############################################################################
	/**
	 * Laden von HTML-Code aus einer Datei und Caching innerhalb einer 
	 * <code>HashMap</code>.
	 */
  public String loadHtmlCode(String name){
  	String html = mHtmlHash.get(name);
    if (html==null){
      final ClassLoader cl = this.getClass().getClassLoader();
      Scanner scnr = new Scanner(cl.getResourceAsStream(name), "UTF-8");
      html = scnr.useDelimiter("\\Z").next();
      mHtmlHash.put(name, html);
      scnr.close();
    }
    return html;
  }
  
	public List<String> getHtmlResources(String name) {
		ArrayList<String> reslist = new ArrayList<>();
		final ClassLoader cl = this.getClass().getClassLoader();
		
		InputStream resFile = cl.getResourceAsStream(name);
		
		if (resFile != null) {
			Scanner scnr = new Scanner(cl.getResourceAsStream(name), "UTF-8");
			while (scnr.hasNextLine()) {
				reslist.add(scnr.nextLine());
			}
			scnr.close();
		}
		
		return reslist;
	}
}