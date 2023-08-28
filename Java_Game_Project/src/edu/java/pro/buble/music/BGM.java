package edu.java.pro.buble.music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
	private Clip clip;
	public BGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("music/bgm.wav"));	
			this.clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			
			// 소리설정
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			// 볼륨조정
			gainControl.setValue(-30.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void stop() {
	    if (clip != null && clip.isRunning()) {
	        clip.stop();
	        clip.close();
	    }
	}	
	

}
