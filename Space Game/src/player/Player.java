/* Player Class.
 * Last edited 8 / 03 / 16.
 * Extremely Important Class.
 */
package player;

import acm.graphics.*;
import ai.Enemy;
import physics.Vector;
import physics.GravityObject;
import game.Score;
import game.SpaceGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player extends GPolygon
{
	private Vector myVector;

	private boolean left;
	private boolean right;
	private List<Projectile> myProjectiles;
	public int missileIndex;
	private double health;
	private double angle;
	private double myHeight;
	private double xUniverse;
	private double yUniverse;
	private Score myScore;
	private SpaceGame myGame;

	public Player(double startX, double startY, double xVel, double yVel, SpaceGame game, Score score)
	{
		super(startX, startY); // create player GImage.
		setXUniverse(startX + game.getXUniverse());
		setYUniverse(startY + game.getYUniverse());

		// Draw the polygon here.
		this.addVertex(0, -3);
		this.addVertex(2, 1);
		this.addVertex(1, 2);
		this.addVertex(-1,  2);
		this.addVertex(-2,  1);
		this.scale(5);
		
		this.recenter();
		
		myHeight = -3 * 5;
		
		// Done drawing the polygon.
		
		myProjectiles = new ArrayList<Projectile>();
		
		this.setColor(Color.GREEN);
		this.setFillColor(Color.RED);
		this.setFilled(true);
		this.setVisible(true);
		
		myVector = new Vector(xVel, yVel); // set initial player vector.

		missileIndex = 0;
		
		left = false;
		right = false;
		health = 1000.;
		
		myGame = game;
	}

	public Vector getVector()
	{
		return myVector;
	}

	public void rotateShip(double degrees)
	{
		this.rotate(degrees);
	}
	
	public double getAngle()
	{
		return angle;
	}

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
	
	public void shoot(Score score, Enemy[] enemies)
	{
		myProjectiles.add(new Projectile(this, score));
	}

	public void monitor()
	{
		if (left)
		{
			this.rotate(2);
			angle -= 2;
		}
		else if (right)
		{
			this.rotate(-2);
			angle += 2;
		}
	} // monitor user - I know the angles look backwards, but the rotate function is different.

	public List<Projectile> getProjectiles()
	{
		return myProjectiles;
	}
	
	public void removeProjectiles(int index)
	{
		myProjectiles.remove(index);
	}
	
	public void move(SpaceGame s)
	{ 
		//super.move(myVector.getXComponent(), myVector.getYComponent());
		s.setXUniverse(s.getXUniverse() + myVector.getXComponent());
		s.setYUniverse(s.getYUniverse() + myVector.getYComponent());
		setXUniverse(getXUniverse() + myVector.getXComponent());
		setYUniverse(getYUniverse() + myVector.getYComponent());
		
		for (Projectile p : myProjectiles)
		{
			if (p != null)
			{
				p.move(p.getVector().getXComponent(), p.getVector().getYComponent());
			}
		}
	}

	public void increaseSpeed()
	{
		if (myVector.getXComponent() < 15 && myVector.getYComponent() < 15)
		{
			myVector.setXComponent(myVector.getXComponent() + Math.sin(Math.toRadians(angle)));
			myVector.setYComponent(myVector.getYComponent() - Math.cos(Math.toRadians(angle)));
		} // Player Speed Control.
	}

	public void decreaseSpeed()
	{
		if (myVector.getXComponent() > -15 && myVector.getYComponent() > -15)
		{
			myVector.setXComponent(myVector.getXComponent() - Math.sin(Math.toRadians(angle)));
			myVector.setYComponent(myVector.getYComponent() + Math.cos(Math.toRadians(angle)));
		} // players can only decrease speed - they can't go backwards.
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
	
	public double getHeight()
	{
		return myHeight;
	}

	public double getXUniverse()
	{
		return xUniverse;
	}

	public double getYUniverse() 
	{
		return yUniverse;
	}

	private void setXUniverse(double x) 
	{
		this.xUniverse = x;
	}

	public void setYUniverse(double y) 
	{
		this.yUniverse = y;
	}
	
	public void reduceHealth(double damage)
	{
		health -= damage;
		if (health <= 0)
		{
			myGame.gameOver();
		}
	}
}


