package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

//Absztrakt osztály a különboző akadálytípuokhoz, feladat hogy definiálja
//az egységes mezőget az akadályokhoz és az effect() és getPosition() fgveket
public abstract class Obstacle {
    //Obstacle osztály mezői
    protected Dimension position;
    protected Dimension size;
    protected Image image;

    protected int decay;
    protected int age;

    //Robotra hatáshoz fgv
    public abstract void effect(Robot robot);

    //Akadály pozícióját adja vissza
    public Dimension getPosition() {
        return position;
    }

    //Öregíti az Akadályt, csak Olajfoltnál használt
    public abstract void age();

    //Lekezeli a Robot adott Akadállyal történő "ütközését"
    public abstract void collide(Robot robot);

    //Vissza adja az adott Akadály elhasználtságát
    public int getDecay(){
        return decay;
    }

    //Vissza adja adott Akadály korát
    public int getAge(){
        return age;
    }

    //Vissza adja az adott Akadályt a játékteren reprezentáló grafikát
    public Image getImage() {
        return image;
    }

    //Szöveges megjelenítés akadályokra
    public abstract String toString();
}
