package player;

import acm.graphics.GPolygon;
import acm.graphics.GPoint;
import physics.Vector;

public class Player extends GPolygon
{
	private Vector myVector;
	
	public Player(double startX, double startY, double xVel, double yVel)
	{
		super(startX, startY); // create player GImage.
		
		myVector = new Vector(xVel, yVel); // set initial player vector.

		this.addPolarEdge(10, 0);
		this.addPolarEdge(7, 60);
		this.addPolarEdge(10, 120);
		//this.addPolarEdge(10, 180);
		this.addPolarEdge(10, 240);
		this.addPolarEdge(7, 300);
		//this.addPolarEdge(10, 360);
		
		
		
		
	}
	
	public Vector getVector()
	{ return myVector; }
	
	public void rotateShip(double degrees)
	{ this.rotate(degrees); }
}
