package map;
import game.SpaceGame;
//
public class Map
{
	
	private double[][] map1;
	
	public Map()
	{
		map1 = new double[100][6];
		this.setRow(0, 100, 0, 0, 2, 600, 600);
		//this.setRow(1, 50, 0, 0, 2, 1000, 1);
	}
	
	private void setRow(int row, double radius, double xVel, double yVel, double multiplier, double universeX, double universeY)
	{
		map1[row][0] = radius;
		map1[row][1] = xVel;
		map1[row][2] = yVel;
		map1[row][3] = multiplier;
		map1[row][4] = universeX;
		map1[row][5] = universeY;
		
	}
	
	public void setMap(SpaceGame game)
	{	
		for (int i = 0; i < this.map1.length; i++)
		{
			game.addGravityObject("assets/images/LargePlanet.png", map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
		}
		
	}
}


