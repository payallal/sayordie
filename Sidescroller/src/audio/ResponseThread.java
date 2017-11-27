package audio;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;

import controller.Controller;

/**
 * Represents thread to receive transcription response for audio sent to Google's server.
 * @author Roger
 *
 */
public class ResponseThread extends Thread {
	
	 /**
	  * Stores instance of the observer for responses from the API.
	  * @see audio.ResponseApiStreamingObserver
	  */
	  private ResponseApiStreamingObserver<StreamingRecognizeResponse> responseObserver;
	  /**
	   * Stores instance of game controller.
	   * @see controller.Controller
	   */
	  private Controller c;
	  
	  /**
	   * Constructor for response thread. Initializes response observer and controller.
	   * @param responseObserver
	   */
	  public ResponseThread(ResponseApiStreamingObserver<StreamingRecognizeResponse> responseObserver) {
		  this.responseObserver = responseObserver;
		  this.c = Controller.getSingleton();
	  }
	  
	  @Override 
	  /**
	   * Receives audio transcription from Google's server and sets to a string.
	   * Passes the string to the controller to perform in-game actions and displays transcription on the screen.
	   * @see controller.Controller
	   */
	  public void run() {
		  
		List<StreamingRecognizeResponse> responses;
		try {
			//now this is blocking
			responses = responseObserver.future().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		for (StreamingRecognizeResponse response: responses) {
		    // For streaming recognize, the results list has one is_final result (if available) followed
		    // by a number of in-progress results (if iterim_results is true) for subsequent utterances.
		    // Just print the first result here.
		    com.google.cloud.speech.v1.StreamingRecognitionResult result = response.getResultsList().get(0);
		    // There can be several alternative transcripts for a given chunk of speech. Just use the
		    // first (most likely) one here.
		    SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
		    String resultString = alternative.getTranscript();
		    this.c.convertStringToMovement(resultString);
		    this.c.setTextOfWordSaid(resultString);
		    	this.c.setTextOfInstruction("Understood!");	    	
		  }
	  }
}
