import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener
{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25; //the more the bigger object in game
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75; //the more the longer for snake movement
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	int rottenAppleX;
	int rottenAppleY;
	char direction = 'R';
	boolean running = false;
	private String highScore = "";
	Timer timer;
	Random random;
	
	GamePanel()
	{
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame()
	{
		newApple();
		newRottenApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g)
	{
		if(running)
		{
			//making grid line
			/*
			for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++)
			{
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, i * UNIT_SIZE);
			}*/
			
			//making apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//making rotten apple
			g.setColor(Color.magenta);
			g.fillOval(rottenAppleX, rottenAppleY, UNIT_SIZE, UNIT_SIZE);
			
			//making body of the snake
			for(int i = 0; i < bodyParts; i++)
			{
				if(i == 0)
				{
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else
				{
					g.setColor(new Color(45, 180, 0));
					/*g.setColor(new Color(random.nextInt(255), random.nextInt(255), 
							random.nextInt(255))); //snake rgb */ 
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			/* buat ngecek highscore
			if(applesEaten == 0)
			{
				//intialize the highscore
				applesEaten = this.getHighscore();
			}*/
			
			//draw scores
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Scores: " + applesEaten, 
					(SCREEN_WIDTH - metrics.stringWidth("Scores: " + applesEaten)) / 2, 
					g.getFont().getSize());
		}
		else
		{
			gameOver(g);
		}
	}
	
	public void newApple()
	{
		//spawning apple on random tiles
		appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}
	
	public void newRottenApple()
	{
		//spawning apple on random tiles
		rottenAppleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		rottenAppleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}
	
	public void move()
	{
		for(int i = bodyParts; i > 0; i--)
		{
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		switch(direction)
		{
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple()
	{
		if((x[0] == appleX) && (y[0] == appleY))
		{
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	public void checkRottenApple()
	{
		if((x[0] == rottenAppleX) && (y[0] == rottenAppleY))
		{
			running = false;
			//bodyParts -= 3; //if rotten apple eaten, snake will become shorter by 5
			//applesEaten -=3; //if you want to reduce the score when eating rotten apple
			/*if(bodyParts == 0) //when snake reaches 0 body parts, the game is over
			{
				running = false;
			}*/	
		}
	}
	
	public void checkCollisions()
	{
		//check if head collides with body
		for(int i = bodyParts; i > 0; i--)
		{
			if((x[0] == x[i]) && (y[0] == y[i]))
			{
				running = false;
			}
		}
		
		//check if head touch left border
		if(x[0] < 0)
			running = false;
		
		//check if head touch right border
		if(x[0] > SCREEN_WIDTH)
			running = false;
		
		//check if head touch top border
		if(y[0] < 0)
			running = false;
		
		//check if head touch bottom border
		if(y[0] > SCREEN_HEIGHT)
			running = false;
		
		//stop timer after collisions
		if(!running)
			timer.stop();
	}
	
	public void bestScore() {
		if(highScore < applesEaten) highScore = applesEaten;
	}
	
	public void gameOver(Graphics g)
	{
		//game over text
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, 
				SCREEN_HEIGHT / 3);
		
		//scores text after game over
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("High Scores: " + applesEaten, 
				(SCREEN_WIDTH - metrics2.stringWidth("Total Scores: " + applesEaten)) / 2, 
				SCREEN_HEIGHT / 2);
				
		//highest score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Highest Score: " + highScore, 
				(SCREEN_WIDTH - metrics3.stringWidth("Highest Score " + highScore)) / 2, 
				(SCREEN_HEIGHT / 2)+50);
	}
	
	/*Bingung bikin highscore
	public String getHighscore() //throws FileNotFoundException
	{
		FileReader readFile = null;
		BufferedReader reader = null;
		
		try
		{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch(Exception e)
		{
			return "0";
		}
		finally
		{
			try
			{
				if(reader != null)
					reader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(running)
		{
			move();
			checkApple();
			checkRottenApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_LEFT:
				if(direction != 'R')
					direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L')
					direction = 'R';
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D')
					direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U')
					direction = 'D';
				break;
			}
		}
	}

}
