package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

/**
 * Created by Ricsard on 2015.02.27..
 */
public class Robot {
    private double speed = 0;
    private int direction;
    private int puttyLeft = 0;
    private int oilLeft = 0;
    private String name = "Player";
    private double coveredDistance = 0;
    private Dimension position;

    public boolean hasPutty() {
        return false;
    }

    public boolean hasOil() {
        return false;
    }

    public Dimension getPositon() {
        return null;
    }

    public int getDirection() {
        return 0;
    }
}
