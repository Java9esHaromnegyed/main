package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class OilSpot extends Obstacle {

    //Olajfolt létrehozása adott pozícióra
    public OilSpot(Dimension pos){
        LogHelper.call("OilSpot();");
        position = pos;
        LogHelper.ret("OilSpot objektum létrejött;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); OilSpot; Robot: " + robot.getName() + ";");
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.slipping();
        LogHelper.ret("effect() lefutott;");
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public String toString() {
        return "OilSpot(" + position.width + ", " + position.height + ")";
    }
}
