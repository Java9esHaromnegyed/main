package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class Wall extends Obstacle {

    //Fal létrehozása adott pozícióra
    public Wall(Dimension pos){
        position = pos;
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
    }

    @Override
    public void age() {}

    @Override
    public void collide(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stop();
    }

    @Override
    public String toString() {
        return "Wall(" + position.width + ", " + position.height + ")";
    }
}
