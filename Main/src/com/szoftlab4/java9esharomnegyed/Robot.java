package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class Robot {
    Arena arena;
    private double speed = 0;
    private int direction = Config.DIR_UP;
    private int puttyLeft = 0;
    private int oilLeft = 0;
    private String name = "Player";
    private double coveredDistance = 0;
    private Dimension position;

    public Robot(Arena a, String n, Dimension startPos, int dir) {
        System.out.println("Robot objektum létrejött;");
        arena = a;
        name = n;
        position = startPos;
        direction = dir;
    }

    public void dropPutty() {
        System.out.println("dropPutty(); Robot; addObstacle(position, putty)");
        arena.addObstacle(new PuttySpot(position));
        System.out.println("dropPutty() lefutott;");
    }

    public void dropOil() {
        System.out.println("dropOil(); Robot; addObstacle(position, oil)");
        arena.addObstacle(new OilSpot(position));
        System.out.println("dropOil() lefutott;");
    }

    public Dimension getPositon() {
        System.out.println("getPosition(); Robot;");
        System.out.println("getPosition() returned with: " + position);
        return position;
    }

    public void setPosition(Dimension pos) {
        System.out.println("setPosition(pos); Dimension; Robot;");
        position = pos;
        System.out.println("setPosition(pos) lefutott");
    }

    public double getCoveredDistance() {
        System.out.println("getCoveredDistance(); Robot;");
        System.out.println("getCoveredDistance() returned with: " + coveredDistance);
        return coveredDistance;
    }

    public void move() {
        System.out.println("move(); Robot;");
        System.out.println("move() lefutott;");
    }

    public void stuck() {
        System.out.println("stuck(); Robot");
    }

    public void slipping() {
        System.out.println("slipping(); Robot;");
        System.out.println("sipping() lefutott;");
    }

    public void stop(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
