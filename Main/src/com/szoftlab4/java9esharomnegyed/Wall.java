package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public class Wall extends Obstacle {

    public Wall(Dimension d){
        LogHelper.call("Wall();");
        LogHelper.ret("Wall objektum létrejött;");
    }

    @Override
    public void effect(Robot robot) {
        LogHelper.call("effect(); Wall; Robot: " + robot.getName() + ";");
        robot.stop();
        LogHelper.ret("effect() lefutott;");
    }
}
