
import com.src.main.classes.EntityA;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {


    private double velX = 0;
    private double velY = 0;
    private BufferedImage player;
    private Textures tex;


    public Player(double x, double y, Textures tex){
        super(x,y);
        this.tex = tex;
    }
      public void tick(){ // movement limiter
        x+=velX;
        y+=velY;
        if (x <= 0)
            x = 0;
          if (x >= 800 -63)
              x = 800 - 63;
          if (y <= 0)
              y = 0;
          if (y >= 600 - 32 )
              y = 600 -32;


     }
    public void render(Graphics g) {
        g.drawImage(tex.player,(int)x,(int)y,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y,44,48);
    }

    public double getX(){
        return x;

}

    public void setX(double x){
        this.x=x;


}
    public double getY(){
        return y;

    }
    public void setY(double y){
        this.y = y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }


}