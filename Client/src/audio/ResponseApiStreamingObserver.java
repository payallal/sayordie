package audio;
import java.util.List;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.common.util.concurrent.SettableFuture;

//defining a whole new class and override each of the methods for apistreamobserver
  class ResponseApiStreamingObserver<T> implements ApiStreamObserver<T> {
	  private final SettableFuture<List<T>> future = SettableFuture.create();
	  private final List<T> messages = new java.util.ArrayList<T>();

	  @Override
	  public void onNext(T message) {
	    messages.add(message);
	    this.onCompleted();
	  }

	  @Override
	  public void onError(Throwable t) {
		  future.setException(t);
	  }

	  @Override
	  public void onCompleted() {
		  future.set(messages);
	  }

	  // Returns the SettableFuture object to get received messages / exceptions.
	  public SettableFuture<List<T>> future() {
		  return future;
	  }
  }