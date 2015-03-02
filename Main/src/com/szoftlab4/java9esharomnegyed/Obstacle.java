package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public abstract class Obstacle {
    private Dimension position;
    private Dimension size;
    private int id;

    public abstract void effect(Robot robot);
}
