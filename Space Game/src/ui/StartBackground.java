package ui;

import acm.graphics.GImage;
import game.SpaceGame;

public class StartBackground extends GImage
{
	public StartBackground(SpaceGame game)
	{
		super("assets/images/background.png", 0, 0);
		this.setSize(game.getWidth(), game.getHeight());
		game.add(this);
	}
}
