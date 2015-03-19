package com.szoftlab4.java9esharomnegyed;

public class Wall extends Obstacle {
    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); Wall");
        robot.stop();
        System.out.println("effect(); lefutott");
    }
}
