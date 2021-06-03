import java.awt.image.BufferedImage;

public class Textures {
    private SpriteSheet ss;
    public BufferedImage player, missile, enemy;

    public Textures (Galga game) {
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();

    }


    private void getTextures(){
        enemy = ss.grabImage(1,60,44,47);
        player = ss.grabImage(1,1,44,48);
        missile = ss.grabImage(0,116,7,25);

    }


}
