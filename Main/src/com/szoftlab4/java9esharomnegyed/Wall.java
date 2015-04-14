package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Egy fajta Obstacle
public class Wall extends Obstacle {

    //Fal létrehozása adott pozícióra
    public Wall(Dimension pos){
        LogHelper.call("Wall();");
        position = pos;
        LogHelper.ret("Wall object created;");
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Visitor v, Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        //robot.stop();
        v.effect(this, robot);
    }

    @Override
    public String toString() {
        return "Wall(" + position.width + ", " + position.height + ")";
    }
}
