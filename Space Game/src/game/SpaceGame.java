
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
	private Player[] players;
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
		this.setXUniverse(1000);
		this.setYUniverse(1000);
		background = new Background(10000, 10000, 10000);
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
		
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
		players = new Player[1];
		players[0] = new Player("assets/images/player.png", this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		this.add(players[0]); // add the player to the window.
		enemies = new Enemy[1];
		enemies[0] = new Enemy(this.getWidth() / 3, this.getHeight() / 3, 0, 0, players);
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
			players[0].monitor();
			players[0].move(this);
			players[0].adjustForGravity(gravityObjects);
			for (GravityObject g: gravityObjects)
			{
				if (g != null)
				{
					g.move();
				}
			}
			
			//GravityObject.handleGravityObjectInteractions(gravityObjects);
			
			for (Star star: background.getBackground()) // Move the star background.
			{
				star.move(-players[0].getVector().getXComponent(), -players[0].getVector().getYComponent());
			}
				
			// Clock tick
			pause(100);
		}
	}
	
	
	// Handle collisions
	public void handleCollisions()
	{
		for (GravityObject obj : gravityObjects)
		{
			players[0].getXUniverse();
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
			players[0].goLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			players[0].goRight();
		}
		else if (k.getKeyChar() == 'w')
		{
			players[0].increaseSpeed();
		}
		else if (k.getKeyChar() == 's')
		{
			players[0].decreaseSpeed();
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		if (k.getKeyChar() == 'a')
		{
			players[0].stopMovingLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			players[0].stopMovingRight();
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