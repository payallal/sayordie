package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class StartMenu extends JPanel{
	
	 private static final Color BG = new Color(10, 50, 200);

	    public StartMenu() {
	        JLabel pausedLabel = new JLabel("Start Game");
	        pausedLabel.setForeground(Color.WHITE);
	        JPanel pausedPanel = new JPanel();
	        pausedPanel.setOpaque(false);
	        pausedPanel.add(pausedLabel);

	        setBackground(BG);
	        int eb = 15;
	        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
	        setLayout(new GridLayout(0, 1, 10, 10));
	        add(pausedPanel);
	        add(new JButton(new FooAction("Single Player")));
	        add(new JButton(new FooAction("Multi Player")));
	    }

	    // simple action -- all it does is to make the dialog no longer visible
	    private class FooAction extends AbstractAction {
	        public FooAction(String name) {
	            super(name);
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Component comp = (Component) e.getSource();
	            Window win = SwingUtilities.getWindowAncestor(comp);
	            win.dispose();  // here -- dispose of the JDialog
	        }

			
	    }
	/*
	private JButton singlePlayer;
	private JButton multiPlayer;
	private ImageIcon single;
	private ImageIcon multi;
	private SingleButtonListener singleListener;
	
	public StartMenu() {
		this.setLayout(new GridLayout(2,1));
		singlePlayer = new JButton();
		multiPlayer = new JButton();
		single = new ImageIcon("img/buttons/single.png");
		singlePlayer.setIcon(single);
		multi = new ImageIcon("img/buttons/multi.png");
		multiPlayer.setIcon(multi);
		
		this.add(singlePlayer);
		this.add(multiPlayer);*/
		
		/*this.singleListener = new SingleButtonListener();
		this.singlePlayer.addActionListener(singleListener);
	
	
		
	}*/


	
}
