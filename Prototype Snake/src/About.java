import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class About {
	
	public Rectangle backButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 430, 100, 45);
	
	public void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// Title
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("ABOUT", 250, 100);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Snake Game is a game", 200, 200);
		g.drawString("built for an Object-Oriented Programming Final Project", 40, 225);
		g.drawString("by", 290, 250);
		g.drawString("Adifa Widyadhani Chanda D - 5025201013", 100, 275);
		g.drawString("Fachrendy Zulfikar Abdillah - 5025201018", 103, 300);
		g.drawString("Rycahaya Sri Hutomo - 5025201046", 130, 325);
		g.drawString("Wahyu Tri Saputro - 5025201217", 145, 350);
		
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x + 23, backButton.y + 30);
		g2d.draw(backButton);
	}
}
