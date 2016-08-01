package physics;

import acm.graphics.GImage;
import physics.Vector;
import player.Player;

public class GravityObject extends GImage
{
	private Vector myVector; // gravity objects move too.
	private double myMass;
	
	public GravityObject(String image, double startX, double startY, double radius, double xVel, double yVel)
	{
		super(image, startX, startY);
		
		myVector = new Vector(xVel, yVel); // set GravityObject's vector.
		
		myMass = radius * radius;
		
		this.setSize(radius * 2, radius * 2);
	}
	
	public double getMass()
	{ return myMass; }
	
	public double getGravityScalar(Player p)
	{
		double d = Math.sqrt(Math.pow((this.getX() - p.getX()), 2) + Math.pow((this.getY() - p.getY()), 2));
		return this.getMass() / Math.pow(d, 2);
	}
	
	public Vector getVector()
	{ return myVector; }
	
	public void addVectorToMyVector(Vector v)
	{
		myVector.add(v);
	}
	
	public void move()
	{ this.move(myVector.getXComponent(), myVector.getYComponent()); }
	
	public static void handleGravityObjectInteractions(GravityObject[] gravityObjects)
	{
		GravityObject[] temp = new GravityObject[gravityObjects.length];
		for (int i = 0; i < gravityObjects.length; i++)
		{
			temp[i] = gravityObjects[i];
			for (int j = 0; j < gravityObjects.length; j++)
			{
				if (i != j)
				{
					temp[i].addVectorToMyVector(gravityObjects[j].getVector());
				} // if they aren't the same thing.
			}
		} // O(n ^ 2) algorithm.
		
		System.arraycopy(temp, 0, gravityObjects, 0, gravityObjects.length);
	} // to monitor gravity objects.
	
	public double getDistance(Player p)
	{
		return Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2));
	}
	
}
