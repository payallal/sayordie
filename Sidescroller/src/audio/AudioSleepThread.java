package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

public class AudioSleepThread extends Thread{
	
	private AudioHandlerThread alt;
	private int recordTime; 
	
	public AudioSleepThread(AudioHandlerThread alt, int recordTime) {
		this.alt = alt;
		this.recordTime = recordTime;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(AudioSleepThread. this.recordTime);
			this.alt.getRecorder().setIsRunning(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
