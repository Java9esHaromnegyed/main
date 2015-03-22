package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Osztály a robotokhoz
public class Robot {
    private Arena arena;                        // just to call addObstacles(Obstacle obstacle) func.
    private double speed = 0;                   // speed only goes from 0 to Config.SPD_LIMIT with Config.SPD_UNIT steps
                                                                // (only slowing effect can achieve other speed argument)
    private int direction = Config.DIR_UP;      // direction only goes from 0 to 3
    private int puttyLeft = Config.PUTTY_TANK;
    private int oilLeft = Config.OIL_TANK;
    private String name = "Robot";
    private double coveredDistance = 0;
    private Dimension position;                 // robot position on the arena
    boolean paralyzed = false;                  // this flag gets true while robot stands on oil

    //Robot létrehozása egy arénához adott névvel, kezdő pozícióval és iránnyal.
    public Robot(Arena a, String n, Dimension startPos, int dir) {
        LogHelper.call("Robot();");
        arena = a;
        name = n;
        position = startPos;
        direction = dir;
        LogHelper.ret("Robot objektum létrejött;");
    }

    //Ragacsfolt lehelyezése a pályára
    public void dropPutty() {
        LogHelper.call("dropPutty(); Robot; " + name + "; addObstacle(position, putty);");
        arena.addObstacle(new PuttySpot(position));
        LogHelper.ret("dropPutty() lefutott;");
    }

    //Olajfolt lehelyezése a pályára
    public void dropOil() {
        LogHelper.call("dropOil(); Robot; " + name + "; addObstacle(position, oil);");
        arena.addObstacle(new OilSpot(position));
        LogHelper.ret("dropOil() lefutott;");
    }

    //Robot pozíciójának lekérdezése
    public Dimension getPositon() {
        LogHelper.call("getPosition(); Robot; " + name + ";");
        LogHelper.ret("getPosition() returned with: " + position + ";");
        return position;
    }

    //Robot pozíciójának beállítása
    public void setPosition(Dimension pos) {
        LogHelper.call("setPosition(pos); Dimension; Robot; " + name + ";");
        position = pos;
        LogHelper.ret("setPosition(pos) lefutott;");
    }

    //Robot által megtett távolság lekérdezése
    public double getCoveredDistance() {
        LogHelper.call("getCoveredDistance(); Robot; " + name + ";");
        LogHelper.ret("getCoveredDistance() returned with: " + coveredDistance);
        return coveredDistance;
    }

    //Robotnak balra fordulás utasítás adása
    public void turnLeft() {
        LogHelper.call("turnLeft(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed)
            direction++;
        if (direction > Config.DIR_LEFT)     // when direction reached 4 we have to change it to 0.  direction only goes from 0 to 3
            direction = Config.DIR_UP;
        LogHelper.ret("turnLeft() lefutott;");
    }

    //Robotnak jobbra fordulás utasítás adása
    public void turnRight(){
        LogHelper.call("turnRight(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed)
            direction--;
        if(direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
            direction = Config.DIR_LEFT;
        LogHelper.ret("turnRight() lefutott;");
    }

    //Robot gyorsítása
    public void speedUp(){
        LogHelper.call("speedUp(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed)
            //Csak ha nem lépi túl a max. sebességet
            if(speed + Config.SPD_UNIT < Config.SPD_LIMIT)  // speed only goes from 0 to Config.SPD_LIMIT
                speed += Config.SPD_UNIT;                       // with Config.SPD_UNIT steps
            else
                speed = Config.SPD_LIMIT;
        LogHelper.ret("speedUp() lefutott;");
    }

    //Robot lassítása
    public void slowDown(){
        LogHelper.call("slowDown(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed)
            //Csak ha nem csökken 1-nél lentebb a sebessége
            if(speed - Config.SPD_UNIT > 0)
                speed += Config.SPD_UNIT;
            else
                speed = 0;
        LogHelper.ret("slowDown() lefutott;");
    }

    //Robot mozgatása
    public void move() {
        System.out.println("move(); Robot; " + name + ";");
        System.out.println("move() lefutott;");
    }

    //Robotra ragacs hat
    public void stuck() {
        System.out.println("stuck(); Robot");
    }

    //Robotra olajfolt hat
    public void slipping() {
        System.out.println("slipping(); Robot; " + name + ";");
        System.out.println("sipping() lefutott;");
    }

    //Robot megállítása (pl fallal ütközés esetén)
    public void stop(){
        System.out.println("stopg(); Robot; " + name + ";");
        System.out.println("stop() lefutott;");
    }

    //Robot nevének lekérdezése
    public String getName() {
        System.out.println("getName(); Robot; " + name + ";");
        System.out.println("getName() returned with: " + name + ";");
        return name;
    }

    //Robot nevének beállítása
    public void setName(String name) {
        LogHelper.call("setName(" + name + "); Robot; " + name + ";");
        LogHelper.ret("setName() lefutott;");
        this.name = name;
    }
}
