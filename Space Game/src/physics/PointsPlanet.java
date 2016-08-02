package physics;

import game.SpaceGame;

public class PointsPlanet extends GravityObject
{

	public PointsPlanet(String image, double radius, double xVel, double yVel, double multiplier, double xu,
			double yu, SpaceGame game)
	{
		super(image, radius, xVel, yVel, multiplier, xu, yu, game);
	}

}