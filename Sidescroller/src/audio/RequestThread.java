package audio;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.protobuf.ByteString;

/**
 * Used for sending messages in bidi (bidirectional) or client-streaming calls byte by byte.
 * @author Roger
 *
 */
public class RequestThread extends Thread {
	  
	/**
	 * Stores an instance of the API's Stream observer to receive notifications.
	 */
	  private ApiStreamObserver<StreamingRecognizeRequest> requestObserver;
	  /**
	   * Stores information to be sent byte by byte to the server.
	   */
	  private byte[] tempBuffer;
	  
	  /**
	   * Constructor that intializes thread fields.
	   * @param requestObserver stream observer to recieve notifications.
	   * @param tempBuffer buffer of information to be sent to server.
	   */
	  public RequestThread(ApiStreamObserver<StreamingRecognizeRequest> requestObserver, byte[] tempBuffer) {
		  this.requestObserver = requestObserver;
		  this.tempBuffer = tempBuffer;
	  }
	  /**
	   * Send requests to the server byte by byte.
	   * @param tempBuffer infomation to be sent byte by byte.
	   */
	  public synchronized void synchronizedWrite(byte tempBuffer[]) {
          this.requestObserver.onNext(StreamingRecognizeRequest.newBuilder()
			      .setAudioContent(ByteString.copyFrom(tempBuffer))
			      .build());
	  }

	  @Override
	  /**
	   * Starts thread that activates the writing function to send requests.
	   */
	  public void run() {
			  this.synchronizedWrite(tempBuffer);
      }
	     
}
