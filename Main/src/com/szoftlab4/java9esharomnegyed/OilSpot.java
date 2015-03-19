package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class OilSpot extends Obstacle {
    public OilSpot(Dimension d){
        System.out.println("OilSpot létrejött");
    }

    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); OilSpot");
        robot.slipping();
        System.out.println("effect(); lefutott");
    }
}
