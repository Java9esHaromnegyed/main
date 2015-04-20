package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class OilSpot extends Obstacle {

    //Olajfolt létrehozása adott pozícióra
    public OilSpot(Dimension pos){
        position = pos;
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.slipping();
    }

    @Override
    public void age(){
        age++; //Olajfolt öregítése
        LogHelper.inline("oilAged atPos: [" + position.width + "; " + position.height + "] age: " + age);
    }

    @Override
    public void collide(Robot robot) {}

    @Override
    public String toString() {
        return "OilSpot(" + position.width + ", " + position.height + ")";
    }
}
