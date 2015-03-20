package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.util.List;

public class Arena {
    private List<Obstacle> obstacles;
    private Dimension size;
    private Robot robot1;
    private Robot robot2;

    public Arena(){
        System.out.println("Arena objektum létrejött");
        size = new Dimension(64, 64);
        robot1 = new Robot(this, "player_one", new Dimension(16, 24), Config.DIR_RIGHT);
        robot2 = new Robot(this, "player_two", new Dimension(16, 16), Config.DIR_RIGHT);
    }

    public void refresh() {
    }

    public void setRobotName(String name, int player){
        System.out.println("setRobotName(name, player); Arena;");
        switch (player) {
            case 0 : robot1.setName(name);
                    break;
            case 1 : robot2.setName(name);
                    break;
        }
        System.out.println("setRobotName(name, player) lefutott;");
    }

    public Obstacle getObstacle(Dimension dest) {
        System.out.println("getObstacle(); Arena");
        System.out.println("getObstacle() lefutott");
        return null;
    }

    public void addObstacle(Obstacle o) {
        System.out.println("addObstacle() -> add(o); Arena");
        obstacles.add(o);
        System.out.println("addObstacle() lefutott");
    }

    public Obstacle collision(Robot r, Dimension d) {
        System.out.println("collision(); Arena");
        r.getPositon();
        Obstacle temp = getObstacle(d);
        System.out.println("collision() lefutott");
        return temp;
    }

    public boolean isOutOfArena(Dimension d) {
        System.out.println("isOutOfArena(); Arena");
        System.out.println("isOutOfArena() lefutott");
        return false;
    }

    public void takeEffect(Robot r, Dimension position) {
        System.out.println("takeEffect(); Arena");
        getObstacle(position).effect(r);
        System.out.println("takeEffect() lefutott");
    }

    public void movementControl(Event e) {
        System.out.println("movementControl(); Arena");
        switch (e.key) {
            case Config.MOV_P1_UP:
                System.out.println("movementControl(); Arena; robot1.UP");
                robot1.move();
                break;
            case Config.MOV_P1_DOWN:
                System.out.println("movementControl(); Arena; robot1.DOWN");
                robot1.move();
                break;
            case Config.MOV_P1_LEFT:
                System.out.println("movementControl(); Arena; robot1.LEFT");
                robot1.move();
                break;
            case Config.MOV_P1_RIGHT:
                System.out.println("movementControl(); Arena; robot1.RIGHT");
                robot1.move();
                break;
            case Config.MOV_P1_OIL:
                System.out.println("movementControl(); Arena; robot1.dropOIL");
                robot1.dropOil();
                break;
            case Config.MOV_P1_PUTTY:
                System.out.println("movementControl(); Arena; robot1.dropPUTTY");
                robot1.dropPutty();
                break;
            case Config.MOV_P2_UP:
                System.out.println("movementControl(); Arena; robot2.UP");
                robot1.move();
                break;
            case Config.MOV_P2_DOWN:
                System.out.println("movementControl(); Arena; robot2.DOWN");
                robot1.move();
                break;
            case Config.MOV_P2_LEFT:
                System.out.println("movementControl(); Arena; robot2.LEFT");
                robot1.move();
                break;
            case Config.MOV_P2_RIGHT:
                System.out.println("movementControl(); Arena; robot2.RIGHT");
                robot1.move();
                break;
            case Config.MOV_P2_OIL:
                System.out.println("movementControl(); Arena; robot1.dropOIL");
                robot1.dropOil();
                break;
            case Config.MOV_P2_PUTTY:
                System.out.println("movementControl(); Arena; robot1.dropPUTTY");
                robot1.dropPutty();
                break;
        }
        System.out.println("movementControl() lefutott");
    }
}
