package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

//Osztály a robotokhoz
public class Robot extends AbstractRobot {

    private int puttyLeft = Config.PUTTY_TANK;
    private int oilLeft = Config.OIL_TANK;
    private double coveredDistance = 0;
    boolean paralyzed = false;                  // this flag gets true while robot stands on oil
    boolean slowed = false;                     // this flag gets true while robot stands on putty

    //Robot létrehozása egy arénához adott névvel, kezdő pozícióval és iránnyal.
    public Robot(Arena a, String n, Dimension startPos, int dir, int ID) {
        arena = a;
        name = n;
        position = startPos;
        direction = dir;
        id = ID;
    }

    //Ragacsfolt lehelyezése a pályára
    public void dropPutty() {
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead && !paralyzed)
            arena.addObstacle(new PuttySpot(position));
    }

    //Olajfolt lehelyezése a pályára
    public void dropOil() {
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead && !paralyzed)
            arena.addObstacle(new OilSpot(position));
    }

    //Robot által megtett távolság lekérdezése
    public double getCoveredDistance() {
        coveredDistance = position.width;                   // lineáris pályán a megtett táv egyezik az x koordiátával
        return coveredDistance;
    }

    //Robotnak balra fordulás utasítás adása
    public void turnLeft() {
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead && !paralyzed)
            direction++;
        if (direction > Config.DIR_LEFT)     // when direction reached 4 we have to change it to 0.  direction only goes from 0 to 3
            direction = Config.DIR_UP;
    }

    //Robotnak jobbra fordulás utasítás adása
    public void turnRight(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!paralyzed)
            direction--;
        if(direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
            direction = Config.DIR_LEFT;
    }

    //A robot halálakor bekövetkező esemény
    @Override
    public void die() {
        dead = true;
    }

    //Robot gyorsítása
    public void speedUp(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!paralyzed || speed == 0)
            //Csak ha nem lépi túl a max. sebességet
            if(speed + Config.SPD_UNIT < Config.SPD_LIMIT)  // speed only goes from 0 to Config.SPD_LIMIT
                speed += Config.SPD_UNIT;                       // with Config.SPD_UNIT steps
            else
                speed = Config.SPD_LIMIT;
    }

    //Robot lassítása
    public void slowDown(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!paralyzed)
            //Csak ha nem csökken 1-nél lentebb a sebessége
            if(speed - Config.SPD_UNIT > 0)
                speed += Config.SPD_UNIT;
            else
                speed = 0;
    }

    //Robot mozgatása
    @Override
    public void move() {
        Dimension destination = position;
        //Csak akkor tehető meg ha nem halott
        if(!dead) {
            switch (direction) {
                case Config.DIR_UP:
                    destination.setSize(destination.width, destination.height + speed);
                    break;
                case Config.DIR_RIGHT:
                    destination.setSize(destination.width + speed, destination.height);
                    break;
                case Config.DIR_DOWN:
                    destination.setSize(destination.width, destination.height - speed);
                    break;
                case Config.DIR_LEFT:
                    destination.setSize(destination.width - speed, destination.height);
                    break;
            }

            arena.takeEffect(this, destination);
        }
    }

    public void clearEffects(){
        paralyzed = false;
        slowed = false;
    }

    //Robotra ragacs hat
    public void stuck() {
        slowed = true;
        speed *= 0.5;
        paralyzed = false;
    }

    //Robotra olajfolt hat
    public void slipping() {
        paralyzed = true;
        slowed = false;
    }

    //Robot megállítása (pl fallal ütközés esetén)
    public void stop(){
        speed = 0;
        paralyzed = false; // muszáj feloldani mert irányváltás és sebességnövelés nélkül nem tudnál elmozdulni onnan.
    }
}
