package game;

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
		super.setFont(score);
		points = 0;
		myGame = game;
	}

	public void increaseScore() // Increase the score by one point
	{
		points++;
		super.setLabel("Score: " + points);
	}

	public int getPoints() // Get the player's score
	{
		return points;
	}

	public void gameOverMessage() // Display the game over message in large font in the middle of the screen
	{
		Font gameOver = new Font("gameOver", 1, 80);
		super.setFont(gameOver);
		super.setLabel("Game Over... Score: " + points);
		super.setLocation(myGame.getWidth() / 2 - super.getWidth() / 2, myGame.getHeight() / 2 - super.getHeight() / 2);
	}

}