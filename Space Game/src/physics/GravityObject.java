package physics;

import acm.graphics.GImage;
import java.awt.Image;
import physics.Vector;
import player.Player;

public class GravityObject extends GImage
{
	private Vector myVector; // gravity objects move too.
	private double myMass;
	
	public GravityObject(Image image, double startX, double startY, double radius, double xVel, double yVel)
	{
		super(image, startX, startY);
		
		myVector.setXComponent(xVel);
		myVector.setYComponent(yVel); // set GravityObject's vector.
		
		myMass = radius * radius;
	}
	
	public double getMass()
	{ return myMass; }
	
	public double getGravityScalar(Player p)
	{
		double d = Math.sqrt(Math.pow((this.getX() - p.getX()), 2) + Math.pow((this.getY() - p.getY()), 2));
		return this.getMass() / Math.pow(d, 2);
	}
	
	public void move()
	{ this.move(myVector.getXComponent(), myVector.getYComponent()); }
}
