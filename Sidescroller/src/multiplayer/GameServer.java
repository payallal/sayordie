package multiplayer;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class GameServer {
	private ServerSocket servSock;
	private ArrayList<ClientThread> clientList;

	public GameServer (int port) {
		try {
			this.servSock = new ServerSocket(port);
			this.clientList = new ArrayList<ClientThread>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptClientLoop() {
		while (true) {
			Socket c;
			try {
				c = this.servSock.accept();
				ClientThread clientThread = new ClientThread(c);
				this.clientList.add(clientThread);
				clientThread.start();
				System.out.println("Just accepted a client. Going to the next iteration");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendJSONToClients(boolean[] barray) {
		for (ClientThread ct: this.clientList) {
			ct.sendJSONToClient(barray);
		}
	}
	
	/*
	public void broadcastMessage(String message, ClientThread sender) {
		//for each client but the sender, send message
		
	}
	*/
}
