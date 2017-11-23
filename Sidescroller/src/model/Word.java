package model;

/**
 * Represents text sprites in the game.
 * @author Roger
 *
 */
public class Word extends Sprite{
	
	/**
	 * Stores string to be displayed on game screen.
	 */
	private String text;
	
	/**
	 * Constructor for word class. Sets the coordinates and the the string to be displayed.
	 * @param s new string to be stored in the text field.
	 * @param coord X and Y-coordinates at which the word will be placed initially.
	 */
	public Word(String s, Coordinate coord) {
		super(coord);
		this.text = s;
	}
	
	/**
	 * Getter method to retrieve the string to be displayed.
	 * @return text field with string to be displayed.
	 */
	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}
	/**
	 * Setter method to change the text stored in the text field.
	 * @param s new text to be stored.
	 */
	public void setText(String s) {
		this.text = s;
	}
	
}
