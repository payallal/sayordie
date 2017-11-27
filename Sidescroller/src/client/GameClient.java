package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import multiplayer.WriteJSONThread;
/**
 * 
 * @author Roger
 *
 */
public class GameClient {
	/**
	 * Stores IP address.
	 */
	private String ip;
	/**
	 * Stores port number.
	 */
	private int port;
	/**
	 * Stores connection socket.
	 */
	private Socket connection;
	/**
	 * Stores instance of buffered reader for text input stream from JSON.
	 */
	private BufferedReader br;
	/**
	 * Stores instance of print writer for text output stream.
	 */
	private PrintWriter pw;
	/**
	 * Constructor to initialize thread fields.
	 * @param ip  IP address
	 * @param port  port number
	 */
	public GameClient (String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * Starts server connection thread.
	 */
	public void scanningServerLoop() {
		ServerConnectorThread sct = new ServerConnectorThread(this.ip, this.port, this);
		sct.start();
	}
	/**
	 * Passes boolean array and starts the thread to translate array to JSON.
	 * @param barray
	 */
	public void sendJSONToServer(boolean [] barray) {
		WriteJSONThread writeJSONThread = new WriteJSONThread(this.pw, barray);
		writeJSONThread.start();
	}
	/**
	 * Setter function to set connection socket.
	 * @param s connection socket.
	 */
	public void setConnection (Socket s) {
		this.connection = s;
	}
	/**
	 * Setter function to set buffered reader.
	 * @param br instance of buffered reader.
	 */
	public void setBufferedReader (BufferedReader br) {
		this.br = br;
	}
	/**
	 * Setter function to set print writer.
	 * @param pw instance of print writer.
	 */
	public void setPrintWriter (PrintWriter pw) {
		this.pw = pw;
	}
	/**
	 * Closes print reader, buffered writer and connection socket.
	 */
	public void closeConnection() {
		try {
			this.pw.close();
			this.br.close();
			this.connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
