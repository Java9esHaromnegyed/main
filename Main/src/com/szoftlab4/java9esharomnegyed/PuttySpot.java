package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public class PuttySpot extends Obstacle {
    public PuttySpot(Dimension pos){
        LogHelper.call("PuttySpot();");
        position = pos;
        LogHelper.ret("PuttySpot objektum létrejött;");
    }

    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); PuttySpot; Robot: " + robot.getName() + ";");
        robot.stuck();
        LogHelper.ret("effect() lefutott;");

    }
}
