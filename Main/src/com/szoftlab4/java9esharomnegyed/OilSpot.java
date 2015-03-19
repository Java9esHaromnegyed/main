package com.szoftlab4.java9esharomnegyed;

public class OilSpot extends Obstacle {
    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); OilSpot");
        robot.slipping();
        System.out.println("effect(); lefutott");
    }
}
