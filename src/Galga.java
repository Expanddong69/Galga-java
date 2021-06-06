import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;


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

    private static int enemy_count = 5;
    private int enemy_killed = 0;

    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;
    private Gameover gameover;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public static int HEALTH = 100;

    Random rand = new Random();
    int velocity = 0;
    int level = 1;



    public static enum STATE {
        MENU,
        GAME,
        GAMEOVER;

    };

    public static STATE state = STATE.MENU;


    public void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("bg.png");
            spriteSheet = loader.loadImage("spritesheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));
        tex = new Textures(this);
        c =  new Controller(tex,this);
        p = new Player(400, 510, tex,this,c); // this is for the player positioning,also,we put this to refer to Game game in the Player Class
        menu = new Menu();
        gameover = new Gameover();

        c.createEnemy(enemy_count);

        if(state == STATE.GAMEOVER) {
        	c.createEnemy(0);

        }
        addKeyListener(new KeyInput(this));
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

    public synchronized void stop() {
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

            //game loop and fps counter
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

        if(state == STATE.GAME) {
        	if (enemy_killed>=enemy_count) {
        		++level; // Increase level number
        		enemy_count += 2;
        		enemy_killed = 0;
        		c.createEnemy(enemy_count);

        	}
        }
        else if(state == STATE.GAMEOVER ) {
        	level = 1;
        	c.removeAllEntity(eb); // To remove all enemies after game is over to start again safely
        	c.removeAllBullets(ea); // To remove all bullets after game is over
        	enemy_killed = 0;
        	enemy_count = 5;
        	c.createEnemy(enemy_count); // To make the enemy spawn from beginning again
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

            Score(g); //showing score in the screen

            g.setColor(Color.gray);
            g.fillRect(5,5,100,20);
            g.setColor(Color.green);
            g.fillRect(5,5,HEALTH,20);
            g.setColor(Color.white);
            g.drawRect(5,5,100,20);




        }

        ////////////////////////
        else if(state == STATE.MENU) {
            menu.render(g);

        }
        else if(state == STATE.GAMEOVER) {
        	gameover.render(g);

        	//HighScore(g);
        }
        g.dispose();
        bs.show();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(3);
                velocity = 483;
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-3);
                velocity = 483;
            } else if (key == KeyEvent.VK_SPACE && !is_shooting) {
                c.addEntity(new Bullet(p.getX() + 18, p.getY(), tex, this));
                is_shooting = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
            velocity = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
            velocity = 0;
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
    String str2 = "\u221E";

    public void Score(Graphics g) {
    	int x = 5, y = 42;
    	Enemy e = new Enemy(HEALTH, enemy_count, tex, c, null);
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font fnt0 = new Font("arial", Font.PLAIN, 13);
 		g.setFont(fnt0);
 		g.setColor(Color.white);//#
 		g.drawString("Score: " + e.getScore(), x, y); // To show current score
		g.drawString("High score: " + e.getHighScore(), x, y+20); // To show high score
		g.drawString("Velocity: " + velocity + "km/s", x, y+40); // To show velocity
		if (level <= 100) {
			g.drawString("Level: " + level , x, y+60);
		}


    }

}

