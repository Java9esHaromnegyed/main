package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class PuttySpot extends Obstacle {

    private int decay = 0;

    //Ragacsfolt létrehozása adott pozícióra
    public PuttySpot(Dimension pos){
        position = pos;
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stuck();

    }

    @Override
    public String toString() {
        return "PuttySpot(" + position.width + ", " + position.height + ")";
    }

    public void decayPutty(){
        decay++;
    }

    public int getDecay(){
        return decay;
    }
}
