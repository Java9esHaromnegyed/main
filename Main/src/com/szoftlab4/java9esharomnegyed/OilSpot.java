package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class OilSpot extends Obstacle {
    public OilSpot(Dimension pos){
        System.out.println("OilSpot létrejött");
        position = pos;
    }


    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); OilSpot");
        robot.slipping();
        System.out.println("effect(); lefutott");
    }
}
