/* Gravity Object Class
 * Extends GImage
 * Constructor Parameters: image, radius, x velocity, y velocity, gravity multiplier, x universe, y universe, and game instance.
 * Instrumental class.
 */

package physics;

import player.Projectile;
import acm.graphics.GImage;
import physics.Vector;
import player.Player;
import game.SpaceGame;

public class GravityObject extends GImage
{
	private Vector myVector; // gravity objects move too.
	private double myMass;
	private double gravityMass;
	private double myMultiplier; // Multiplier on strength of gravitational force. Black holes have a multiplier of 100.
	private double xUniverse; // Coordinates in entire map
	private double yUniverse;

	public GravityObject(String image, double radius, double xVel, double yVel, double multiplier, double xu, double yu, SpaceGame game)
	{
		super(image, xu - 500, xu - 500);
		setXUniverse(xu);
		setYUniverse(yu);

		myVector = new Vector(xVel, yVel); // set GravityObject's vector.

		myMass = Math.pow(radius, 2); // Mass is proportional to area
		gravityMass = radius * (radius/2);
		
		this.setSize(radius * 2, radius * 2);

		myMultiplier = multiplier;
	}
	
	public double gravityMass()
	{
		return gravityMass;
	}

	public double getMass()
	{
		return myMass;
	}

	public double getGravityScalar(Player p) // Force of gravity
	{
		double d = Math.pow((this.getXUniverse() + this.getWidth() / 2 - p.getXUniverse()), 2) +
				Math.pow((this.getYUniverse() + this.getHeight() / 2 - p.getYUniverse()), 2);
		return (myMultiplier * this.gravityMass() / d) / 2;
	}
	
	public double getGravityScalar(Projectile p) // Force of gravity
	{
		double d = Math.sqrt(Math.pow((this.getXUniverse() + this.getWidth() / 2 - p.getX()), 2) + Math.pow((this.getYUniverse() + this.getHeight() / 2 - p.getY()), 2));
		return (myMultiplier * this.getMass() / Math.pow(d, 2)) / 2;
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
		return Math.sqrt(Math.pow(p.getXUniverse() - this.getXUniverse(), 2) + Math.pow(p.getYUniverse() - this.getYUniverse(), 2));
	}
	
	public double getDistance(Projectile p) // The distance from a player 
	{
		return Math.sqrt(Math.pow(p.getX() - this.getXUniverse(), 2) + Math.pow(p.getY() - this.getYUniverse(), 2));
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