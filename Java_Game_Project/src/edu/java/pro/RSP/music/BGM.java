package edu.java.pro.RSP.music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
	private Clip clip;
	
	public BGM() {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("music/rspbgm.wav"));
			this.clip = AudioSystem.getClip();
			this.clip.open(ais);
			
			// 소리 설정
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			// 볼륨 조정
			gainControl.setValue(-20.0f);
			
			clip.start();
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
