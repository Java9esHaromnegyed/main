package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class PuttySpot extends Obstacle {

    //Ragacsfolt létrehozása adott pozícióra
    public PuttySpot(Dimension pos){
        LogHelper.call("PuttySpot();");
        position = pos;
        LogHelper.ret("PuttySpot objektum létrejött;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); PuttySpot; Robot: " + robot.getName() + ";");
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stuck();
        LogHelper.ret("effect() lefutott;");

    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public String toString() {
        return "PuttySpot(" + position.width + ", " + position.height + ")";
    }
}
