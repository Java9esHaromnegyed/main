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
    public boolean dead = false;

    //Robot pozíciójának lekérdezése
    public Dimension getPosition() {
        return position;
    }

    //Robot pozíciójának beállítása
    public void setPosition(Dimension pos) {
        position = pos;
    }

    public void turnLeft(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead)
            direction++;
        if (direction > Config.DIR_LEFT)     // when direction reached 4 we have to change it to 0.  direction only goes from 0 to 3
            direction = Config.DIR_UP;
    };

    public void turnRight(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead)
            direction--;
        if(direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
            direction = Config.DIR_LEFT;
    };

    //A robot halálakor bekövetkező esemény
    public abstract void die();

    public int getDirection() {
        return direction;
    }

    //Robot nevének lekérdezése
    public String getName() {
        return name;
    }

    //Robot nevének beállítása
    public void setName(String name) {
        this.name = name;
    }

    public abstract void move();

    public double getSpeed(){
        return speed;
    }

    public int getID() {
        return id;
    }
}
