package game;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;

public class Score extends GLabel
{

	private static int points;
	private SpaceGame myGame;
	private int kills;

	public Score(int x, int y, SpaceGame game)
	{
		super("Score: 0", x, y);
		Font score = new Font("score", 1, 40);
		setColor(Color.WHITE); //The color of the score during the game will be filled with the color white
		super.setFont(score);
		points = 0;
		myGame = game;
		sendToFront();
		kills = 0;
	}

	public void increaseScore(int value) // Increase the score by one point
	{
		if (value > 2)
		{
			kills++;
		}
		points += value;
		super.setLabel("Score: " + points);
	}

	public int getPoints() // Get the player's score
	{
		return points;
	}

	public void gameOverMessage() // Display the game over message in large font in the middle of the screen
	{
		setFont("fantasy-bold-35");

		setColor(Color.CYAN);
		if (points == 1)
		{
			super.setLabel("Game Over... Score: " + points + ".   " + kills + " enemy destroyed.");
		}
		else
		{
			super.setLabel("Game Over... Score: " + points + ".   " + kills + " enemies destroyed.");
		}
		setLocation(myGame.getWidth() / 2 - getWidth() / 2, myGame.getHeight() / 4);
	}

}