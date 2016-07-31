package player;

import acm.graphics.GPolygon;
import physics.Vector;
import java.awt.Color;

public class Player extends GPolygon
{
	private Vector myVector;
	
	private boolean left;
	private boolean right;
	private Projectile[] myProjectiles;
	
	public Player(double startX, double startY, double xVel, double yVel)
	{
		super(startX, startY); // create player GImage.
		
		myVector = new Vector(xVel, yVel); // set initial player vector.

		this.addPolarEdge(10, 0);
		this.addPolarEdge(7, 60);
		this.addPolarEdge(10, 120);
		this.addPolarEdge(10, 240);
		this.addPolarEdge(7, 300);
		
		this.setFillColor(Color.WHITE);
		this.setColor(Color.WHITE);
		this.setFilled(true);
		
		left = false;
		right = false;
	}
	
	public Vector getVector()
	{ return myVector; }
	
	public void rotateShip(double degrees)
	{ this.rotate(degrees); }
	
	public void goLeft()
	{
		left = true;
	}
	
	public void goRight()
	{
		right = true;
	}
	
	public void stopMovingLeft()
	{
		left = false;
	}
	
	public void stopMovingRight()
	{
		right = false;
	}
	
	public void monitor()
	{
		if (left)
		{
			this.rotate(5);
		}
		else if (right)
		{
			this.rotate(-5);
		}
	}
}
