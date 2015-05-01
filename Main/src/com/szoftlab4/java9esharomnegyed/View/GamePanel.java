package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private Canvas playGround;
    private Canvas statusCanvas;
    private List<Robot> robots;
    private List<CleanerRobot> cleaners;
    private List<Obstacle> obstacles;
    private List<Wall> walls;





    public void update(){
        Arena temp = Game.getArena();
        robots = temp.getRobotList();
        cleaners = temp.getCleanersList();
        obstacles = temp.getObstacleList();
        walls = temp.getWallList();

        this.invalidate();
    }
}
