package multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.Controller;
/**
 * Handles connection between client thread and controller.
 * @author Roger
 *
 */
public class ClientConnectorThread extends Thread {
	/**
	 * Stores the server socket to be used.
	 */
	private ServerSocket servSock;
	/**
	 * 
	 * @see multiplayer.Gameserver
	 */
	private GameServer gs;
	/**
	 * Constructor to initialize fields.
	 * @param gs instance of game server.
	 * @param ss instance of socket server.
	 */
	public ClientConnectorThread(GameServer gs, ServerSocket ss) {
		this.servSock = ss;
		this.gs = gs;
	}
	
	@Override
	/**
	 * Starts thread that connects client thread to controller.
	 */
	public void run() {
		boolean scanning = true;
		while (scanning) {
			Socket c;
			try {
				//blocking method to wait for client
				c = this.servSock.accept();
				
				//if there is a client then we will instantiate player2
				Controller.getSingleton().setPlayer2();
				
				//Change the instructions
				Controller.getSingleton().setTextOfInstruction("Connected!");
				Thread.sleep(1000);
				Controller.getSingleton().getGameWindow().getRecordButton().setEnabled(true);
				Controller.getSingleton().setTextOfInstruction("Click to connect ->");
				
				//Run client thread to handle reading and writing to client
				ClientThread clientThread = new ClientThread(c);
				this.gs.addToClientList(clientThread);
				clientThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
