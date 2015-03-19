package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public abstract class Obstacle {
    protected Dimension position;
    protected Dimension size;

    public abstract void effect(Robot robot);

    public Dimension getPosition(){
        return position;
    }
}
