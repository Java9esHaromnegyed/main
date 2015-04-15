package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public abstract class AbstractRobot {
    protected Arena arena;                        // just to call back to Arena in functions
    protected double speed = Config.SPD_DEFFAULT; // speed only goes from 0 to Config.SPD_LIMIT with Config.SPD_UNIT steps
                                                        // (only slowing effect can achieve other speed argument)
    protected int direction = Config.DIR_RIGHT; // direction only goes from 0 to 3
    protected String name = "Robot";
    protected Dimension position;                 // robot position on the arena
    protected int id = -1;
    protected boolean dead = false;

    //Robot pozíciójának lekérdezése
    public Dimension getPosition() {
        return position;
    }

    //Robot pozíciójának beállítása
    public void setPosition(Dimension pos) {
        position = pos;
    }

    public abstract void turnLeft();

    public abstract void turnRight();

    public abstract void die();

    //Robot nevének lekérdezése
    public String getName() {
        return name;
    }

    //Robot nevének beállítása
    public void setName(String name) {
        this.name = name;
    }

    abstract public void move();

    public int getID() {
        return id;
    }

}