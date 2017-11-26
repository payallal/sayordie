package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.Controller;
import multiplayer.GameClient;

public class ClientGameWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private GamePanel gp;
	private JLabel recordLabel;
	private StartMenu menu;
	private StartPanel sp;
	private final int width = 1280;
	private final int height = 800;
	
	public ClientGameWindow() {
		this.setLayout(new BorderLayout());
		JLayeredPane layeredPane = new JLayeredPane();
	    Dimension panelDimension = new Dimension(this.width,this.height);
	    Dimension labelDimension = new Dimension(150,150);

	    this.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setPreferredSize(panelDimension);

		//set up game panel
		this.gp = new GamePanel(this.width, this.height);
		layeredPane.add(this.gp, JLayeredPane.DEFAULT_LAYER);
	    this.gp.setSize(layeredPane.getPreferredSize());
	    this.gp.setLocation(0, 0);
	    
	    //set up record label
		Icon recordGrey = new ImageIcon("img/ui/recordGrey.png");
		this.recordLabel = new JLabel(recordGrey);
		layeredPane.add(this.recordLabel, new Integer(2));
		this.recordLabel.setSize(labelDimension);
		this.recordLabel.setLocation(this.width-150, 0);
		Controller.getSingleton().setRecordLabel(this.recordLabel);
		Controller.getSingleton().setRecordLabelMouseListener(this.recordLabel);

		this.pack();
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.sp = new StartPanel(this);
		Controller.getSingleton().setGameWindow(this);
	} 

	public static void main(String[] args) {
		ClientGameWindow gw = new ClientGameWindow();
		
		//we only do this if user chooses multiplayer
		if (Controller.getSingleton().getMultiplayer()) {
			GameClient c;
			try {
				//we should only be here if multiplier is set to true
				c = new GameClient("127.0.0.1", 1664);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}	
			Controller.getSingleton().setClient(c);
		}
	}
}