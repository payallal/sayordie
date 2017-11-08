package multiplayer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	private String ip;
	private int port;
	private Socket connection;
	private BufferedReader br;
	private PrintWriter pw;

	public GameClient (String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		try {
			this.connection = new Socket(this.ip, this.port);
			this.br = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			this.pw = new PrintWriter(this.connection.getOutputStream());
			//once client has made a connection with server, we run the readkeythread to listen for updates from server
			ReadKeyThread readKeyThread = new ReadKeyThread(this, this.br);
			readKeyThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void sendJSONToServer(boolean [] barray) {
		WriteJSONThread writeJSONThread = new WriteJSONThread(this.pw, barray);
		writeJSONThread.start();
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
