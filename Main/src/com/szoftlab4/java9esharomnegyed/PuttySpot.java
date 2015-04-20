package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class PuttySpot extends Obstacle {

    //Ragacsfolt létrehozása adott pozícióra
    public PuttySpot(Dimension pos){
        position = pos;
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stuck();
        decay++; //ragacs öregítése
        LogHelper.inline("puttyDecayed atPos: [" + position.width + "; " + position.height + "] decay: " + decay);
    }

    @Override
    public void age() {}


    @Override
    public void collide(Robot robot) {}

    @Override
    public String toString() {
        return "PuttySpot(" + position.width + ", " + position.height + ")";
    }


}
