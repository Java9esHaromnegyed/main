package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Osztály a robotokhoz
public class Robot {
    private Arena arena;                        // just to call addObstacles(Obstacle obstacle) func.
    private double speed = Config.SPD_DEFFAULT; // speed only goes from 0 to Config.SPD_LIMIT with Config.SPD_UNIT steps
                                                                // (only slowing effect can achieve other speed argument)
    private int direction = Config.DIR_RIGHT;   // direction only goes from 0 to 3
    private int puttyLeft = Config.PUTTY_TANK;
    private int oilLeft = Config.OIL_TANK;
    private String name = "Robot";
    private double coveredDistance = 0;
    private Dimension position;                 // robot position on the arena
    boolean paralyzed = false;                  // this flag gets true while robot stands on oil
    boolean slowed = false;                     // this flag gets true while robot stands on putty

    //Robot létrehozása egy arénához adott névvel, kezdő pozícióval és iránnyal.
    public Robot(Arena a, String n, Dimension startPos, int dir) {
        LogHelper.call("Robot();");
        arena = a;
        name = n;
        position = startPos;
        direction = dir;
        LogHelper.ret("Robot object created;");
    }

    //Ragacsfolt lehelyezése a pályára
    public void dropPutty() {
        LogHelper.call("dropPutty(); Robot; " + name + ";");
        arena.addObstacle(new PuttySpot(position));
        LogHelper.ret("dropPutty() returned with: void;");
    }

    //Olajfolt lehelyezése a pályára
    public void dropOil() {
        LogHelper.call("dropOil(); Robot; " + name + ";");
        arena.addObstacle(new OilSpot(position));
        LogHelper.ret("dropOil() returned with: void;");
    }

    //Robot pozíciójának lekérdezése
    public Dimension getPositon() {
        LogHelper.call("getPosition(): Dimension; Robot; " + name + ";");
        LogHelper.ret("getPosition() returned with: " + position + ";");
        return position;
    }

    //Robot pozíciójának beállítása
    public void setPosition(Dimension pos) {
        LogHelper.call("setPosition(" + pos + "); param: Dimension; Robot; " + name + ";");
        position = pos;
        LogHelper.ret("setPosition(" + pos + ") returned with: void;");
    }

    //Robot által megtett távolság lekérdezése
    public double getCoveredDistance() {
        LogHelper.call("getCoveredDistance(); Robot; " + name + ";");
        coveredDistance = position.width;                   // lineáris pályán a megtett táv egyezik az x koordiátával
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
        LogHelper.ret("turnLeft() returned with: void;");
    }

    //Robotnak jobbra fordulás utasítás adása
    public void turnRight(){
        LogHelper.call("turnRight(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed)
            direction--;
        if(direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
            direction = Config.DIR_LEFT;
        LogHelper.ret("turnRight() returned with: void;");
    }

    //Robot gyorsítása
    public void speedUp(){
        LogHelper.call("speedUp(); Robot; " + name + ";");
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás
        if(!paralyzed || speed == 0)
            //Csak ha nem lépi túl a max. sebességet
            if(speed + Config.SPD_UNIT < Config.SPD_LIMIT)  // speed only goes from 0 to Config.SPD_LIMIT
                speed += Config.SPD_UNIT;                       // with Config.SPD_UNIT steps
            else
                speed = Config.SPD_LIMIT;
        LogHelper.ret("speedUp() returned with: void;");
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
        LogHelper.ret("slowDown() returned with: void;");
    }

    //Robot mozgatása
    public void move() {
        LogHelper.call("move(); Robot; " + name + ";");
        Dimension destination = position;
        switch (direction) {
            case Config.DIR_UP: destination.setSize(destination.width, destination.height + speed);
                break;
            case Config.DIR_RIGHT: destination.setSize(destination.width + speed, destination.height);
                break;
            case Config.DIR_DOWN: destination.setSize(destination.width, destination.height - speed);
                break;
            case Config.DIR_LEFT: destination.setSize(destination.width - speed, destination.height);
                break;
        }

        arena.takeEffect(this, destination);

        LogHelper.ret("move() returned with: void;");
    }

    public void clearEffects(){
        LogHelper.call("clearEffects(); Robot; " + name + ";");
        paralyzed = false;
        slowed = false;
        LogHelper.ret("clearEffects() returned with: void;");
    }

    //Robotra ragacs hat
    public void stuck() {
        LogHelper.call("stuck(); Robot; " + name + ";");
        slowed = true;
        speed *= 0.5;
        paralyzed = false;
        LogHelper.ret("stuck() returned with: void");
    }

    //Robotra olajfolt hat
    public void slipping() {
        LogHelper.call("slipping(); Robot; " + name + ";");
        paralyzed = true;
        slowed = false;
        LogHelper.ret("sipping() returned with: void;");
    }

    //Robot megállítása (pl fallal ütközés esetén)
    public void stop(){
        LogHelper.call("stop(); Robot; " + name + ";");
        speed = 0;
        paralyzed = false; // muszáj feloldani mert irányváltás és sebességnövelés nélkül nem tudnál elmozdulni onnan.
        LogHelper.ret("stop() returned with: void;");
    }

    //Robot nevének lekérdezése
    public String getName() {
        //LogHelper.call("getName(); Robot; " + name + ";");
        //LogHelper.ret("getName() returned with: " + name + ";");
        return name;
    }

    //Robot nevének beállítása
    public void setName(String name) {
        LogHelper.call("setName(" + name + "); Robot; " + this.name + ";");
        this.name = name;
        LogHelper.ret("setName(" + name + ") returned with: void;");
    }
}
