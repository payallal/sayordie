package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;

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
	        add(new JButton(new DialogDisposer("Single Player")));
	        add(new JButton(new DialogDisposer("Multi Player")));
	    }

	    // simple action -- all it does is to make the dialog no longer visible
	    private class DialogDisposer extends AbstractAction {
	        public DialogDisposer(String name) {
	            super(name);
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Component comp = (Component) e.getSource();
	            Window win = SwingUtilities.getWindowAncestor(comp);
	            win.dispose();  // here -- dispose of the JDialog
	        }

			
	    }
}
