package ai;
//
import player.Player;

public class AI
{
	private double myX;
	private double myY;
	private double distance;
	private Player target;
	private Player[] myPlayers;
	
	public AI(double x, double y, Player[] players)
	{
		myX = x;
		myY = y;
		myPlayers = players;
		target = myPlayers[0];
		distance = Math.sqrt(Math.pow(myX - target.getXUniverse(), 2) + Math.pow(myY - target.getYUniverse(), 2));
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
		
	}
	
}
