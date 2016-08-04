package ai;

import acm.graphics.GPolygon;
import game.Score;
import game.SpaceGame;

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
	private Vector myVector;
	private double health;
	private int value;
	private double xUniverse;
	private double yUniverse;
	private final double SPEED;

	public Enemy(double xu, double yu, double xVel, double yVel, Player thisPlayer, SpaceGame game)
	{
		super(xu - game.getXUniverse(), yu - game.getYUniverse());
		setXUniverse(xu);
		setYUniverse(yu);
		
		target = thisPlayer;
		
		SPEED = 5;

		this.addEdge(0, -10);
		this.addEdge(3, -3);
		this.addEdge(-8, 5);
		this.addEdge(8, 5);
		this.addEdge(-3, -3);
		this.recenter();
		this.scale(3);
		this.setFillColor(Color.YELLOW);
		this.setColor(Color.GREEN);
		this.setFilled(true);
		myPlayers = new ArrayList<Player>();
		myVector = new Vector(xVel, yVel);
		angle = 0;
		value = (int) (Math.random() * 51) + 75;
		
		/*for (int i = 0; i < otherPlayers.length; i++)
		{
			if (otherPlayers[i] != null)
			{
				myPlayers.add(otherPlayers[i]);
			}
		}*/
		myPlayers.add(0, thisPlayer);
		target = thisPlayer;
		distance = Math.sqrt(Math.pow(this.getXUniverse() - target.getXUniverse(), 2) + Math.pow(this.getYUniverse() - target.getYUniverse(), 2));
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
		this.attackPlayer();
		this.move();
	}
	
	private void move()
	{
		myVector.setXComponent(myVector.getXComponent() + Math.sin(Math.toRadians(angle)));
		myVector.setYComponent(myVector.getYComponent() + Math.cos(Math.toRadians(angle)));
		this.setXUniverse(this.getXUniverse() + myVector.getXComponent()/2);
		this.setYUniverse(this.getYUniverse() + myVector.getYComponent()/2);
		
	}
	
	public void attackPlayer()
	{
		double d = Math.sqrt(Math.pow(target.getX() - this.getX(), 2) + Math.pow(target.getY() - this.getY(), 2));
		double s = (Math.random() * 3 + SPEED) / d;
		myVector.setXComponent(target.getX() - this.getX() + Math.random() * 3 - 2);
		myVector.setYComponent(target.getY() - this.getY() + Math.random() * 3 - 2);
		myVector.multiplyByScalar(s);
		
		this.rotate(Math.atan((target.getX() - this.getX()) / (target.getY() - this.getY())));
	} // function written to make AI intelligent.
	
	
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

	public void reduceHealth(double damage, Score score)
	{
		health -= damage;
		if (health <= 0)
		{
			remove();
			score.increaseScore(value);
		}
	}

	private void remove()
	{
		//sdghodushagasgsbg
	}
	
}

