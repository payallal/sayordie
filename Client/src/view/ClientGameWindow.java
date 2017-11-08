package view;
import java.io.IOException;

import javax.swing.JFrame;

import controller.Controller;
import multiplayer.GameClient;

public class ClientGameWindow extends JFrame{
	
	private GamePanel gp;
	
	public ClientGameWindow() {
		this.gp = new GamePanel();
		add(gp);
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} 

	public static void main(String[] args) {
		ClientGameWindow gw = new ClientGameWindow();
		GameClient c;
		try {
			c = new GameClient("127.0.0.1", 1664);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		Controller.getSingleton().setClient(c);
	}
}