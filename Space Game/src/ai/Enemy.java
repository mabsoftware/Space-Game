package ai;
//
import acm.graphics.GPolygon;
import player.Player;

public class Enemy extends GPolygon
{
	private double myX;
	private double myY;
	private double distance;
	private Player target;
	private Player[] myPlayers;
	private double angle;
	private double xVel;
	private double yVel;
	
	public Enemy(double startX, double startY, Player[] players)
	{
		super(startX, startY);
		angle = 0;
		myX = startX;
		myY = startY;
		myPlayers = players;
		target = myPlayers[0];
		distance = Math.sqrt(Math.pow(myX - target.getXUniverse(), 2) + Math.pow(myY - target.getYUniverse(), 2));
	}
	
	public void setAngle(double newAngle)
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
	
	public void findTarget()
	{
		for (int i = 0; i < myPlayers.length; i++)
		{
			if (Math.sqrt(Math.pow(myX - target.getXUniverse(), 2) + Math.pow(myY - target.getYUniverse(), 2)) < distance)
			target = myPlayers[i];
			distance = Math.sqrt(Math.pow(myX - target.getXUniverse(), 2) + Math.pow(myY - target.getYUniverse(), 2));
		}	
	}
	
	public void followTarget()
	{
		angle = Math.atan2(Math.abs(target.getYUniverse() - myY), Math.abs(target.getXUniverse() - myX));
		if (angle < 0)
		{
			angle += 180;
		}
		setAngle(angle);
		xVel = Math.cos(Math.toRadians(angle));
		yVel = Math.sin(Math.toRadians(angle));
	}
	
	public void move()
	{
		myX += xVel;
		myY += yVel;
	}
}
