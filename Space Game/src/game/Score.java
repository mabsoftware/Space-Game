package game;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;

public class Score extends GLabel
{

	private static int points;
	private SpaceGame myGame;

	public Score(int x, int y, SpaceGame game)
	{
		super("Score: 0", x, y);
		Font score = new Font("score", 1, 40);
		setColor(Color.WHITE);
		super.setFont(score);
		points = 0;
		myGame = game;
		sendToFront();
	}

	public void increaseScore(int value) // Increase the score by one point
	{
		points += value;
		super.setLabel("Score: " + points);
	}

	public int getPoints() // Get the player's score
	{
		return points;
	}

	public void gameOverMessage() // Display the game over message in large font in the middle of the screen
	{
		setFont("fantasy-bold-48");
		
		setColor(Color.CYAN);
		super.setLabel("Game Over... Score: " + points);
		setLocation(myGame.getWidth() / 2 - getWidth() / 2, myGame.getHeight() / 4);
	}

}