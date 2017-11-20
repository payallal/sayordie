package audio;


public class AudioSleepThread extends Thread{
	
	private int recordTime; 
	private AudioHandlerThread aht;
	
	public AudioSleepThread(AudioHandlerThread aht, int recordTime) {
		this.recordTime = recordTime;
		this.aht = aht;
	}
	
	@Override
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
