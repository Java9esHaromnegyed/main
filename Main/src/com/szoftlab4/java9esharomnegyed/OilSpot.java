package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public class OilSpot extends Obstacle {
    public OilSpot(Dimension pos){
        LogHelper.call("OilSpot();");
        position = pos;
        LogHelper.ret("OilSpot objektum létrejött;");
    }


    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); OilSpot; Robot: " + robot.getName() + ";");
        robot.slipping();
        LogHelper.ret("effect() lefutott;");
    }
}
