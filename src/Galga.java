import com.src.main.classes.EntityA;
import com.src.main.classes.EntityB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class Galga extends Canvas  implements Runnable {
    public static final int WITDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCALE = 2;
    public static final String TITLE = "Galga!";

    private boolean running;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean is_shooting = false;

    private int enemy_count = 5;
    private int enemy_killed = 0;

    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

  public static enum STATE {
        MENU,
        GAME

    };

    public static STATE state = STATE.MENU;


    public void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("bg.png");
            spriteSheet = loader.loadImage("spritesheet.png");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));
        tex = new Textures(this);
        p = new Player(400, 510, tex); // this is for the player positioning,also,we put this to refer to Game game in the Player Class
        c =  new Controller(tex,this);
        menu = new Menu();

        c.createEnemy(enemy_count);

        ea = c.getEntityA();
        eb = c.getEntityB();

        this.addMouseListener(new MouseInput());

    }



    private synchronized void start() {

        if (running)

            return;

        running = true;
        thread = new Thread(this);
        thread.start();


    }

    private synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);

    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfticks = 60.0; // FPS cap
        double ns = 1000000000 / amountOfticks;
        double delta = 0; // calculating the time passed so the ticks can catch up witht the FPS
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            //Game Loop
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
                frames++;
                render();
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println(updates + " ticks, FPS " + frames);
                    updates = 0;
                    frames = 0;

                }
            }


        }
        stop();

    }

    private void tick() {
        if (state == STATE.GAME) {
            p.tick();
            c.tick();
        }
        if (enemy_killed>=enemy_count) {
            enemy_count += 2;
            enemy_killed = 0;
            c.createEnemy(enemy_count);
        }


    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////
        g.drawImage(image, 0, 0, getWidth(),getWidth(), this);
        g.fillRect(0,0,800,800);
        g.drawImage(background,0,0,null);
        if(state == STATE.GAME) {
            p.render(g);
            c.render(g);
        }

        ////////////////////////
        else if(state == STATE.MENU) {
            menu.render(g);

        }
        g.dispose();
        bs.show();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(3);
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-3);
            } else if (key == KeyEvent.VK_SPACE && !is_shooting) {
                c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
                is_shooting = true;


            }
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        }
        else if (key == KeyEvent.VK_SPACE) {
            is_shooting = false;

        }
    }



    public static void main(String[] args) {
        Galga game = new Galga();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame frame = new JFrame(Galga.TITLE);
        frame.add(game);
        frame.pack();
        frame.setSize(WITDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;

    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }


}

