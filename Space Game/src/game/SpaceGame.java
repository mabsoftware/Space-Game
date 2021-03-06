/* Space Game in Java
 * Started 7/30/16.
 * Main method and main class
 * Contains game loop.
 */

package game;

import acm.graphics.*;
import physics.PointsPlanet;
import acm.program.GraphicsProgram;
import ai.Enemy;
import player.Player;
import player.Projectile;
import ui.Button;
import ui.StartBackground;
import ui.Title;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import background.Background;
import background.Star;
import map.Map;
import physics.GravityObject;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player;
	private Background background;
	private GravityObject[] gravityObjects;
	private int gravityIndex;
	private Map map;
	private double xUniverse;
	private double yUniverse;
	private Enemy[] enemies;
	private Score score;
	private ArrayList<Integer> pointsPlanets;
	private int shootCounter;
	
	Title title;
	Button start;
	Button controls;
	Button quit;
	
	boolean startBoolean;
	boolean controlsBoolean;
	boolean quitBoolean; // Not used, for future UI development.

	public static void main(String args[])
	{
		new SpaceGame().start(); // run the application.
	} // Start the application.

	public void init()
	{
		// The window and background their attributes are initialized here.
		this.setSize(320*3, 240*3);
		this.setTitle("GravityShip"); // set the size and title of the window.
		this.setBackground(Color.BLACK);
		double offset = 150;
		startBoolean = false;
		controlsBoolean = false;
		quitBoolean = false;
		StartBackground startBackground = new StartBackground(this);
		title = new Title("Gravity Ship", Color.WHITE, this.getWidth() / 2, 50, this);
		start = new Button("Start", Color.RED, 10, offset, this.getWidth() - 20, 100, this);
		controls = new Button("Controls", Color.RED, 10, offset + 120, this.getWidth() - 20, 100, this);
		quit = new Button("Quit", Color.RED, 10, offset + 240, this.getWidth() - 20, 100, this);
		addMouseListeners();
		this.waitForClick();
		if (quitBoolean) System.exit(0);
		start.removeText();
		controls.removeText();
		quit.removeText();
		this.remove(title);
		this.remove(startBackground);
		this.remove(start);
		this.remove(controls);
		this.remove(quit); // controls.
		//setting the size of the universe/map
		this.setXUniverse(5000);
		this.setYUniverse(5000);
		background = new Background(10000, 10000, 10000);
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
		score = new Score(10, 40, this);
		add(score);
		
		// Initialize player
		player = new Player(this.getWidth() / 2, this.getHeight() / 2, 0, 0, this, score);
		this.add(player); // add the player to the window.
		
		// New enemies
		enemies = new Enemy[50];
		for (int i = 0; i < enemies.length; i++)
		{
			enemies[i] = new Enemy((int) (Math.random() * 10000), (int) (Math.random() * 10000), 0, 0, player, this);
			this.add(enemies[i]);
		}

		// Planets and black holes initialized here
		gravityObjects = new GravityObject[500];
		gravityIndex = 0;
		map = new Map();
		map.setMap(this);
		pointsPlanets = map.setMap(this);
		for (GravityObject g: gravityObjects)
		{
			if (g != null)
			{
				this.add(g);
			}
		}

		// Variables and objects are initialized here.		
		gravityIndex = 0;
		shootCounter = 0;
		running = true; // running boolean for the game loop.
		
		// User input and output is initialized here.
		this.addKeyListeners();
	}

	public void run()
	{
		// Game loop
		while (running)
		{
			this.draw();

			// Monitor and move player here.
			player.monitor();
			player.move(this);
			player.adjustForGravity(gravityObjects);
			shootCounter--;
			for (GravityObject g: gravityObjects)
			{
				if (g != null)
				{
					g.move();
				}
			}
			
			// Check to increase score.
			this.orbitScore();
			
			// Move the star background.
			for (Star star: background.getBackground()) 
			{
				star.move(-player.getVector().getXComponent() / 4, -player.getVector().getYComponent() / 4);
			}
			
			// Monitor and move enemies here. 
			for (Enemy e : enemies)
				if (e != null)
					e.action();

			// Handle projectiles.
			for (int i = 0; i < player.getProjectiles().size(); i++)
			{
				if (player.getProjectiles().get(i) != null)
				{
					player.getProjectiles().get(i).adjustForGravity(gravityObjects);
					player.getProjectiles().get(i).move();
				}
			} 
			this.handleCollisions();

			// Clock tick
			pause(15);
		}
	}

	// Handle collisions between player and planets as well as enemies and projectiles
	public void handleCollisions()
	{
		for (GravityObject obj : gravityObjects)
		{
			if (obj != null)
			{
				double x = Math.pow((player.getXUniverse() - (obj.getXUniverse() + obj.getWidth() / 2)), 2);
				double y = Math.pow((player.getYUniverse() - (obj.getYUniverse() + obj.getHeight() / 2)), 2);
				if (x + y <= obj.getMass())
				{
					gameOver();
					GImage b = new GImage("assets/images/explosion.png", 0, 0);
					b.setSize(player.getWidth() * 3, player.getHeight() * 3);
					b.setLocation(player.getX() - b.getWidth()/2, player.getY() - b.getHeight()/2);
					b.sendToFront();
					add(b);
					return; //game over
				}
			}
		}
		for (int j = 0; j < enemies.length; j++)
		{
			Enemy e = enemies[j];
			if (e != null)
			{
				
				for (int i = 0; i < player.getProjectiles().size(); i++)
				{
					Projectile p = player.getProjectiles().get(i);
					//if (p.getXUniverse() >= (e.getXUniverse()-e.getWidth()) && p.getXUniverse() <= (e.getXUniverse()+e.getWidth()) &&
						//	p.getYUniverse() >= (e.getYUniverse()-e.getHeight()) && p.getYUniverse() <= (e.getYUniverse()+e.getHeight()))
					if (e.getBounds().intersects(p.getBounds()))
					{
						remove(p);
						player.removeProjectiles(i);
						remove(e);
						enemies[j] = null;
						score.increaseScore((int)(Math.random()*6) + 10);
					}
				}
			}
			if (e != null)
			{
				if (player.getBounds().intersects(e.getBounds()))
				{
					remove(e);
					enemies[j] = null;
					score.increaseScore(-5);
				}
			}
		}
	}
	
	// Score increase when near planet
	public void orbitScore()
	{
		for (int i = 0; i < pointsPlanets.size(); i++)
		{
			GravityObject obj = gravityObjects[pointsPlanets.get(i)];
			double x = Math.pow((player.getXUniverse() - (obj.getXUniverse() + obj.getWidth() / 2)), 2);
			double y = Math.pow((player.getYUniverse() - (obj.getYUniverse() + obj.getHeight() / 2)), 2);
			if (x + y <= obj.getMass() * 2)
			{
				score.increaseScore(1);
			}
			else if (x + y <= obj.getMass() * 3)
			{
				score.increaseScore(1);
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
		for (Enemy e : enemies)
		{
			if (e != null)
				e.setLocation(e.getXUniverse() - this.getXUniverse(), e.getYUniverse() - this.getYUniverse());
		}
		for (Projectile p : player.getProjectiles())
		{
			p.setLocation(p.getXUniverse() - this.getXUniverse(), p.getYUniverse() - this.getYUniverse());
		}
	}

	// Add planets method, called by map class
	public void addGravityObject(String image,
			double radius, double xVel, double yVel, double multiplier, double xUniverse, double yUniverse)
	{
		gravityObjects[gravityIndex] = new GravityObject(image, radius, xVel, yVel, multiplier, xUniverse, yUniverse, this);
		gravityIndex++;
	}

	public void addPointsPlanet(String image,
			double radius, double xVel, double yVel, double multiplier, double xUniverse, double yUniverse)
	{
		gravityObjects[gravityIndex] = new PointsPlanet(image, radius, xVel, yVel, multiplier, xUniverse, yUniverse, this);
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
			if (shootCounter <= 0)
			{
				player.shoot(score, enemies);
				player.decreaseSpeed();
				shootCounter = 50;
			}
		}
	}
	
	public void mouseMove(MouseEvent e)
	{
		if (start.contains(e.getX(), e.getY()))
		{
			startBoolean = true;
		}
		else if (controls.contains(e.getX(), e.getY()))
		{
			controlsBoolean = true;
		}
		else if (quit.contains(e.getX(), e.getY()))
		{
			quitBoolean = true;
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
	
	public void gameOver() {
		running = false;
		score.gameOverMessage();
		score.sendToFront();
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