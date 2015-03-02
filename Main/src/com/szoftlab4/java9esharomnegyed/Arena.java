package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.util.List;

public class Arena {
    private List<Obstacle> obstacles;
    private Dimension size;
    private Dimension startingPos1;
    private Dimension startingPos2;

    public void refresh() {
    }

    public int getObstacle(Dimension dest){
        return 0;
    }

    public void addObstacle(Dimension pos, int id){
    }

    public Dimension getStartingPos(int robotNumber) {
        return null;
    }

    public Obstacle collision(Robot r, Dimension d) {
        return null;
    }

    public boolean isOutOfArena(Dimension d){
        return false;
    }
}
