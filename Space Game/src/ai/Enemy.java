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
	private int type;
	private Vector myVector;
	private Projectile missile;
	private Laser laser;

	public Enemy(double startX, double startY, double xVel, double yVel, Player thisPlayer, Player[] otherPlayers)
	{
		super(startX, startY);

		this.addEdge(0, -10);
		this.addEdge(-8, 5);
		this.addEdge(8, 5);
		this.setFillColor(Color.YELLOW);
		this.setColor(Color.YELLOW);
		this.setFilled(true);
		
		myPlayers = new ArrayList<Player>();

		myVector = new Vector(xVel, yVel);

		angle = 0;
		
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
		if ((int) (Math.random() * 4) == 0)
		{
			type = 0;
		}
		else
		{
			type = 1;
		}
	}

	public void action()
	{
		findTarget();
		followTarget();
		if (distance < 300)
		{
			fire();
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
		move(myVector.getXComponent(), myVector.getYComponent());
	}
	
	private void fire()
	{
		if (type == 0)
		{
//			missile = new Projectile();
		}
		else
		{
//			laser = new Laser();
		}
	}
	
}



