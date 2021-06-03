import com.src.main.classes.EntityA;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Bullet extends GameObject implements EntityA {

    private Textures tex;
    private Galga game;

    BufferedImageLoader loader;
    BufferedImage image;

    public Bullet(double x, double y, Textures tex, Galga game) {
        super(x,y);

        this.tex = tex;
        this.game = game;
    }
        public void tick () {
            y -= 10;
            if (Physics.Collision(this, game.eb)) {

                System.out.println("COLLISION DETECTED");

            }
        }

        public void render (Graphics g){
            g.drawImage(tex.missile, (int) x, (int) y, null);
        }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,7,25);
    }

    public double getX() {
        return x;
        }
        public double getY() {
        return y;
        }
    }

