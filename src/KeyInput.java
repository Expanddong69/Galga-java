import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter { // keyAdapter is used to call the methods below
    Galga game;
    public KeyInput(Galga game){
        this.game = game;

    }
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);

    }
    public void keyReleased (KeyEvent e) {
        game.keyReleased(e);
    }


}
