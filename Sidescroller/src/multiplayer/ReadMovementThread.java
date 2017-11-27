
package multiplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import controller.Controller;

/**
 * Handles interpretation of character movement data sent accross clients.
 * @author Roger
 *
 */
public class ReadMovementThread extends Thread {
	
	/**
	 * Stores instance of buffered reader for text input stream from JSON.
	 */
	private BufferedReader incomingJSON;
	/**
	 * Stores array of boolean values associated with charcter movements.
	 */
	private boolean barray[];
	/**
	 * Stores instance of game controller.
	 * @see controller.Controller
	 */
	private Controller controller;
	/**
	 * Constructor that initalizes fields.
	 * @param br instance of buffered reader.
	 */
	public ReadMovementThread (BufferedReader br) {
		this.incomingJSON = br;
		this.barray = new boolean[6];
		this.resetBooleans();
		this.controller = Controller.getSingleton();
	}
	/**
	 * Sets all values in the boolean array to false.
	 */
	public void resetBooleans() {
		for (int i = 0; i<this.barray.length; i++) {
			this.barray[i] = false;
		}
	}

	@Override
	/**
	 * Starts thread to receive an interpret JSON requests and pass instructions to controller.
	 */
	public void run()
	{
		String json = "";
		try {
			while(true)
			{
				if ((json = this.incomingJSON.readLine()) != null) {
					System.out.println("Received the following JSON: " + json);
					// parseJSON, and sets the booleans
					parseJSONToSetBool(json);
					controller.updatePlayer2Movement(this.barray);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connection was closed!");
	}
	/**
	 * Parser to translate JSON requests to instructions.
	 * @param json JSON instructions received.
	 */
	public void parseJSONToSetBool(String json) {
		        
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject keyObject = reader.readObject();
		reader.close();
		
		//now grab the boolean values we need
		this.barray[0] = keyObject.getBoolean("start");
		this.barray[1] = keyObject.getBoolean("right");
		this.barray[2] = keyObject.getBoolean("jump");
		this.barray[3] = keyObject.getBoolean("stop");
		
	}
}
