package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

public class AudioListenerThread extends Thread{
	
	private SoundRecordingUtil recorder;
	private static final int RECORD_TIME = 10000;   // 10 seconds 
	private File wavFile = new File("record.wav");
	
	public AudioListenerThread(SoundRecordingUtil recorder) {
		this.recorder = recorder;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Recording start:");
			AudioSleepThread ast = new AudioSleepThread(this);
			ast.start();
			this.recorder.start();
			this.stopAndSaveFile();
			
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			System.exit(-1);
		} 
	}
	
	public void stopAndSaveFile() {
		try {
			this.recorder.stop();
			System.out.println("Recording Stopped!");
			this.recorder.save(this.wavFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SoundRecordingUtil getRecorder() {
		return this.recorder;
	}
	
}
