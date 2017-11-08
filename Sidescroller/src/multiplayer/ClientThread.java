package multiplayer;
import java.net.*;

import controller.Controller;

import java.io.*;


public class ClientThread extends Thread {
	
	private Socket client;
	private ReadKeyThread keyRead;
	private GameServer server;
	private PrintWriter pw;
	private BufferedReader br;
	
	public ClientThread (Socket c, GameServer gs) {
		this.server = gs;
		this.client = c;
		try {
			this.pw = new PrintWriter(this.client.getOutputStream());	
			this.br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.keyRead = new ReadKeyThread(this, this.br);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	public void run()
	{
		this.keyRead.start();
	}
	
	//method that will 

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
