package audio;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Obstacle;

public class AudioPlayerThread extends Thread {
	
	private Obstacle obstacle;
	
	public AudioPlayerThread(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	
	@Override
	public void run() {
		this.playAudio();
	}
	
	public void playAudio() {
	    try {
	        URL url = this.getClass().getResource(this.obstacle.getAudioPath());
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
