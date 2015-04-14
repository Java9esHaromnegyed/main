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
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stop();
    }

    //Absztrakt metódus megvalósítása a "fal-e" vizsgálathoz
    @Override
    public boolean isWall() {
        return true;
    }

    @Override
    public boolean isOil() {
        return false;
    }


    @Override
    public String toString() {
        return "Wall(" + position.width + ", " + position.height + ")";
    }
}
