package model;

/**
 * Represents X and Y-coordinates of the associated object - sprites or words.
 * @author Roger
 *
 */
public class Coordinate {
	/**
	 * Stores X-coordinate.
	 */
	private int x;
	/**
	 * Stores Y-coordinate.
	 */
	private int y;
	
	/**
	 * Constructor which initializes x and y-coordinate values.
	 * @param x the initial X-coordinate value.
	 * @param y the initial Y-coordinate value.
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter method for the X-coordinate value.
	 * @return the X-coordinate value.
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Getter method for the U-coordinate value.
	 * @return the Y-coordinate value.
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * Setter method for X-coordinate value.
	 * @param x new X-coordinate value.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Setter method for Y-coordinate value.
	 * @param x new Y-coordinate value.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
