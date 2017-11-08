package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

public class AudioSleepThread extends Thread{
	
	private AudioListenerThread alt;
	private static final int RECORD_TIME = 10000;   // 10 seconds 
	
	public AudioSleepThread(AudioListenerThread alt) {
		this.alt = alt;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(AudioSleepThread.RECORD_TIME);
			this.alt.getRecorder().setIsRunning(false);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
