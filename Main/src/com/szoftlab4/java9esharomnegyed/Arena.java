package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    //Arena osztály mezői
    private Dimension size;             // Arena size
    private List<Obstacle> obstacles;   // a List where the arena register all the placed Obstacle
    private List<Robot> robots;
    private List<CleanerRobot> cleaners;

    //Üres konstruktor az elemek megfelelő inicializálásához
    public Arena(){
        LogHelper.call("Arena();");
        //64x64-es pálya lesz
        size = new Dimension(64, 64);
        //Két robot létrehozása default névvel
        robots = new ArrayList<Robot>();
        robots.add(new Robot(this, "player_one", new Dimension(16, 24), Config.DIR_RIGHT, 1));
        robots.add(new Robot(this, "player_two", new Dimension(16, 16), Config.DIR_RIGHT, 2));

        cleaners = new ArrayList<CleanerRobot>();

        // Areana will be build from txt, this is just an example for skeleton
        obstacles = new ArrayList<Obstacle>();

        // two Obstacle for takeEffect sequence + a Wall
        obstacles.add(new OilSpot(new Dimension(17, 16)));
        obstacles.add(new PuttySpot(new Dimension(17, 24)));
        obstacles.add(new Wall(new Dimension(18, 16)));

        Dimension temp = new Dimension(0,0);
        for(int i = 0; i < size.height; i += 16)         // this will build a rectangle around the Arena out of Wall
            for(int j = 0; j < size.width; j += 16) {
                temp.setSize(i, j);
                obstacles.add(new Wall(temp));
            }

    }

    //Robot nevét beállító fgv
    public void setRobotName(String name, int player){
        robots.get(player).setName(name);
    }

    //Egy adott pozíció alapján esetleges ott lévő akadály elkérése
    public Obstacle getObstacle(Dimension dest) {
        for(int i = 0; i < obstacles.size(); i++){
            if(obstacles.get(i).getPosition().width == dest.width
                    && obstacles.get(i).getPosition().height == dest.height) {
                return obstacles.get(i);
            }
        }
        return null;
    }

    //cleaner robot ezt hívja meg az akadály eltüntetésére
    public void removeObstacle(Obstacle obstacle){
        obstacles.remove(obstacle);
    }

    //Akadály felvétele a listába
    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    public void addCleanerRobot(Dimension pos, int dir, int id){
        CleanerRobot roboC = new CleanerRobot(this, pos, dir, id);
        cleaners.add(roboC);
    }

    public CleanerRobot getCleanerRobot(int id){
        return cleaners.get(id);
    }

    public void addRobot(String name, Dimension pos, int dir, int id){
        Robot robo = new Robot(this, name, pos, dir, id);
        robots.add(robo);
    }

    //Robot objektum visszaadása
    public Robot getRobot(int id) {
        return robots.get(id);
    }

    //Ütközésdetekció
    public Obstacle collision(Robot r, Dimension d) {
        // a skeletonban az ütközés detekciót nem valósítjuk meg teljes mértékben
        Obstacle w = getObstacle(d);
        if(w != null)
            if(w.isWall()) {
                w.effect(r);
                return w;
            }
        return null;
    }

    //Pályáról kilépés érzékelése
    public boolean isOutOfArena(Dimension d) {
        boolean rBool = (d.width < 0 || d.width > size.width) || (d.height < 0 || d.height > size.height);
        // akkor van pályán kívül ha bármelyik koordináta nagyobb mint a pálya méretében vagy kissebb mint nulla
        return rBool;
    }

    //Effekt érvényesítése egy adott roboton egy adott pozícióban
    public void takeEffect(Robot r, Dimension dest) {
        Obstacle temp = collision(r, dest);     //először megnézi falbe ütközött-e
        if(temp != null) {
            temp.effect(r);
            //megfelelő helyre elhelyezni majd tesztelni hogy ott történik e valami.
        }else {
            r.setPosition(dest);
            temp = getObstacle(r.getPosition());
            if (temp != null)
                temp.effect(r);
            else {
                r.clearEffects();       // ha nem lép semmire töröljük az eddigi hatásokat
            }
        }
    }

    //Irányításkezelő fgv
    public void movementControl(int e) {
        //Key event lesz majd az 'int e'-ből
        //int key = e.getKeyCode()
        int key = e;
        //A két robot mozgását külön gombokkal kezeljük, ezek megnyomása közvetlen a robot
        //megfelelő fgvének meghívását vonja maga után
        switch (key) {
            case Config.MOV_P1_UP:
                robots.get(0).speedUp();
                break;
            case Config.MOV_P1_DOWN:
                robots.get(0).slowDown();
                break;
            case Config.MOV_P1_LEFT:
                robots.get(0).turnLeft();
                break;
            case Config.MOV_P1_RIGHT:
                robots.get(0).turnRight();
                break;
            case Config.MOV_P1_OIL:
                robots.get(0).dropOil();
                break;
            case Config.MOV_P1_PUTTY:
                robots.get(0).dropPutty();
                break;
            case Config.MOV_P2_UP:
                robots.get(1).speedUp();
                break;
            case Config.MOV_P2_DOWN:
                robots.get(1).slowDown();
                break;
            case Config.MOV_P2_LEFT:
                robots.get(1).turnLeft();
                break;
            case Config.MOV_P2_RIGHT:
                robots.get(1).turnRight();
                break;
            case Config.MOV_P2_OIL:
                robots.get(1).dropOil();
                break;
            case Config.MOV_P2_PUTTY:
                robots.get(1).dropPutty();
                break;
        }
    }

    public void tick() {
        for (int j = 0; j < robots.size(); j++) {
            robots.get(j).move();
        }
        for (int j = 0; j < cleaners.size(); j++) {
            cleaners.get(j).move();
        }
        for (int j = 0; j < obstacles.size(); j++) {
            OilSpot oilSpot;
            if (obstacles.get(j).isOil()) {
                oilSpot = (OilSpot) obstacles.get(j);
                oilSpot.ageOil();
            }
        }

    }
}
