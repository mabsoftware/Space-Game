package ui;

import java.awt.Color;

import acm.graphics.GLabel;

public class ControlsScreen extends GLabel
{
	public ControlsScreen()
	{
		super("The objective of the game is to get points. This can be done in two ways: \n - Orbiting special shiny planets "
				+ "\n - killing enemies \n \nIf enemies touch you, however, you lose points. The game ends when you touch a planet."
				+ "Try to get as many points as you can!");
		this.setColor(Color.WHITE);
		this.setFont("fantasy-bold-48");
		this.setLocation(40, 40);
	}
}