package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class PuttySpot extends Obstacle {

    public PuttySpot(Dimension d){
        System.out.println("PuttySpot létrejött");
    }

    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); PuttySpot");
        robot.stuck();
        System.out.println("effect(); lefutott");

    }
}
