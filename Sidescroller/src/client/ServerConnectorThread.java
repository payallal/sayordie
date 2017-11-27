package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controller.Controller;
import multiplayer.ReadMovementThread;

public class ServerConnectorThread extends Thread {
	private String ip;
	private int port;
	private GameClient gc;
	
	public ServerConnectorThread(String ip, int port, GameClient gc) {
		this.ip = ip;
		this.port = port;
		this.gc = gc;
	}
	
	@Override
	public void run() {
		boolean scanning = true;
		while(scanning) {
			try {
				Socket connection = new Socket(this.ip, this.port);
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				PrintWriter pw = new PrintWriter(connection.getOutputStream());
				this.gc.setConnection(connection);
				this.gc.setBufferedReader(br);
				this.gc.setPrintWriter(pw);
				
				//once client has made a connection with server, we instantiate player 2
				Controller.getSingleton().setPlayer2();
				
				//we display connected
				
				Controller.getSingleton().setTextOfInstruction("Connected!");
				Thread.sleep(1000);
				Controller.getSingleton().getGameWindow().getRecordButton().setEnabled(true);
				Controller.getSingleton().setTextOfInstruction("Click to connect ->");
				
				//run the readkeythread to listen for updates from server
				ReadMovementThread readKeyThread = new ReadMovementThread(br);
				readKeyThread.start();
				scanning = false;
			} catch (IOException e) {
				Controller.getSingleton().setTextOfInstruction("Failed to connect.");
				try {
					Thread.sleep(1000);
					Controller.getSingleton().setTextOfInstruction("Trying again...");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Controller.getSingleton().setTextOfInstruction("Connecting to Server...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
