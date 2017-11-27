package audio;
import java.util.List;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.common.util.concurrent.SettableFuture;

/**
 * A new class to override each of the methods for apistreamobserver.
 * @author Roger
 *
 * @param <T> message returned to client from server.
 */
  class ResponseApiStreamingObserver<T> implements ApiStreamObserver<T> {
	  
	  private final SettableFuture<List<T>> future = SettableFuture.create();
	  private final List<T> messages = new java.util.ArrayList<T>();

	  @Override
	  /**
	   * Receives a value from the stream. 
	   * @param message message returned to client from server.
	   */
	  public void onNext(T message) {
	    messages.add(message);
	    this.onCompleted();
	  }

	  @Override
	  /**
	   * Receives a terminating error from the stream. 
	   * @param t exception to be thrown.
	   */
	  public void onError(Throwable t) {
		  future.setException(t);
	  }

	  @Override
	  /**
	   * Receives a notification of successful stream completion.  
	   */
	  public void onCompleted() {
		  future.set(messages);
	  }

	  /**
	   * Returns the SettableFuture object to get received messages/exceptions.
	   * @return the SettableFuture object.
	   */
	  public SettableFuture<List<T>> future() {
		  return future;
	  }
  }