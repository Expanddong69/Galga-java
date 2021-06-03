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


        if(mx >= 340 && mx <= 520) {
            if (my >= 200 && my <= 250)
            {
                //Play Button
                Galga.state = Galga.STATE.GAME;
            }
        }

        //Help Button

        if(mx >= 340 && mx <= 520) {
            if (my >= 300 && my <= 350)
            {
                //pressed play button
                Galga.state = Galga.STATE.GAME;
            }
        }

        //Quit Button
        if(mx >= 340 && mx <= 520) {
            if (my >= 400 && my <= 450)
            {
                //pressed play button
             System.exit(1);
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
