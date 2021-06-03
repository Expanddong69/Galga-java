import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(340, 200, 100, 50);
    public Rectangle helpButton = new Rectangle(340, 300, 100, 50);
    public Rectangle quitButton = new Rectangle(340, 400, 100, 50);

    public void render (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font ("arial" ,Font.BOLD, 50);

        g.setFont(fnt0);
        g.setColor(Color.blue);
        g.drawString("GALGA!",300, 130);

        Font fnt1 = new Font("arial",Font.BOLD,30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y+ 35);
        g2d.draw(playButton);
        g.drawString("Help", helpButton.x + 19, helpButton.y+ 35);
        g2d.draw(helpButton);
        g.drawString("Quit", quitButton.x + 19, quitButton.y+ 35);
        g2d.draw(quitButton);
    }
}
