package map;
import game.SpaceGame;

public class Map
{
	
	private double[][] map1;
	
	public Map()
	{
		map1 = new double[100][6];
		//this.setRow(0, 100, 5, 5, 2, 600, 600);//
		for (int i = 0; i < map1.length; i++)
		{
			this.setRow(i, Math.random() * 200, Math.random() * 5, Math.random() * 5, Math.random() * 2 + 1, Math.random() * 10000 - 5000, Math.random() * 10000 - 5000);
		}
		//this.setRow(1, 50, 0, 0, 2, 1000, 1);
	} //
	
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
			int x = (int)(Math.random() * 4);
			String s = "assets/images/LargePlanet.png";
			if (x == 0) s = "assets/images/LargePlanet.png";
			else if (x == 1) s = "assets/images/mars.png";
			else if (x == 2) s = "assets/images/uranus.png";
			else if (x == 3) s = "assets /images/saturn.png";
			game.addGravityObject(s, map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
		}
		
	}
}


