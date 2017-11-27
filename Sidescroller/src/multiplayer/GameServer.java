package multiplayer;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Handles multiplayer communications via sockets.
 * @author Roger
 *
 */
public class GameServer {
	/**
	 * Stores the server socket to be used.
	 */
	private ServerSocket servSock;
	/**
	 * Stores array list of client threads to communicate with.
	 * @see client.ClientThread
	 */
	private ArrayList<ClientThread> clientList;

	/**
	 * Constructor to initialize the socket and client list.
	 * @param port port for socket server.
	 */
	public GameServer (int port) {
		try {
			this.servSock = new ServerSocket(port);
			this.clientList = new ArrayList<ClientThread>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts client connector thread.
	 * @see multiplayer.ClientConnectorThread
	 */
	public void acceptClientLoop() {
		ClientConnectorThread cct = new ClientConnectorThread(this, this.servSock);
		cct.start();
	}
	/**
	 * Sends array of movement data to the client thread via JSON.
	 * @see multiplayer.ClientThread
	 * @param barray array of boolean values associated with movements.
	 */
	public void sendJSONToClients(boolean[] barray) {
		for (ClientThread ct: this.clientList) {
			ct.sendJSONToClient(barray);
		}
	}
	/**
	 * Adds client thread to array list of client threads.
	 * @param ct Instance of client thread.
	 * @see multiplayer.ClientThread
	 */
	public void addToClientList(ClientThread ct) {
		this.clientList.add(ct);
	}
	
	/*
	public void broadcastMessage(String message, ClientThread sender) {
		//for each client but the sender, send message
		
	}
	*/
}
