package multiplayer;

import java.io.PrintWriter;

/**
 * Thread that writes JSON for multiplayer communications.
 * @author Roger
 *
 */
public class WriteJSONThread extends Thread{
	
	/**
	 * Stores instance of print writer for text output stream.
	 */
	private PrintWriter pw;
	/**
	 * Array of boolean values to controll player movements.
	 */
	private boolean [] barray;
	
	/** 
	 * Constructor to initialize print writer and boolean array
	 * @param pw instance of print writer.
	 * @param barray array of character movement booleans.
	 */
	public WriteJSONThread (PrintWriter pw, boolean [] barray) {
		this.pw = pw;
		this.barray = barray;
	}
	
	@Override
	/**
	 * Starts thread to create JSON in correct format.
	 */
	public void run() {
		this.pw.println(
				"  {" +
		        "   \"start\": " + barray[0] + "," +
		        "   \"right\" : " + barray[1] + "," +
		        "   \"jump\" : " + barray[2] + "," +
		        "   \"stop\" : " + barray[3] +	
		        " }");
		this.pw.flush();
	}
}
