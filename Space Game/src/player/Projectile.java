/* Projectile Class
 * Written for Player.java.
 * Constructor parameters: Player p.
 */
package player;

import acm.graphics.GMath;
import acm.graphics.GPolygon;
import ai.Enemy;
import game.Score;
import physics.Vector;
import java.awt.Color;
import physics.GravityObject; // import Gravity object class.

public class Projectile extends GPolygon
{
	private final double MISSILESPEED = .5;

	//Defining the private instance variables
	private Vector myVector;
	private Enemy[] myEnemies;
	private double myDamage;
	private Player myPlayer;
	private double xUniverse;
	private double yUniverse;

	public Projectile(Player player, Enemy[] enemies)
	{
		super(player.getX(), player.getY()); // create a Polygon.

		this.addPolarEdge(5, 60);
		this.addPolarEdge(5, 180);
		this.addPolarEdge(5, 300);

		this.recenter();

		this.rotate(player.getAngle());

		this.setFillColor(Color.RED);
		this.setColor(Color.YELLOW);
		this.setFilled(true);

		myVector = new Vector(player.getHeight() * GMath.cosDegrees(player.getAngle() + 90), player.getHeight() * GMath.sinDegrees(player.getAngle() + 90));
		myVector.multiplyByScalar(MISSILESPEED); // player's vector + missle's speed ( a scalar ).

		myDamage = Math.random() * 11 + 15;
		myPlayer = player;
		myEnemies = enemies;
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

	public void move(Score score)
	{
		this.move(myVector.getXComponent(), myVector.getYComponent());
		if (myPlayer.contains(getLocation()))
		{
			myPlayer.reduceHealth(myDamage);
		}
		
		for (int i = 0; i < myEnemies.length; i++)
		{
			if (myEnemies[i].contains(getXUniverse(), getYUniverse()))
			{
				myEnemies[i].reduceHealth(myDamage, score);
			}
		}
	}

	public double getXUniverse() {
		return xUniverse;
	}

	public void setXUniverse(double xUniverse) {
		this.xUniverse = xUniverse;
	}

	public double getYUniverse() {
		return yUniverse;
	}

	public void setYUniverse(double yUniverse) {
		this.yUniverse = yUniverse;
	}
}


