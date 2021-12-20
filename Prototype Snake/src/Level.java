import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Level {
	public Rectangle easyButton = new Rectangle (GamePanel.WIDTH/ 2 + 100, 300, 100, 50);
	public Rectangle mediumButton = new Rectangle (GamePanel.WIDTH/ 2 + 240, 300, 120, 50);
	public Rectangle hardButton = new Rectangle (GamePanel.WIDTH/ 2 + 400, 300, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("LEVEL", GamePanel.WIDTH / 2 + 225, 200);
		
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Easy", easyButton.x + 23, easyButton.y + 35);
		g2d.draw(easyButton);
		g.drawString("Medium", easyButton.x + 155, easyButton.y + 35);
		g2d.draw(mediumButton);
		g.drawString("Hard", easyButton.x + 323, easyButton.y + 35);
		g2d.draw(hardButton);
	}
}
