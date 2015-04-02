package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public abstract class AbstractRobot {
    protected Arena arena;                        // just to call addObstacles(Obstacle obstacle) func.
    protected double speed = Config.SPD_DEFFAULT; // speed only goes from 0 to Config.SPD_LIMIT with Config.SPD_UNIT steps
    // (only slowing effect can achieve other speed argument)
    protected int direction = Config.DIR_RIGHT; // direction only goes from 0 to 3
    protected String name = "Robot";
    protected Dimension position;                 // robot position on the arena
    protected int id = -1;

    //Robot pozíciójának lekérdezése
    public Dimension getPosition() {
        LogHelper.call("getPosition(): Dimension; Robot; " + name + ";");
        LogHelper.ret("getPosition() returned with: " + position + ";");
        return position;
    }

    //Robot pozíciójának beállítása
    public void setPosition(Dimension pos) {
        LogHelper.call("setPosition(" + pos + "); param: Dimension; Robot; " + name + ";");
        position = pos;
        LogHelper.ret("setPosition(" + pos + ") returned with: void;");
    }

    public abstract void turnLeft();

    public abstract void turnRight();

    //Robot nevének lekérdezése
    public String getName() {
        //LogHelper.call("getName(); Robot; " + name + ";");
        //LogHelper.ret("getName() returned with: " + name + ";");
        return name;
    }

    //Robot nevének beállítása
    public void setName(String name) {
        LogHelper.call("setName(" + name + "); Robot; " + this.name + ";");
        this.name = name;
        LogHelper.ret("setName(" + name + ") returned with: void;");
    }

    abstract public void move();

    public int getID() {
        return id;
    }
}
