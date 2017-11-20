package audio2;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.protobuf.ByteString;

public class RequestThread extends Thread {
	  
	  private ApiStreamObserver<StreamingRecognizeRequest> requestObserver;
	  private byte[] tempBuffer;
	  
	  public RequestThread(ApiStreamObserver<StreamingRecognizeRequest> requestObserver, byte[] tempBuffer) {
		  this.requestObserver = requestObserver;
		  this.tempBuffer = tempBuffer;
	  }
	  
	  public synchronized void synchronizedWrite(byte tempBuffer[]) {
          this.requestObserver.onNext(StreamingRecognizeRequest.newBuilder()
			      .setAudioContent(ByteString.copyFrom(tempBuffer))
			      .build());
	  }

	  @Override
	  public void run() {
			  this.synchronizedWrite(tempBuffer);
      }
	     
}
