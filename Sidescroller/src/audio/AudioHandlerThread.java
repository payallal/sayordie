package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import controller.Controller;

public class AudioHandlerThread extends Thread{
	
	private AudioRecordingUtil recorder;
	private AudioInterpreterUtil interpreter;
	private static final int RECORD_TIME = 1000;   // 4 seconds 
	private String rawFileName = "record.raw";
	private File rawFile = new File(this.rawFileName);
	private Controller controller;
	
	public AudioHandlerThread(AudioRecordingUtil recorder, AudioInterpreterUtil interpreter) {
		this.recorder = recorder;
		this.interpreter = interpreter;
		this.controller = Controller.getSingleton();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Recording start:");
			AudioSleepThread ast = new AudioSleepThread(this, RECORD_TIME);
			ast.start();
			this.recorder.start();
			this.stopAndSaveFile();
			System.out.println("Interpreting start:");
			String result = this.interpreter.interpret(this.rawFileName);
			this.controller.convertStringToMovement(result);
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void stopAndSaveFile() {
		try {
			this.recorder.stop();
			System.out.println("Recording stopped:");
			this.recorder.save(this.rawFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AudioRecordingUtil getRecorder() {
		return this.recorder;
	}
	
	
}
