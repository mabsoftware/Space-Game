package physics;

import acm.graphics.GImage;
import physics.Vector;
import player.Player;

public class GravityObject extends GImage
{
	private Vector myVector; // gravity objects move too.
	private double myMass;
	private double myMultiplier; // Multiplier on strength of gravitational force. Black holes have a multiplier of 100.
	private double xUniverse; // Coordinates in entire map
	private double yUniverse;

	public GravityObject(String image, double radius, double xVel, double yVel, double multiplier, double xu, double yu)
	{
		super(image, xu-1000, xu-1000);
		setXUniverse(xu);
		setYUniverse(yu);

		myVector = new Vector(xVel, yVel); // set GravityObject's vector.

		myMass = radius * radius; // Mass is proportional to area

		this.setSize(radius * 2, radius * 2);

		myMultiplier = multiplier;
	}

	public double getMass()
	{
		return myMass;
	}

	public double getGravityScalar(Player p) // Force of gravity
	{
		double d = Math.sqrt(Math.pow((this.getX() - p.getX()), 2) + Math.pow((this.getY() - p.getY()), 2));
		return (myMultiplier * this.getMass() / Math.pow(d, 2));
	}

	public Vector getVector()
	{
		return myVector;
	}

	public void addVectorToMyVector(Vector v)
	{
		myVector.add(v);
	}

	public void move()
	{
		this.move(myVector.getXComponent(), myVector.getYComponent());
	}

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

	public double getDistance(Player p) // The distance from a player 
	{
		return Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2));
	}

	public double getYUniverse() {
		return yUniverse;
	}

	public void setYUniverse(double yUniverse) {
		this.yUniverse = yUniverse;
	}

	public double getXUniverse() {
		return xUniverse;
	}

	public void setXUniverse(double xUniverse) {
		this.xUniverse = xUniverse;
	}

}