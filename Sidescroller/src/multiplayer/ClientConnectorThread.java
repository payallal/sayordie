package multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.Controller;

public class ClientConnectorThread extends Thread {
	private ServerSocket servSock;
	private GameServer gs;
	
	public ClientConnectorThread(GameServer gs, ServerSocket ss) {
		this.servSock = ss;
		this.gs = gs;
	}
	
	@Override
	public void run() {
		while (true) {
			Socket c;
			try {
				//blocking method to wait for client
				c = this.servSock.accept();
				//if there is a client then we will instantiate player2
				Controller.getSingleton().setPlayer2();
				Controller.getSingleton().setTextOfInstruction("Connected to player! Connect to audio->");
				ClientThread clientThread = new ClientThread(c);
				this.gs.addToClientList(clientThread);
				clientThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
