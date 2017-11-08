/*
package multiplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import controller.Controller;


public class KeyThread extends Thread {
	private BufferedReader incomingJSON;
	private ClientThread clientThread;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean jumpPressed;
	private boolean leftReleased;
	private boolean rightReleased;
	private boolean jumpReleased;
	private Controller controller;
	
	public KeyThread (ClientThread ct, BufferedReader br) {
		this.incomingJSON = br;
		this.clientThread = ct;
		this.leftPressed = false;
		this.rightPressed = false;
		this.jumpPressed = false;
		this.leftReleased = false;
		this.rightReleased = false;
		this.jumpReleased = false;
		this.controller = Controller.getSingleton();
	}

	@Override
	public void run()
	{
		String json = "";
		try {
			while(true)
			{
				System.out.println(json);
				if ((json = this.incomingJSON.readLine()) != null) {
					System.out.println("I received the following JSON: " + json);
					// parseJSON, and sets the booleans
					parseJSONToSetBool(json);
					controller.updatePlayer2Movement(this.leftPressed, this.rightPressed, this.jumpPressed, this.leftReleased, this.rightReleased, this.jumpReleased);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connection was closed!");
	}
	
	public void parseJSONToSetBool(String json) {
		        
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject keyObject = reader.readObject();
		reader.close();
		
		//now grab the boolean values we need
		this.leftPressed = keyObject.getBoolean("leftPressed");
		this.rightPressed = keyObject.getBoolean("rightPressed");
		this.jumpPressed = keyObject.getBoolean("jumpPressed");
		this.leftReleased = keyObject.getBoolean("leftReleased");
		this.rightReleased = keyObject.getBoolean("rightReleased");
		this.jumpReleased = keyObject.getBoolean("jumpReleased");
		
		//Send a confirmation to the client that message was received
		this.clientThread.sendConfirmation();
	}
}

*/
