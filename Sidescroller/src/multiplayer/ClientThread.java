package multiplayer;
import java.net.*;

import java.io.*;

/**
 * Handles exchange of data in multiplayer mode.
 * @author Roger
 *
 */
public class ClientThread extends Thread {
	/**
	 * Stores client socket.
	 */
	private Socket client;
	/**
	 * Stores instance of thread for movement interpretation.
	 * @see multiplayer.ReadMovementThread
	 */
	private ReadMovementThread readMovementThread;
	/**
	 * Stores instance of print writer for text output stream.
	 */
	private PrintWriter pw;
	/**
	 * Stores instance of buffered reader for text input stream from JSON.
	 */
	private BufferedReader br;
	/**
	 * Constructor that initializes the class fields.
	 * @param c instance of client socket.
	 */
	public ClientThread (Socket c) {
		this.client = c;
		try {
			this.pw = new PrintWriter(this.client.getOutputStream());	
			this.br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.readMovementThread = new ReadMovementThread(this.br);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Starts thread to send array of booleans for movement to client.
	 * @param barray
	 */
	public void sendJSONToClient(boolean [] barray) {
		WriteJSONThread writeJSONThread = new WriteJSONThread(this.pw, barray);
		writeJSONThread.start();
	}
	
	
	/*
	public synchronized void sendConfirmation()
	{
		this.pw.println("Message from KeyThread to Client: JSON Received");
		this.pw.flush();
	}
	*/

	@Override
	/**
	 * Starts this thread.
	 */
	public void run()
	{
		this.readMovementThread.start();
	}
	
	//method that will 
	/**
	 * Disconnect this instance of the client.
	 */
	public void cleanConnection()
	{
		System.out.println("Client disconnecting, cleaning the data!");
		this.pw.close();
		try {
			this.br.close();
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
