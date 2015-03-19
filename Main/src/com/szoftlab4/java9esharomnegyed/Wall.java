package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class Wall extends Obstacle {

    public Wall(Dimension d){
        System.out.println("Wall létrejött");
    }

    @Override
    public void effect(Robot robot) {
        System.out.println("effect(); Wall");
        robot.stop();
        System.out.println("effect(); lefutott");
    }
}
