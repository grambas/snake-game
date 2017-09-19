package de.hshannover.inform.dunkleit.gruppe12.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
/**
 * Primitiver MusicLoader zum Laden von Clips aus zip-/jar-Dateien (inkl. Caching). <br>
 * Basiert auf <code>Clip</code>
 * 
 * @author Kerim Jdiya
 */
public class MusicLoader {
	private HashMap<String, Clip> mMusicHash = new HashMap<String, Clip>();

	// ############################################################################
	/**
	 * SINGLETON
	 */
	protected static volatile transient MusicLoader mInstance;

	// ############################################################################
	/**
	 * SINGLETON
	 */
	public static synchronized MusicLoader getInstance() {
		if (mInstance == null)
			mInstance = new MusicLoader();
		return mInstance;
	}

	// ############################################################################
	/**
	 * SINGLETON
	 */
	protected MusicLoader() {
	}

	// ############################################################################
	/**
	 * Laden eines Clips aus einer Datei und Caching innerhalb einer <code>HashMap</code>.
	 */
	public Clip loadClip(String name) {
		Clip clip = mMusicHash.get(name);
		if (clip == null) {
			clip = loadClipNoCache(name);
			mMusicHash.put(name, clip);
		}
		return clip;
	}
	
	public Clip loadClipNoCache(String name) {
		Clip clip = null;
		URL url = this.getClass().getClassLoader().getResource(name); // Legt Pfad derSounddatei fest
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn); // Lädt die Sounddatei
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Das Audioformat " + name.substring(name.indexOf('.')) + " wird nicht unterstützt!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Die Datei " + name + " konnte nicht gefunden werde!");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		return clip;
	}
	
	public Clip loadMP3Clip(String name) {
		Clip clip = mMusicHash.get(name);
		if (clip == null) {
			clip = loadMP3ClipNoCache(name);
			mMusicHash.put(name, clip);
		}
		return clip;
	}
	
	public Clip loadMP3ClipNoCache(String name) {
		Clip clip = null;
		URL url = this.getClass().getClassLoader().getResource(name); // Legt den Pfad der Sounddatei fest
		try {
			AudioInputStream audioIn = new MpegAudioFileReader().getAudioInputStream(url);
			AudioFormat baseFormat = audioIn.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(), 
					baseFormat.getChannels() * 2, 
					baseFormat.getSampleRate(), 
					false);

			AudioInputStream decodedAudioIn = AudioSystem.getAudioInputStream(decodeFormat, audioIn);

			clip = AudioSystem.getClip();
			clip.open(decodedAudioIn); // Lädt die Sounddatei
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Das Audioformat " + name.substring(name.indexOf('.')) + " wird nicht unterstützt!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Die Datei " + name + " konnte nicht gefunden werde!");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		return clip;
	}
}