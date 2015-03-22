package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Arena {
    private List<Obstacle> obstacles;
    private Dimension size;
    private Robot robot1;
    private Robot robot2;

    public Arena(){
        LogHelper.call("Arena()");
        size = new Dimension(64, 64);
        //LogHelper.comment("Arena() -> Robot();");   // felesleges csak minta commentre
        robot1 = new Robot(this, "player_one", new Dimension(16, 24), Config.DIR_RIGHT);
        //LogHelper.comment("Arena() -> Robot();");   // felesleges csak minta commentre
        robot2 = new Robot(this, "player_two", new Dimension(16, 16), Config.DIR_RIGHT);
        LogHelper.ret("Arena objektum létrejött");
    }

    public void refresh() {
    }

    public void setRobotName(String name, int player){
        LogHelper.call("setRobotName(" + name + ", " + player + "); Arena;");
        switch (player) {
            case 0 : robot1.setName(name);
                    break;
            case 1 : robot2.setName(name);
                    break;
        }
        LogHelper.ret("setRobotName(name, player) lefutott;");
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
        LogHelper.call("collision(); Arena");
        r.getPositon();
        //TODO: ezzel miafasz legyen?
        LogHelper.ret("collision() lefutott");
        return null;
    }

    public boolean isOutOfArena(Dimension d) {
        LogHelper.call("isOutOfArena(); Arena");
        LogHelper.ret("isOutOfArena() lefutott");
        return false;
    }

    public void takeEffect(Robot r, Dimension position) {
        LogHelper.call("takeEffect(); Arena");
        getObstacle(position).effect(r);
        LogHelper.ret("takeEffect() lefutott");
    }

    public void movementControl(int e) {   //Key event e
        LogHelper.call("movementControl(); Arena");
        //int key = e.getKeyCode();
        int key = e;
        switch (key) {
            case Config.MOV_P1_UP:
                robot1.speedUp();
                break;
            case Config.MOV_P1_DOWN:
                robot1.slowDown();
                break;
            case Config.MOV_P1_LEFT:
                robot1.turnLeft();
                break;
            case Config.MOV_P1_RIGHT:
                robot1.turnRight();
                break;
            case Config.MOV_P1_OIL:
                robot1.dropOil();
                break;
            case Config.MOV_P1_PUTTY:
                robot1.dropPutty();
                break;
            case Config.MOV_P2_UP:
                robot2.speedUp();
                break;
            case Config.MOV_P2_DOWN:
                robot2.slowDown();
                break;
            case Config.MOV_P2_LEFT:
                robot2.turnLeft();
                break;
            case Config.MOV_P2_RIGHT:
                robot2.turnRight();
                break;
            case Config.MOV_P2_OIL:
                robot2.dropOil();
                break;
            case Config.MOV_P2_PUTTY:
                robot2.dropPutty();
                break;
            default: LogHelper.error("none of it: " + e + "; " + KeyEvent.KEY_FIRST);
        }
        LogHelper.ret("movementControl() lefutott;");
    }
}
