package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Dialog.ModalityType;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * /**
 * JPanel which overlays the game screen with the start menu dialog box and a glass pane.
 * @author Alexia, Valerie
 */
public class UIPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Stores the alpha value to determine the opacity of the colour of the glass pane between 0 to 255.
	 */
	private static final int ALPHA = 175; 
	/**
	 * Stores the RGB values for background color and alpha value of the glass pane. In this case the color is black. 
	 */
    private static final Color GP_BG = new Color(0, 0, 0, ALPHA);
    /**
     * Stores an instance of the game window.
     * @see view.GameWindow
     */
    private GameWindow gw;
    /**
     * Stores an instance of the start menu JPanel.
     * @see view.Menu
     */
    private Menu deDialogPanel; 
    
    /**
     * Constructor for the start menu panel to overlay the game screen.
     * Sets the glass pane, adds the start menu and sets the modality.
     * @param gw an instance the game window on which this start JPanel will be overlayed.
     */
	public UIPanel(GameWindow gw, String labelName, Color color, String buttonText1, String buttonText2, ActionListener aL1, ActionListener aL2) {
		this.gw = gw;
		this.deDialogPanel = new Menu(labelName, color, buttonText1, buttonText2, aL1, aL2);
		this.setOpaque(false); 
        this.setBackground(GP_BG);
        gw.setGlassPane(this);  // set the glass pane
        this.setVisible(true);
        this.add(deDialogPanel);
        // create a modal JDialog
        JDialog dialog = new JDialog(this.gw, "", ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(deDialogPanel);  // add its JPanel to it
        dialog.setUndecorated(true); // give it no borders
        dialog.pack(); // size it
        dialog.setLocationRelativeTo(this.gw); // ** Center it over the JFrame **
        dialog.setVisible(true);  // display it, pausing the GUI below it
        // at this point the dialog is no longer visible, so get rid of glass pane
        this.setVisible(false); 

	}
	
	/**
	 * Paint component method which paints the screen to look translucent black.
	 */
	protected void paintComponent(Graphics g) {
        // magic to make it dark without side-effects
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
