import com.src.main.classes.EntityA;
import com.src.main.classes.EntityB;

import java.awt.*;
import java.awt.Rectangle;

import java.util.Random;

 public class Enemy extends GameObject implements EntityB {
     private Galga game;
    private Textures tex;
    Random r = new Random();
    private Controller c;

    private int speed = r.nextInt(3) + 1;

    public Enemy (double x, double y, Textures tex,Controller c,Galga game) {

        super(x,y);
        this.tex = tex;
        this.c = c;
        this.game = game;

    }
    public void tick (){
        y += speed;

        if (y > (Galga.HEIGHT -10 * Galga.SCALE -10 ))  //enemy spawn
        {
            y = -15;
            x = r.nextInt(50);
            speed = r.nextInt(3) + 1;
        }

        for (int i = 0; i<game.ea.size(); i++)
        {
            EntityA tempEnt = game.ea.get(i);
            if (Physics.Collision(this,tempEnt))
            {
                c.removeEntity(tempEnt); //removes bullet when it hits the enemy
                c.removeEntity(this); // enemy death,the enemy sprite disappears whenever the bullet hits it
                game.setEnemy_killed(game.getEnemy_killed() + 1);
            }
        }

     }
    public void render(Graphics g){
        g.drawImage(tex.enemy, (int)x, (int)y,null);




    }

     @Override
     public Rectangle getBounds() {
         return new Rectangle((int)x, (int)y,44,47);
     }


     @Override





     public double getX() {
        return x;
    }

    public double getY (){
        return y;

    }
    public  void setY (double y) {
        this.y=y;
    }
}
