package view;
import javax.swing.JFrame;

import multiplayer.GameServer;

public class GameWindow extends JFrame{
	
	private GamePanel gp;
	private GameServer gs;
	
	public GameWindow() {
		this.gp = new GamePanel();
		add(gp);
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		this.gs = new GameServer(1664);
		gs.acceptClientLoop();
	} 

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
	}
}