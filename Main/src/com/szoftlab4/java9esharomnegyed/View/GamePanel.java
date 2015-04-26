package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private Canvas playGround;
    private Canvas statusCanvas;
    private ArrayList<Robot> robots;
    private ArrayList<CleanerRobot> cleaners;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Wall> walls;

    public void clockTick(){

    }

    public void statusChanged(){

    }
}
