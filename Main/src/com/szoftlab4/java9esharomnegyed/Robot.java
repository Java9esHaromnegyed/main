package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class Robot {
    private double speed = 0;
    private int direction = Config.DIR_UP;
    private int puttyLeft = 0;
    private int oilLeft = 0;
    private String name = "Player";
    private double coveredDistance = 0;
    private Dimension position;

    public Robot(String n, Dimension startPos, int dir) {
        name = n;
        position = startPos;
        direction = dir;
    }

    public void dropPutty() {
    }

    public void dropOil() {
    }

    public Dimension getPositon() {
        return null;
    }

    public void setPosition(Dimension pos) {
        position = pos;
    }

    public double getCoveredDistance() {
        return coveredDistance;
    }

    public void move() {
    }

    public void stuck() {
    }

    public void slipping() {
    }
}
