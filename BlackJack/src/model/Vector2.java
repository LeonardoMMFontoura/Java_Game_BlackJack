//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package model;

public class Vector2 
{
	private int x;
	private int y;
	
	/**
	 * Defines a Bi-dimensional Vector
	 * @param x
	 * @param y
	 */
	Vector2(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set new values for a Vector2
	 * @param x
	 * @param y
	 */
	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	/**
	 * Set new values for a Vector2
	 * @param vector
	 */
	public void set(Vector2 vector)
	{
		this.x = vector.x;
		this.y = vector.y;
	}
	
	/**
	 * Subtracts one vector by other
	 * @param vector
	 */
	public void MinusEqual(Vector2 vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	/**
	 * Adds one vector to other
	 * @param temp
	 */
	public void PlusEqual(Vector2 vector)
	{
		this.x += vector.x;
		this.y += vector.y;
	}
	/**
	 * Public getter for X coordinate
	 * @return
	 */
	public int getX() 
	{
		return this.x;
	}
	/**
	 * Public getter for Y coordinate
	 * @return
	 */
	public int getY()
	{
		return this.y;
	}

}
