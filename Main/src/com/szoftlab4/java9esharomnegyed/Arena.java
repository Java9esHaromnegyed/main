package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    //Arena osztály mezői
    private List<Obstacle> obstacles;   // a List where the arena register all the placed Obstacle
    private Dimension size;             // Arena size
    private Robot robot1;
    private Robot robot2;

    //Üres konstruktor az elemek megfelelő inicializálásához
    public Arena(){
        LogHelper.call("Arena();");
        //64x64-es pálya lesz
        size = new Dimension(64, 64);
        //Két robot létrehozása default névvel
        robot1 = new Robot(this, "player_one", new Dimension(16, 24), Config.DIR_RIGHT);
        robot2 = new Robot(this, "player_two", new Dimension(16, 16), Config.DIR_RIGHT);

        // Areana will be build from txt, this is just an example for skeleton
        obstacles = new ArrayList<Obstacle>();

        // two Obstacle for takeEffect sequence + a Wall
        obstacles.add(new OilSpot(new Dimension(17, 16)));
        obstacles.add(new PuttySpot(new Dimension(17, 24)));
        obstacles.add(new Wall(new Dimension(18,16)));

        Dimension temp = new Dimension(0,0);
        for(int i = 0; i < size.height; i += 16)         // this will build a rectangle around the Arena out of Wall
            for(int j = 0; j < size.width; j += 16) {
                temp.setSize(i, j);
                obstacles.add(new Wall(temp));
            }

        LogHelper.ret("Arena objektum létrejött");
    }

    public void refresh() {
    }

    //Robot nevét beállító fgv
    public void setRobotName(String name, int player){
        LogHelper.call("setRobotName(" + name + ", " + player + "); Arena;");
        switch (player) {
            case 0 : robot1.setName(name);
                    break;
            case 1 : robot2.setName(name);
                    break;
        }
        LogHelper.ret("setRobotName(" + name + ", " + player + ") returned with: void;");
    }

    //Egy adott pozíció alapján esetleges ott lévő akadály elkérése
    public Obstacle getObstacle(Dimension dest) {
        LogHelper.call("getObstacle(); Arena;");
        for(int i = 0; i < obstacles.size(); i++){
            if(obstacles.get(i).getPosition().width == dest.width
                    && obstacles.get(i).getPosition().height == dest.height) {
                LogHelper.ret("getObstacle() returned with: " + obstacles.get(i).toString() + ";");
                return obstacles.get(i);
            }
        }
        LogHelper.ret("getObstacle() returned with: null;");
        return null;
    }

    //Akadály felvétele a listába
    public void addObstacle(Obstacle o) {
        LogHelper.call("addObstacle(" + o.toString() + "); Arena;");
            LogHelper.call("add(" + o.toString() + "); Arena;");
            boolean r = obstacles.add(o);
            LogHelper.ret("add() returned with: " + r + ";");
        LogHelper.ret("addObstacle() returned with: void;");
    }

    //Ütközésdetekció
    public Obstacle collision(Robot r, Dimension d) {
        LogHelper.call("collision(); Arena;");
        r.getPositon();
        // a skeletonban az ütközés detekciót nem valósítjuk meg teljes mértékben
        Obstacle w = getObstacle(d);
        if(w != null)
            if(w.isWall()) {
                w.effect(r);
                LogHelper.ret("collision() returned with: " + w.toString() + ";");
                return w;
            }
        LogHelper.ret("collision() returned with: void");
        return null;
    }

    //Pályáról kilépés érzékelése
    public boolean isOutOfArena(Dimension d) {
        LogHelper.call("isOutOfArena(); Arena");
        boolean rBool = (d.width < 0 || d.width > size.width) || (d.height < 0 || d.height > size.height);
        // akkor van pályán kívül ha bármelyik koordináta nagyobb mint a pálya méretében vagy kissebb mint nulla
        LogHelper.ret("isOutOfArena() returned with: " + rBool + ";");
        return rBool;
    }

    //Effekt érvényesítése egy adott roboton egy adott pozícióban
    public void takeEffect(Robot r, Dimension dest) {
        LogHelper.call("takeEffect(); Arena");
        Obstacle temp = collision(r, dest);     //először megnézi falbe ütközött-e
        if(temp != null)
            temp.effect(r);
        else {
            r.setPosition(dest);
            temp = getObstacle(r.getPositon());
            if (temp != null)
                temp.effect(r);
            else {
                r.clearEffects();       // ha nem lép semmire töröljük az eddigi hatásokat
                LogHelper.inline("#there is no Obstacle.");
            }
        }
        LogHelper.ret("takeEffect() returned with: void;");
    }

    //Robot objektum visszaadása
    public Robot getRobot(int id) {
        if(id == 0)
            return robot1;
        else if(id == 1)
            return robot2;
        else
            return null;
    }

    //Irányításkezelő fgv
    public void movementControl(int e) {   //Key event e
        LogHelper.call("movementControl(); Arena;");
        //int key = e.getKeyCode();
        int key = e;
        //A két robot mozgását külön gombokkal kezeljük, ezek megnyomása közvetlen a robot
        //megfelelő fgvének meghívását vonja maga után
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
        LogHelper.ret("movementControl() returned with: void;");
    }
}
