import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx= e.getX();
        int my= e.getY();
        

        // Play button
        if(mx >= 320 && mx <= 430 && Galga.state == Galga.STATE.MENU) {
			
			if(my >= 300 && my <= 350) {
				// Press play button
				Galga.state = Galga.STATE.GAME;
				
			}
		}

        //Quit Button
        if(mx >= Galga.WITDTH / 4 + 120 && mx <= Galga.WITDTH / 4 + 230 && Galga.state == Galga.STATE.MENU) {
			
			if(my >= 400 && my <= 450 ) {
				// Press play button
				
				System.exit(1);
				
			}
			
		}
        
        // Play again button
        if(mx >= 283 && mx <= 473 && Galga.state == Galga.STATE.GAMEOVER) {
        	if(my >= 320 && my <= 370) {
        		Galga.HEALTH = 100;
        		Galga.state = Galga.STATE.GAME;
        		
        	}
        }
        
        // Back to main menu button
        if(mx >= 245 && mx <= 520 && Galga.state == Galga.STATE.GAMEOVER) {
        	if(my >= 400 && my <= 450) {
        		Galga.state = Galga.STATE.MENU;
        		Galga.HEALTH = 100;
        	}
        }

    }





    @Override
    public void mouseReleased(MouseEvent e) {



    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
