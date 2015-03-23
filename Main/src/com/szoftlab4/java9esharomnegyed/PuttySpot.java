package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class PuttySpot extends Obstacle {

    //Ragacsfolt létrehozása adott pozícióra
    public PuttySpot(Dimension pos){
        LogHelper.call("PuttySpot();");
        position = pos;
        LogHelper.ret("PuttySpot object created;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(Robot: " + robot.getName() + "); param: Robot; PuttySpot;");
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stuck();
        LogHelper.ret("effect(Robot: " + robot.getName() + ") returned with: void;");

    }

    //Absztrakt metódus megvalósítása a "fal-e" vizsgálathoz
    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public String toString() {
        return "PuttySpot(" + position.width + ", " + position.height + ")";
    }
}
