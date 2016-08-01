package player;

import acm.graphics.GImage;
import physics.Vector;
import physics.GravityObject;
import game.SpaceGame;

public class Player extends GImage
{
	private Vector myVector;
	
	private boolean left;
	private boolean right;
	private Projectile[] myProjectiles;
	
	private double angle;
	private double speed;
	private double xUniverse;
	private double yUniverse;
	
	public Player(String s, double startX, double startY, double xVel, double yVel)
	{
		super(s, startX, startY); // create player GImage.
		setXUniverse(startX);
		setYUniverse(startY);
		
		myVector = new Vector(xVel, yVel); // set initial player vector.
		
		this.setSize(20, 50); // set size of ship.
		
		left = false;
		right = false;
		speed = 0;
	}

	public Vector getVector()
	{ return myVector; }
	
	public void rotateShip(double degrees)
	{ this.movePolar(0, degrees); }
	
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
			this.movePolar(0, 5);
			angle -= 5;
		}
		else if (right)
		{
			this.movePolar(0, -5);
			angle += 5;
		}
	} // monitor user - I know the angles look backwards, but the rotate function is different.
	
	public void move(SpaceGame s)
	{ 
		s.setLocation((int)myVector.getXComponent(), (int)myVector.getYComponent());
		this.setXUniverse(myVector.getXComponent());
		this.setYUniverse(myVector.getYComponent()); 
	}
	
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
	
	public double getXUniverse()
	{
		return xUniverse;
	}

	public double getYUniverse() {
		return yUniverse;
	}
	
	private void setXUniverse(double x) {
		this.xUniverse = x;
	}

	public void setYUniverse(double y) {
		this.yUniverse = y;
	}
}
