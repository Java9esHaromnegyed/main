package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class PuttySpot extends Obstacle {
    public PuttySpot(Dimension pos){
        System.out.println("PuttySpot létrejött");
        position = pos;

    }

    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); PuttySpot");
        robot.stuck();
        System.out.println("effect(); lefutott");

    }
}
