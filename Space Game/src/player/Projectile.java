/* Projectile Class
 * Written for Player.java.
 * Constructor parameters: Player p.
 */
package player;

import acm.graphics.GPolygon;
import physics.Vector;
import java.awt.Color;
import physics.GravityObject; // import Gravity object class.

public class Projectile extends GPolygon
{
	private final double MISSILESPEED = 1.5;
	
	private Vector myVector;

	public Projectile(Player p)
	{
		super(p.getX(), p.getY()); // create a Polygon.
		
		this.addVertex(0, -5);
		this.addVertex(3, 5);
		this.addVertex(-3, 5);
		
		this.recenter();
		
		this.rotate(p.getAngle());
		
		this.setFillColor(Color.RED);
		this.setColor(Color.YELLOW);
		this.setFilled(true);

		myVector = new Vector(p.getHeight() * Math.cos(p.getAngle()), p.getHeight() * Math.sin(p.getAngle()));
		myVector.multiplyByScalar(MISSILESPEED); // player's vector + missle's speed ( a scalar ).
	}

	public Vector getVector()
	{
		return myVector;
	}
	
	public void adjustForGravity(GravityObject[] gObjects)
	{
		for (GravityObject g : gObjects)
		{
			if (g != null)
			{
				double scalar = g.getGravityScalar(this);
				double distance = g.getDistance(this);
				Vector temp = new Vector(g.getXUniverse() - this.getX(), g.getYUniverse() - this.getY());
				temp.multiplyByScalar(scalar / distance);
				myVector.add(temp);
			}
		}
	}
	
	public void move()
	{
		this.move(myVector.getXComponent(), myVector.getYComponent());
	}
}
