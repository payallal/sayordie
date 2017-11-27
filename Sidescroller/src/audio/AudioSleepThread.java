package audio;

/**
 * Thread that handles timing of microphone capture.
 * @author Roger
 *
 */
public class AudioSleepThread extends Thread{
	/**
	 * Stores length of time that the thread sleeps.
	 */
	private int recordTime; 
	/**
	 * Stores instance of the audio handler thread.
	 * @see audio.AudioHandlerThread
	 */
	private AudioHandlerThread aht;
	
	/**
	 * Constructor that sets the relevant instance of the audio handler thread and length
	 * @param aht instance of audio handler thread.
	 * @param recordTime length of recording to indicate the sleep time for this thread.
	 * @see audio.AudioHandlerThread
	 */
	public AudioSleepThread(AudioHandlerThread aht, int recordTime) {
		this.recordTime = recordTime;
		this.aht = aht;
	}
	
	@Override
	/**
	 * Thread sleeps for a set recording time then stops the audio capture.
	 */
	public void run() {
		try {
			Thread.sleep(this.recordTime);
			this.aht.setStopCapture(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
