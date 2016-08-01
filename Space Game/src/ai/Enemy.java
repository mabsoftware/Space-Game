package ai;
//
import acm.graphics.GPolygon;
import java.awt.Color;
import player.Player;
import physics.Vector;

public class Enemy extends GPolygon
{
	
	private double distance;
	private Player target;
	private Player[] myPlayers;
	private double angle;
	
	private Vector myVector;
	
	public Enemy(double startX, double startY, double xVel, double yVel, Player[] players)
	{
		super(startX, startY);
		
		this.addEdge(0, -10);
		this.addEdge(-8, 5);
		this.addEdge(8, 5);
		this.setFillColor(Color.YELLOW);
		this.setColor(Color.YELLOW);
		this.setFilled(true);
		
		myVector = new Vector(xVel, yVel);
		
		angle = 0;
		myPlayers = players;
		target = myPlayers[0];
		distance = Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2));
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
			if (Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2)) < distance)
			target = myPlayers[i];
			distance = Math.sqrt(Math.pow(this.getX() - target.getXUniverse(), 2) + Math.pow(this.getY() - target.getYUniverse(), 2));
		}	
	}
	
	public void followTarget()
	{
		angle = Math.atan2(Math.abs(target.getYUniverse() - this.getY()), Math.abs(target.getXUniverse() - this.getY()));
		if (angle < 0)
		{
			angle += 180;
		}
		setAngle(angle);
		myVector.setXComponent(myVector.getXComponent() + Math.sin(Math.toRadians(angle)));
		myVector.setYComponent(myVector.getYComponent() + Math.cos(Math.toRadians(angle)));
	}
	
	public void move()
	{
		this.move(myVector.getXComponent(), myVector.getYComponent());
	}
}
