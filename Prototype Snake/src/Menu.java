import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {
	
	// Size of Play Button
	public Rectangle playButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 150, 100, 50);
	// Size of About Button
	public Rectangle aboutButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 250, 100, 50);
	// Size of Quit Button
	public Rectangle quitButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 350, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// Title "SNAKE GAME"
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SNAKE GAME", GamePanel.WIDTH / 2 + 120, 100);
		
		// Play Button
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 23, playButton.y + 35);
		g2d.draw(playButton);
		// About Button
		g.drawString("About", playButton.x + 15, playButton.y + 135);
		g2d.draw(aboutButton);
		g.drawString("Quit", playButton.x + 23, playButton.y + 235);
		g2d.draw(quitButton);
	} 
}
