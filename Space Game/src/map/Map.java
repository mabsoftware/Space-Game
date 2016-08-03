package map;
import java.util.ArrayList;

import game.SpaceGame;

public class Map
{

	private double[][] map1;

	public Map()
	{
		map1 = new double[249][6];
		
		for (int i = 0; i < map1.length / 4; i++)
		{
			this.setRow(i, Math.random() * 126 + 50, 0, 0, Math.random() * 1.5 + 1, Math.random() * 5200, Math.random() * 4800);
		}

		for (int i = 0; i < map1.length / 4; i++)
		{
			this.setRow(i, Math.random() * 126 + 50, 0, 0, Math.random() * 1.5 + 1, Math.random() * 4800 + 5200, Math.random() * 5200);
		}

		for (int i = 0; i < map1.length / 4; i++)
		{
			this.setRow(i, Math.random() * 126 + 50, 0, 0, Math.random() * 1.5 + 1, Math.random() * 5200 + 4800, Math.random() * 4800 + 5200);
		}

		for (int i = 0; i < map1.length / 4; i++)
		{
			this.setRow(i, Math.random() * 126 + 50, 0, 0, Math.random() * 1.5 + 1, Math.random() * 4800, Math.random() * 5200 + 4800);
		}
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
	
	public ArrayList<Integer> setMap(SpaceGame game)
	{	
		ArrayList<Integer> pointsPlanets = new ArrayList<Integer>();
		for (int i = 0; i < this.map1.length; i++)
		{
			//importing the differnt images that will be used in the game
			int x = (int) (Math.random() * 7);
			String s;
			if (x % 4 == 0)
			{
				//Adding the Large Planet Image
				s = "assets/images/LargePlanet.png";
				game.addGravityObject(s, map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
			}
			else if (x % 4 == 1)
			{
				//importing the image(s) of Mars 
				s = "assets/images/mars.png";
				game.addGravityObject(s, map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
			}
			else if (x % 4 == 2)
			{
				//importing the image(s) of Uranus
				s = "assets/images/uranus.png";
				game.addGravityObject(s, map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
			}
			else if (x == 3)
			{
				//importing the image(s) of the Shiny Planet, which will result in a gain of points by crashing into the planet
				s = "assets/images/ShinyPlanet.png";
				game.addPointsPlanet(s, map1[i][0], map1[i][1], map1[i][2], map1[i][3], map1[i][4], map1[i][5]);
				pointsPlanets.add(i);
			}
		}
		return pointsPlanets;
	}
	
	public double[][] getMap()
	{
		return map1;
	}
}
