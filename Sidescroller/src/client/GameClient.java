package client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import multiplayer.ReadMovementThread;
import multiplayer.WriteJSONThread;

public class GameClient {
	private String ip;
	private int port;
	private Socket connection;
	private BufferedReader br;
	private PrintWriter pw;

	public GameClient (String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
	}
	
	public void scanningServerLoop() {
		ServerConnectorThread sct = new ServerConnectorThread(this.ip, this.port, this);
		sct.start();
	}
	
	public void sendJSONToServer(boolean [] barray) {
		WriteJSONThread writeJSONThread = new WriteJSONThread(this.pw, barray);
		writeJSONThread.start();
	}
	
	public void setConnection (Socket s) {
		this.connection = s;
	}
	
	public void setBufferedReader (BufferedReader br) {
		this.br = br;
	}
	
	public void setPrintWriter (PrintWriter pw) {
		this.pw = pw;
	}
	
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
