package player;

import acm.graphics.GPolygon;
import physics.Vector;
import java.awt.Color;
import physics.GravityObject;
import java.applet.*;
import java.net.*;

public class Player extends GPolygon
{
	private Vector myVector;
	
	private boolean left;
	private boolean right;
	private Projectile[] myProjectiles;
	
	private double angle;
	private double speed;
	
	public Player(double startX, double startY, double xVel, double yVel)
	{
		super(startX, startY); // create player GImage.
		
		myVector = new Vector(xVel, yVel); // set initial player vector.

		this.addPolarEdge(10, 0);
		this.addPolarEdge(7, 60);
		this.addPolarEdge(10, 120);
		this.addPolarEdge(10, 240);
		this.addPolarEdge(7, 300);
		
		this.setFillColor(Color.GRAY);
		this.setColor(Color.GRAY);
		this.setFilled(true);
		
		left = false;
		right = false;
		speed = 0;
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
			angle -= 5;
		}
		else if (right)
		{
			this.rotate(-5);
			angle += 5;
		}
	} // monitor user - I know the angles look backwards, but the rotate function is different.
	
	public void move()
	{ this.move(myVector.getXComponent(), myVector.getYComponent()); }
	
	public void increaseSpeed()
	{
		myVector.setXComponent(myVector.getXComponent() + Math.sin(Math.toRadians(angle)));
		myVector.setYComponent(myVector.getYComponent() - Math.cos(Math.toRadians(angle)));	
	}
	
	public void adjustForGravity(GravityObject[] gObjects)
	{
		for (GravityObject g : gObjects)
		{
			if (g != null)
			{
				double scalar = g.getGravityScalar(this);
				double distance = g.getDistance(this);
				Vector temp = new Vector(g.getX() - this.getX(), g.getY() - this.getY());
				temp.multiplyByScalar(scalar / distance);
				myVector.add(temp);
			}
		}
	}
}
