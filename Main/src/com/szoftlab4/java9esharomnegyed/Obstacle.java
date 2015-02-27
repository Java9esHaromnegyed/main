package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

/**
 * Created by Ricsard on 2015.02.27..
 */
public abstract class Obstacle {
    private Dimension position;
    private Dimension size;
    private int id;

    public abstract void effect(Robot robot);
}
