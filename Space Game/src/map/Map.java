package map;
import game.SpaceGame;

public class Map
{
	
	private double[][] map1;
	
	public Map()
	{
		map1 = new double[100][8];
		this.setRow(0, -100., -100., 200., 0., 0., 2., 500., 500.);
		this.setRow(1, 300., 300., 50., 0., 0., 2., 900., 900.);
	}
	
	private void setRow(int row, double screenX, double screenY, double radius, double xVel, double yVel, double multiplier, double universeX, double universeY)
	{
		map1[row][0] = screenX;
		map1[row][1] = screenY;
		map1[row][2] = radius;
		map1[row][3] = xVel;
		map1[row][4] = yVel;
		map1[row][5] = multiplier;
		map1[row][6] = universeX;
		map1[row][7] = universeY;
		
	}
	
	public void setMap(SpaceGame game)
	{	
		for (int i = 0; i < this.map1.length; i++)
		{
			game.addGravityObject("assets/LargePlanet.png", map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5], map1[i][6], map1[i][7]);
		}
		
	}
}
