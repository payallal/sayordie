package audio;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Obstacle;
/**
 * Thread to handle audio played when a game obstacle is encountered.
 * @author Roger
 *
 */
public class AudioPlayerThread extends Thread {
	/**
	 * Stores instance of the game obstacle associated with audio.
	 * @see model.Obstacle
	 */
	private Obstacle obstacle;
	/**
	 * Constructor for thread that sets game obstacle.
	 * @param obstacle instance of game obstacle.
	 * @see model.Obstacle
	 */
	public AudioPlayerThread(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	
	@Override
	/**
	 * Starts function to play audio file.
	 */
	public void run() {
		this.playAudio();
	}
	/**
	 * Plays audio clip associated with game obstacle.
	 */
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
