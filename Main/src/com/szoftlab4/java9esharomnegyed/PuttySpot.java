package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;
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
    public void effect(Visitor v, Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        //robot.stuck();
        v.effect(this, robot);
    }

    //Absztrakt metódus megvalósítása a "fal-e" vizsgálathoz
    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public boolean isOil() {
        return false;
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
