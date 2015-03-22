package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class Wall extends Obstacle {

    //Fal létrehozása adott pozícióra
    public Wall(Dimension d){
        LogHelper.call("Wall();");
        LogHelper.ret("Wall objektum létrejött;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); Wall; Robot: " + robot.getName() + ";");
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stop();
        LogHelper.ret("effect() lefutott;");
    }
}
