package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Absztrakt osztály a különboző akadálytípuokhoz, feladat hogy definiálja
//az egységes mezőget az akadályokhoz és az effect() és getPosition() fgveket
public abstract class Obstacle {
    //Obstacle osztály mezői
    protected Dimension position;
    protected Dimension size;

    //Robotra hatáshoz fgv
    public abstract void effect(Robot robot);

    //Akadály pozícióját adja vissza
    public Dimension getPosition(){
        LogHelper.call("getPosition(); Obstacle;");
        LogHelper.ret("getPosition() returned with: " + position + ";");
        return position;
    }

    //megmondja hogy fal-e?
    public abstract boolean isWall();

    //Szöveges megjelenítés akadályokra
    public abstract String toString();
}
