package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	
	public BGSound (String file) {
		if(Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()");
			System.exit(0);
		}
		while(m == null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + file);
				
				//runnable is attached when media is finished playing as the last param
				m = MediaManager.createMedia(is, "audio/wav", this);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * pauses the playing sound
	 */
	public void pause() {
		m.pause();
	}
	/**
	 * continues to play the sound where it left off
	 */
	public void play() {
		m.play();
	}
	/**
	 * starts playing from 0
	 */
	@Override
	public void run() {
		m.setTime(0);	
		m.play();
	}
	
	
}
