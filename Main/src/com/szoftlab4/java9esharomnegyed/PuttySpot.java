package com.szoftlab4.java9esharomnegyed;

public class PuttySpot extends Obstacle {
    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); PuttySpot");
        robot.stuck();
        System.out.println("effect(); lefutott");

    }
}
