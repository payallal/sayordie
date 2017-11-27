package audio;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

import controller.Controller;

/**
 * Represents thread which handles the capture and passing of audio the the server for processing.
 * @author Roger, Alexia
 *
 */
public class AudioHandlerThread extends Thread{
	
	//These are default 
	/**
	 * Stores array list of request threads.
	 */
	 ArrayList<RequestThread> reqtArray = new ArrayList<RequestThread>();
	 /**
	  * Stores boolean value to determine whether or not to stop audio capture.
	  */
	 boolean stopCapture = false;
	 SpeechClient speech;
	 RecognitionConfig recConfig;
	 StreamingRecognitionConfig config;
	 ResponseApiStreamingObserver<StreamingRecognizeResponse> responseObserver;
	 BidiStreamingCallable<StreamingRecognizeRequest,StreamingRecognizeResponse> callable;
	 ApiStreamObserver<StreamingRecognizeRequest> requestObserver;
	 AudioFormat format;
	 byte tempBuffer[];
	 TargetDataLine targetDataLine;
	 ResponseThread respt;
	 
	 /**
	  * Stores the number of second that the audio thread will sleep.
	  */
	 private int seconds;
	 /**
	  * Stores instance of the game controller.
	  * @see controller.Controller
	  */
	 private Controller c;

	 /**
	  * Initializes controller and sets the length of the sleep time of the thread in seconds.
	  * @param seconds number of seconds that the thread sleeps.
	  */
	 public AudioHandlerThread(int seconds) {
		 this.seconds = seconds;
		 this.c = Controller.getSingleton();
	 }
	 
	/**
	 * Sets the format of the audio to be exchanged with the Google server.
	 * @return format of the audio. 
	 */
	public AudioFormat getAudioFormat() {
	      float sampleRate = 16000;
	      int sampleSizeInBits = 16;
	      int channels = 1;
	      boolean signed = true;
	      boolean bigEndian = true;
	      return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
	                bigEndian);
	}

	@Override
	/**
	 * Establishes connection with Google's server and configures request. 
	 * Instantiate response observer custom class, gets audio format, instantiates audio threads and handles recording and passing of audio to Google server.
	 */
	public void run() {	
		
		try {
			
			  // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
			  SpeechClient speech = SpeechClient.create();

			  // Configure request with local raw PCM audio
			  RecognitionConfig recConfig = RecognitionConfig.newBuilder()
			      .setEncoding(AudioEncoding.LINEAR16)
			      .setLanguageCode("en-US")
			      .setSampleRateHertz(16000)
			      .build();
			  StreamingRecognitionConfig config = StreamingRecognitionConfig.newBuilder()
			      .setConfig(recConfig)
			      .build();


			  //instantiate our response observer custom class
			  ResponseApiStreamingObserver<StreamingRecognizeResponse> responseObserver =
			      new ResponseApiStreamingObserver<StreamingRecognizeResponse>();

			  //bidirectional callable
			  BidiStreamingCallable<StreamingRecognizeRequest,StreamingRecognizeResponse> callable =
			      speech.streamingRecognizeCallable();

			  //Connect the request observer with the response observer
			  ApiStreamObserver<StreamingRecognizeRequest> requestObserver =
			      callable.bidiStreamingCall(responseObserver);

			  // The first request must **only** contain the audio configuration:
			  requestObserver.onNext(StreamingRecognizeRequest.newBuilder()
			      .setStreamingConfig(config)
			      .build());
			  
			  //get audio format
			  AudioFormat format = this.getAudioFormat();
			  
			  //have the byte buffer created
			  byte tempBuffer[] = new byte[40000]; 
			  
			  //targetdataline helps us read the data into a buffer
		      TargetDataLine targetDataLine;
		      try {
		    	  	targetDataLine = AudioSystem.getTargetDataLine(format);
		    	  	targetDataLine.open();
		    	  	//don't forget to start the microphone
		    	  	targetDataLine.start();
		    	  	if (this.c.getConnected()) {
		    	  		this.c.setTextOfInstruction("Speak now!");
			    		this.c.setRecordingIndicatorRed();
		    	  	}
		      } catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;	
		      }
		      
			  ResponseThread respt = new ResponseThread(responseObserver);
			  respt.start();
			  
			  //call sleep thread (how long specified by sleep parameter
			  AudioSleepThread ast = new AudioSleepThread(this, seconds);
			  ast.start();
			  
			  while(!this.stopCapture) {
				//this method blocks
				int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
				if (cnt>0) {
					  // Subsequent requests must **only** contain the audio data.
					  RequestThread reqt = new RequestThread(requestObserver, tempBuffer);
					  reqt.start();
					  //Add it to array list
					  this.reqtArray.add(reqt);
				}
			  }
			  
			  this.c.setRecordingIndicatorGrey();
			  if (this.c.getConnected()) {
				  this.c.setTextOfInstruction("Loading...");
			  }
			  
			  //Make sure that each request thread is finished before moving on
			  for (RequestThread reqt : this.reqtArray) {
				  reqt.join();
			  }
			  
			  
			  /*
			  //Make sure that each response thread is finished before moving on. If I wait for response thread to finish nothing gets printed out
			  for (ResponseThread respt: this.resptArray) {
				  respt.join();
			  }*/
			  
			  requestObserver.onCompleted();

			  respt.join();
			  
			  responseObserver.onCompleted();

			  
		      if (targetDataLine != null) {
		          targetDataLine.drain();
		          targetDataLine.close();
		      }
		      
			  speech.close();
			  
			  if (!this.c.getConnected()) {
	    	  		this.c.setConnected(true);
	    	  	  }
			  
			  if (!this.c.getGameInProgress()) {
	    	  		this.c.setTextOfInstruction("Click button and say 'begin'");
			  }
			  else {
				  this.c.setTextOfInstruction("Processed!");
			  }
			  //enable the record button again
			  this.c.getGameWindow().getRecordButton().setEnabled(true);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Setter method for boolean value to determine whether to stop audio capture.
	 * @param b new boolean value to determine whether to stop audio capture.
	 */
	public void setStopCapture(boolean b) {
		this.stopCapture = b;
	}
}
