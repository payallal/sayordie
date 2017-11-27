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
		ClientConnectorThread cct = new ClientConnectorThread(this, this.servSock);
		cct.start();
	}
	
	public void sendJSONToClients(boolean[] barray) {
		for (ClientThread ct: this.clientList) {
			ct.sendJSONToClient(barray);
		}
	}
	
	public void addToClientList(ClientThread ct) {
		this.clientList.add(ct);
	}
	
	/*
	public void broadcastMessage(String message, ClientThread sender) {
		//for each client but the sender, send message
		
	}
	*/
}
