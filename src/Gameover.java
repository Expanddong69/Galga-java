import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Gameover {
	
	public Rectangle playButton = new Rectangle(283, 320, 190, 50);
	public Rectangle backToMenuButton = new Rectangle(245, 400, 270, 50);
	
	public void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Font fnt0 = new Font("arial", Font.BOLD, 90);
		g.setFont(fnt0);
		g.setColor(Color.red);
		g.drawString("GAME OVER", Galga.WITDTH / 7, 270);
		
		Font fnt1 = new Font("arial", Font.PLAIN, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Play again", playButton.x + 25, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Back to main menu", backToMenuButton.x + 5, backToMenuButton.y + 35);
		g2d.draw(backToMenuButton);
		
	}
}
