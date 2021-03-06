/* Projectile Class
 * Written for Player.java.
 * Constructor parameters: Player p.
 */
package player;

import acm.graphics.GMath;
import acm.graphics.GPolygon;
import ai.Enemy;
import game.Score;
import game.SpaceGame;
import physics.Vector;
import java.awt.Color;
import physics.GravityObject; // import Gravity object class.

public class Projectile extends GPolygon
{
	private final double MISSILESPEED = .5;

	//Defining the private instance variables
	private Vector myVector;
//	private Enemy[] myEnemies;
//	private double myDamage;
//	private Player myPlayer;
	private double xUniverse;
	private double yUniverse;

	public Projectile(Player player, Enemy[] enemies, SpaceGame game)
	{
		super(player.getXUniverse() - game.getXUniverse(), player.getYUniverse() - game.getYUniverse()); // create a Polygon.
		this.setXUniverse(player.getXUniverse());
		this.setYUniverse(player.getYUniverse());
		
		this.addPolarEdge(5, 60);
		this.addPolarEdge(5, 180);
		this.addPolarEdge(5, 300);

		this.recenter();

		this.rotate(player.getAngle());

		this.setFillColor(Color.RED);
		this.setColor(Color.YELLOW);
		this.setFilled(true);
		this.sendToFront();

		myVector = new Vector(player.getHeight() * GMath.cosDegrees(player.getAngle() + 90), player.getHeight() * GMath.sinDegrees(player.getAngle() + 90));
		myVector.multiplyByScalar(MISSILESPEED); // player's vector + missle's speed ( a scalar ).

//		myDamage = Math.random() * 11 + 15;
//		myPlayer = player;
//		myEnemies = enemies;
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
				Vector temp = new Vector(g.getXUniverse() - this.getXUniverse(), g.getYUniverse() - this.getYUniverse());
				temp.multiplyByScalar(scalar / distance);
				myVector.add(temp);
			}
		}
	}

	public void move()
	{
		setXUniverse(getXUniverse() + myVector.getXComponent());
		setYUniverse(getYUniverse() + myVector.getYComponent());
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

//
