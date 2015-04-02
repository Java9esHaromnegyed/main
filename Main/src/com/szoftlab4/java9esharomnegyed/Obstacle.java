package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

//Absztrakt osztály a különboző akadálytípuokhoz, feladat hogy definiálja
//az egységes mezőget az akadályokhoz és az effect() és getPosition() fgveket
public abstract class Obstacle {
    //Obstacle osztály mezői
    protected Dimension position;
    protected Dimension size;
    private int age = -1;
    private long createTime = -1;

    //Robotra hatáshoz fgv
    public abstract void effect(Robot robot);

    //Akadály pozícióját adja vissza
    public Dimension getPosition() {
        //LogHelper.call("getPosition(); Obstacle;");
        //LogHelper.ret("getPosition() returned with: " + position + ";");
        return position;
    }

    //megmondja hogy fal-e?
    public abstract boolean isWall();

    //Szöveges megjelenítés akadályokra
    public abstract String toString();
}
