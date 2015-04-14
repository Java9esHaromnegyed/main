package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;

import java.awt.*;

//Egy fajta Obstacle
public class OilSpot extends Obstacle {

    private int age = 0;

    //Olajfolt létrehozása adott pozícióra
    public OilSpot(Dimension pos){
        position = pos;
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Visitor v, Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        //robot.slipping();
        v.effect(this, robot);
    }

    //Absztrakt metódus megvalósítása a "fal-e" vizsgálathoz
    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public boolean isOil() {
        return true;
    }


    @Override
    public String toString() {
        return "OilSpot(" + position.width + ", " + position.height + ")";
    }

    public void ageOil(){
        age++;
    }

    public int getAge(){
        return age;
    }
}
