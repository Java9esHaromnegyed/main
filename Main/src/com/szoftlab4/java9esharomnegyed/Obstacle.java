package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;

import java.awt.*;

//Absztrakt osztály a különboző akadálytípuokhoz, feladat hogy definiálja
//az egységes mezőget az akadályokhoz és az effect() és getPosition() fgveket
public abstract class Obstacle {
    //Obstacle osztály mezői
    protected Dimension position;
    protected Dimension size;

    protected int decay;
    protected int age;

    //Robotra hatáshoz fgv
    public abstract void effect(Robot robot);

    //Akadály pozícióját adja vissza
    public Dimension getPosition() {
        return position;
    }

    public abstract void age();

    public abstract void decay();

    public abstract void collide(Robot robot);

    public int getDecay(){
        return decay;
    }

    public int getAge(){
        return age;
    }

    //Szöveges megjelenítés akadályokra
    public abstract String toString();
}
