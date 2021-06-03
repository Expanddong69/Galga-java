package com.src.main.classes;
import java.awt.Rectangle;

import java.awt.*;

public interface EntityB {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();

}
