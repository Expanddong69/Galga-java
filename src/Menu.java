import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {

	public Rectangle playButton = new Rectangle(320, 300, 110, 50);
	public Rectangle quitButton = new Rectangle(Galga.WITDTH / 4 + 120, 400, 110, 50);
	Image img;

    public void render (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        


		try {
			img = ImageIO.read(getClass().getResourceAsStream("logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img,216,90,null);

		Font fnt1 = new Font("arial", Font.PLAIN, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Play", playButton.x + 25, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Quit", quitButton.x + 25, quitButton.y + 35);
		g2d.draw(quitButton);
    }



}
