import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	private Menu menu;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//mendeklarasikan untuk letak x dan y
		int mx = e.getX();
		int my = e.getY();
		
		/**
		 	public Rectangle playButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 150, 100, 50);
			public Rectangle aboutButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 250, 100, 50);
			public Rectangle quitButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 350, 100, 50);
			
			public Rectangle backButton = new Rectangle (GamePanel.WIDTH/ 2 + 250, 430, 100, 45);
			
			public Rectangle easyButton = new Rectangle (GamePanel.WIDTH/ 2 + 100, 300, 100, 50);
			public Rectangle mediumButton = new Rectangle (GamePanel.WIDTH/ 2 + 240, 300, 120, 50);
			public Rectangle hardButton = new Rectangle (GamePanel.WIDTH/ 2 + 400, 300, 100, 50);
		 */
		
		//jika diklik diarea tersebut akan menuju ke GamePanel.STATE.GAME
		if(mx >= GamePanel.WIDTH / 2 + 250 && mx <= GamePanel.WIDTH / 2 + 350) {
			if (my >= 150 && my<= 200) {
				GamePanel.State = GamePanel.STATE.GAME;
			}
		}
		
		//jika diklik diarea tersebut akan menuju ke GamePanel.STATE.ABOUT
		if(mx >= GamePanel.WIDTH / 2 + 250 && mx <= GamePanel.WIDTH / 2 + 350) {
			if (my >= 250 && my<= 300) {
				GamePanel.State = GamePanel.STATE.ABOUT;
			}
		}
		
		if(mx >= GamePanel.WIDTH / 2 + 250 && mx <= GamePanel.WIDTH / 2 + 350) {
			if (my >= 350 && my<= 400) {
				System.exit(1);
			}
		}
		
		if(mx >= GamePanel.WIDTH / 2 + 250 && mx <= GamePanel.WIDTH / 2 + 430) {
			if (my >= 430 && my<= 470) {
				GamePanel.State = GamePanel.STATE.MENU;
			}
		}
		
		if(mx >= GamePanel.WIDTH / 2 + 100 && mx <= GamePanel.WIDTH / 2 + 250) {
			if (my >= 300 && my<= 330) {
				GamePanel.State = GamePanel.STATE.EASY;
			}
		}
		if(mx >= GamePanel.WIDTH / 2 + 240 && mx <= GamePanel.WIDTH / 2 + 340) {
			if (my >= 300 && my<= 330) {
				GamePanel.State = GamePanel.STATE.MEDIUM;
			}
		}
		if(mx >= GamePanel.WIDTH / 2 + 400 && mx <= GamePanel.WIDTH / 2 + 500) {
			if (my >= 300 && my<= 330) {
				GamePanel.State = GamePanel.STATE.HARD;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
