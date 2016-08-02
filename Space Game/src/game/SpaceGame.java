
/* Space Game in Java
 * Started 7/30/16.
 * Main method and main class
 */

package game;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import ai.Enemy;
import player.Player;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.awt.Color;
import background.Background;
import background.Star;
import map.Map;
import physics.GravityObject;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player;
	private Player[] otherPlayers;
	private Background background;
	private GravityObject[] gravityObjects;
	private int gravityIndex;
	private Map map;
	private double xUniverse;
	private double yUniverse;
	private Enemy[] enemies;
	
	public static void main(String args[])
	{
		new SpaceGame().start(); // run the application.
	}
	
	public void init()
	{
		// The window and background their attributes are initialized here.
		
		this.setSize(320 * 3, 240 * 3);
		this.setTitle("Space Game"); // set the size and title of the window.
		this.setBackground(Color.BLACK);
		this.setXUniverse(500);
		this.setYUniverse(500);
		background = new Background(10000, 10000, 10000);
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
		
		//////////////////////////////////////////////////////////
		// Just initializing otherPlayers to test.               //
		//////////////////////////////////////////////////////////
		otherPlayers = new Player[5];
		
		// Planets and black holes initialized here
		gravityObjects = new GravityObject[100];
		gravityIndex = 0;
		map = new Map();
		map.setMap(this);
		for (GravityObject g: gravityObjects)
		{
			if (g != null)
			{
				this.add(g);
			}
		}
		
		// Variables and objects are initialized here.		
		gravityIndex = 0;
		running = true; // running boolean for the game loop.
		player = new Player(this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		this.add(player); // add the player to the window.
		enemies = new Enemy[1];
		enemies[0] = new Enemy(this.getWidth() / 3, this.getHeight() / 3, 0, 0, player, otherPlayers);
		this.add(enemies[0]);
		
		// User input and output is initialized here.
		this.addKeyListeners();
	}
	
	public void run()
	{
		// Game loop
		while (running)
		{
			this.draw();
			
			// Monitor and move all objects here.
			player.monitor();
			player.move(this);
			player.adjustForGravity(gravityObjects);
			for (GravityObject g: gravityObjects)
			{
				if (g != null)
				{
					g.move();
				}
			}
			this.handleCollisions();
			
			//GravityObject.handleGravityObjectInteractions(gravityObjects);
			
			for (Star star: background.getBackground()) // Move the star background.
			{
				star.move(-player.getVector().getXComponent(), -player.getVector().getYComponent());
			}
				
			// Clock tick
			pause(100);
		}
		GLabel gameOver = new GLabel("Game Over. You blew up.", getWidth() / 2, getHeight() / 2);
		gameOver.setFont("fantasy-bold-48");
		gameOver.setLocation(this.getWidth() / 2 - gameOver.getWidth() / 2, getHeight() / 2);
		gameOver.setColor(Color.CYAN);
		add(gameOver);
	}
	
	
	// Handle collisions
	public void handleCollisions()
	{
		for (GravityObject obj : gravityObjects)
		{
			double x = Math.pow((player.getXUniverse() - (obj.getXUniverse() + obj.getWidth()/2)), 2);
			double y = Math.pow((player.getYUniverse() - (obj.getYUniverse() + obj.getHeight()/2)), 2);
			if (x + y <= obj.getMass())
			{
				running = false;
			}

			
		}
	}
	
	
	// Sets screen coordinates of all objects based on Universe coordinates
	public void draw() 
	{
		for (GravityObject obj : gravityObjects)
		{
			if (obj != null)
				obj.setLocation(obj.getXUniverse() - this.getXUniverse(), obj.getYUniverse() - this.getYUniverse());	
		}
	}
	
	public void addGravityObject(String image,
			double radius, double xVel, double yVel, double multiplier, double xUniverse, double yUniverse)
	{
		gravityObjects[gravityIndex] = new GravityObject(image, radius, xVel, yVel, multiplier, xUniverse, yUniverse);
		gravityIndex++;
	}
	
	// All user input is handled here.
	public void keyPressed(KeyEvent k)
	{
		if (k.getKeyChar() == 'a')
		{
			player.goLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			player.goRight();
		}
		else if (k.getKeyChar() == 'w')
		{
			player.increaseSpeed();
		}
		else if (k.getKeyChar() == 's')
		{
			player.decreaseSpeed();
		}
		else if (k.getKeyChar() == ' ')
		{
			player.laser(this);
			player.decreaseSpeed();
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		if (k.getKeyChar() == 'a')
		{
			player.stopMovingLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			player.stopMovingRight();
		}
	}

	// Getters and Setters for Universe coordinates
	public double getXUniverse() 
	{
		return xUniverse;
	}
	public void setXUniverse(double xUniverse) 
	{
		this.xUniverse = xUniverse;
	}
	public double getYUniverse() 
	{
		return yUniverse;
	}
	public void setYUniverse(double yUniverse) 
	{
		this.yUniverse = yUniverse;
	}
}