package ai;

import acm.graphics.GPolygon;
import java.awt.Color;
import java.util.ArrayList;

import java.util.List;
import player.Laser;
import player.Player;
import player.Projectile;
import physics.Vector;

public class Enemy extends GPolygon
{

	private double distance;
	private Player target;
	private List<Player> myPlayers;
	private double angle;
//	private int type;
	private Vector myVector;
//	private Projectile missile;
//	private Laser laser;
//	private int laserExists;
	private double health;
	private int value;
	private double xUniverse;
	private double yUniverse;

	public Enemy(double xUniverse, double yUniverse, double xVel, double yVel, Player thisPlayer, Player[] otherPlayers)
	{
		super(xUniverse - 500, yUniverse - 500);

		this.addEdge(0, -10);
		this.addEdge(-8, 5);
		this.addEdge(8, 5);
		this.setFillColor(Color.YELLOW);
		this.setColor(Color.YELLOW);
		this.setFilled(true);
		myPlayers = new ArrayList<Player>();
		myVector = new Vector(xVel, yVel);
		angle = 0;
		value = (int) (Math.random() * 51) + 75;
		
		for (int i = 0; i < otherPlayers.length; i++)
		{
			if (otherPlayers[i] != null)
			{
				myPlayers.add(otherPlayers[i]);
			}
		}
		myPlayers.add(thisPlayer);
		target = myPlayers.get(0);
		distance = Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2));
//		if ((int) (Math.random() * 4) == 0)
//		{
//			type = 0;
//			type = 1;
//		}
//		else
//		{
//			type = 1;
//		}
//		laserExists = 0;
		health = 100.;
	}

	public void action()
	{
		findTarget();
		followTarget();
		if (distance < 3000)
		{
			//fire();
		}
		else		
		{
			move();
		}
	}

	private void findTarget()
	{
		for (int i = 0; i < myPlayers.size(); i++)
		{
			if (Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2)) < distance)
			{
				target = myPlayers.get(i);
				distance = Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2));
			}
		}	
	}

	private void followTarget()
	{
		angle = Math.atan2(Math.abs(target.getYUniverse() - this.getY()), Math.abs(target.getXUniverse() - this.getY()));
		if (angle < 0)
		{
			angle += 180;
		}
		setAngle(angle);
	}

	private void setAngle(double newAngle)
	{
		double rotateAngle = newAngle - angle;
		rotate(rotateAngle);
		angle = newAngle;
		while (angle >= 360)
		{
			angle -= 360;
		}
		while (angle < 0)
		{
			angle += 360;
		}
	}
	
	private void move()
	{
		myVector.setXComponent(myVector.getXComponent() + Math.sin(Math.toRadians(angle)));
		myVector.setYComponent(myVector.getYComponent() + Math.cos(Math.toRadians(angle)));
		setXUniverse(myVector.getXComponent());
		setYUniverse(myVector.getYComponent());
		//
	}
	
	/*
	private void fire()
	{
		if (type == 0)
		{
//			missile = new Projectile();
		}
		else
		{
			if (laserExists == 0)
			{
				laser = new Laser(getX(), getY(), angle);
				laserExists = 4;
			}
			else
			{
				laser.moveLaser();
				laserExists--;
			}
		}
	}
	*/
	
	public double getXUniverse() 
	{
		return xUniverse;
	}
	
	public void setXUniverse(double x)
	{
		xUniverse = x;
	}
	
	public double getYUniverse()
	{
		return yUniverse;
	}
	
	public void setYUniverse(double y)
	{
		yUniverse = y;
	}
	
}