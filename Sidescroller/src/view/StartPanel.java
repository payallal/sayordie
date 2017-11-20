package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dialog.ModalityType;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class StartPanel extends JPanel{

	private static final int ALPHA = 175; // how much see-thru. 0 to 255
    private static final Color GP_BG = new Color(0, 0, 0, ALPHA);
    private GameWindow gw;
    private StartMenu deDialogPanel = new StartMenu();  // jpanel shown in JDialog
    
	public StartPanel(GameWindow gw) {
		this.gw = gw;
		this.setOpaque(false); 
        this.setBackground(GP_BG);
        gw.setGlassPane(this);  // set the glass pane
        this.setVisible(true);
        
        this.add(deDialogPanel);
        // create a *modal* JDialog
        JDialog dialog = new JDialog(this.gw, "", ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(deDialogPanel);  // add its JPanel to it
        dialog.setUndecorated(true); // give it no borders (if desired)
        dialog.pack(); // size it
        dialog.setLocationRelativeTo(this.gw); // ** Center it over the JFrame **
        dialog.setVisible(true);  // display it, pausing the GUI below it

        // at this point the dialog is no longer visible, so get rid of glass pane
        this.setVisible(false); 

	}

	protected void paintComponent(Graphics g) {
        // magic to make it dark without side-effects
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
