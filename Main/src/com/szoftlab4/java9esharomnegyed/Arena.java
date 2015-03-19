package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.util.List;

public class Arena {
    private List<Obstacle> obstacles;
    private Dimension size;
    private Dimension startingPos1;
    private Dimension startingPos2;
    private Robot robot1;
    private Robot robot2;

    public void refresh() {
    }

    public void setRobotName(String name, int player){
        switch (player) {
            case 0 : robot1.setName(name);
                    break;
            case 1 : robot2.setName(name);
        }

    }

    public int getObstacle(Dimension dest) {
        return 0;
    }

    public void addObstacle(Dimension pos, int id) {
    }

    public Dimension getStartingPos(int robotNumber) {
        return null;
    }

    public Obstacle collision(Robot r, Dimension d) {
        return null;
    }

    public boolean isOutOfArena(Dimension d) {
        return false;
    }

    public void takeEffect(Robot r, Dimension position) {
    }

    public void movementControl(Event e) {
    }
}
