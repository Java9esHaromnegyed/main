package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class OilSpot extends Obstacle {

    //Olajfolt létrehozása adott pozícióra
    public OilSpot(Dimension pos){
        LogHelper.call("OilSpot();");
        position = pos;
        LogHelper.ret("OilSpot object created;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(Robot: " + robot.getName() + "); param: Robot; OilSpot;");
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.slipping();
        LogHelper.ret("effect(Robot: " + robot.getName() + ") returned with: void;");
    }

    //Absztrakt metódus megvalósítása a "fal-e" vizsgálathoz
    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public String toString() {
        return "OilSpot(" + position.width + ", " + position.height + ")";
    }
}
