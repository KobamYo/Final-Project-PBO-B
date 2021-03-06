import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random; 
import javax.swing.JPanel;  

public class GamePanel extends JPanel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 95;
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
	int highScore = 0;
	Timer timer;
	Random random;
	private Menu menu;
	private About about;
	int time;
    	private Level level;
	
	public static enum STATE{
		MENU,
		ABOUT,
		GAME,
		EASY,
		MEDIUM,
		HARD
	};
	public static STATE State = STATE.MENU;
	private Rectangle backButton;
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MouseInput());
		
		startGame();
	}
	
	public void startGame()
	{	
		newApple();
		newRottenApple();
		menu = new Menu();
		about = new About();
		level = new Level();
		running = true;
		this.time = 270;
        	this.timer = new Timer(this.time, this);
        	this.timer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void game(Graphics g) {
		
		if(running)
		{
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
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
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
	
	public void draw(Graphics g)
	{
		if(State == STATE.GAME)
		{
			level.render(g);
		}
		if(State == STATE.EASY)
		{
			this.timer.stop();
			this.time = 270;
	        	this.timer = new Timer(this.time, this);
	        	this.timer.start();
			game(g);
		}
		if(State == STATE.MEDIUM)
		{
			this.timer.stop();
			this.time = 270 / 2;
	        	this.timer = new Timer(this.time, this);
	       		this.timer.start();
			game(g);
		}
		if(State == STATE.HARD)
		{
			this.timer.stop();
			this.time = 270 / 3;
	        	this.timer = new Timer(this.time, this);
	        	this.timer.start();
			game(g);
		}
		else if (State == STATE.MENU) 
		{
			menu.render(g);
		}
		else if(State == STATE.ABOUT)
		{
			about.render(g);
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
			newRottenApple();
		}
	}
	
	public void checkRottenApple()
	{
		if((x[0] == rottenAppleX) && (y[0] == rottenAppleY))
		{
			running = false;
			newRottenApple();
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
	
	public void gameOver(Graphics g)
	{
		if(applesEaten > highScore) highScore = applesEaten;
		backButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 430, 100, 45);
		Graphics2D g2d = (Graphics2D) g;
		
		//game over text
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 55));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, 
				SCREEN_HEIGHT / 3);
		
		//scores text after game over
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Your Score: " + applesEaten, 
				(SCREEN_WIDTH - metrics2.stringWidth("Your Score " + applesEaten)) / 2, 
				SCREEN_HEIGHT / 2);
		
		//highest score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Highest Score: " + highScore, 
				(SCREEN_WIDTH - metrics3.stringWidth("Highest Score " + highScore)) / 2, 
				(SCREEN_HEIGHT / 2)+50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x + 23, backButton.y + 30);
		g2d.draw(backButton);
	}
	
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
			switch(e.getKeyCode()){
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
