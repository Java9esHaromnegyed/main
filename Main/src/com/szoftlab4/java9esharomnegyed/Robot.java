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
    private int effect = Config.EFF_NONE;

    public Robot(String n, Dimension startPos, int dir){
        name = n;
        position = startPos;
        direction = dir;
    }

    public void dropPutty() {
    }

    public void dropOil() {
    }

    public Dimension getPositon() {
        return position;
    }

    public void setPosition(Dimension pos){
        position = pos;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double s){
        speed = s;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir){
        direction = dir;
    }

    public int getEffect(){
        return effect;
    }

    public void setEffect(int id){
        effect = id;
    }

    public double getCoveredDistance(){
        return coveredDistance;
    }
}
