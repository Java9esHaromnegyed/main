package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.swing.*;
import java.awt.*;

//Osztály a robotokhoz
public class Robot extends AbstractRobot {

    private int puttyLeft = Config.PUTTY_TANK;
    private int oilLeft = Config.OIL_TANK;
    private double coveredDistance = 0;
    boolean paralyzed = false;                  // this flag gets true while robot stands on oil
    boolean slowed = false;                     // this flag gets true while robot stands on putty

    private Image blue;
    private Image red;

    //Robot létrehozása egy arénához adott névvel, kezdő pozícióval és iránnyal.
    public Robot(Arena a, String n, Dimension startPos, int dir, int ID) {
        arena = a;
        name = n;
        position = startPos;
        direction = dir;
        id = ID;

        blue = new JPanel().getToolkit().getImage(getClass().getResource(Config.BLUE)).getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
        red = new JPanel().getToolkit().getImage(getClass().getResource(Config.RED)).getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
    }

    //Ragacsfolt lehelyezése a pályára
    public void dropPutty() {
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead && !paralyzed) {
            //Ha van még ragacs a készleten lerakjuk, egyébként jelezzük, hogy kiürült
            if(puttyLeft != 0){
                arena.addObstacle(new PuttySpot(position));
                puttyLeft--;  //csökkentyük a putty készletet 1-el
                //LogHelper.inline("puttyDropped fromId: "+id+" pos: "+"["+position.width+"; "+position.height+"]");
            }
            else{
                //LogHelper.inline("#PUTTY TANK IS EMPTY");
            }
        }
    }

    //Olajfolt lehelyezése a pályára
    public void dropOil() {
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!dead && !paralyzed){
            //Ha van még olaj a készleten lerakjuk, egyébként jelezzük, hogy kiürült
            if(oilLeft != 0){
                arena.addObstacle(new OilSpot(position));
                oilLeft--; //csökkentyük az olaj készletet 1-el
                //LogHelper.inline("oilDropped fromId: "+id+" pos: "+"["+position.width+"; "+position.height+"]");
            }
            else{
                //LogHelper.inline("#OIL TANK IS EMPTY");
            }
        }
    }

    //Robot által megtett távolság lekérdezése
    public double getCoveredDistance() {
        coveredDistance = position.width;                   // lineáris pályán a megtett táv egyezik az x koordiátával
        return coveredDistance;
    }

    //Robotnak balra fordulás utasítás adása
    public void turnLeft() {
        //Ha nem paralyzed meghívjuk az ősosztály turnLeft-jét
        if(!paralyzed) {
            super.turnLeft();

            String temp = "Up";
            if(direction == Config.DIR_RIGHT)
                temp = "Right";
            else if(direction == Config.DIR_DOWN)
                temp = "Down";
            else if(direction == Config.DIR_LEFT)
                temp = "Left";

            //LogHelper.inline("robotTurnedLeft id: " + id + " facing: " + temp);
        }
        //Amúgy semmit sem csinálunk
    }

    //Robotnak jobbra fordulás utasítás adása
    public void turnRight(){
        //Ha nem paralyzed meghívjuk az ősosztály turnRight-ját
        if(!paralyzed) {
            super.turnRight();

            String temp = "Up " + direction;
            if(direction == Config.DIR_RIGHT)
                temp = "Right";
            else if(direction == Config.DIR_DOWN)
                temp = "Down";
            else if(direction == Config.DIR_LEFT)
                temp = "Left";

            //LogHelper.inline("robotTurnedRight id: " + id + " facing: " + temp);
        }
        //Amúgy semmit sem csinálunk
    }

    @Override
    public void die() {
        //ha meghalt, vele nem foglalkozunk
        dead = true;
        //LogHelper.inline("robotDied id: " + id);
    }

    //Robot gyorsítása
    public void speedUp(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!paralyzed && !dead && !slowed) {
            //Csak ha nem lépi túl a max. sebességet
            if ((speed + Config.SPD_UNIT) < Config.SPD_LIMIT)  // speed only goes from 0 to Config.SPD_LIMIT
                speed += Config.SPD_UNIT;                       // with Config.SPD_UNIT steps
            else
                speed = Config.SPD_LIMIT;
            //LogHelper.inline("robotSpeedUp id: " + id + " speed: " + speed);
        }
        //Amúgy semmit sem csinálunk
    }

    //Robot lassítása
    public void slowDown(){
        //Csak akkor tehető meg ha épp nem blokkolja valamilyen hatás és nem halott
        if(!paralyzed && !dead && !slowed) {
            //Csak ha nem csökken 1-nél lentebb a sebessége
            if ((speed - Config.SPD_UNIT) > 0)
                speed -= Config.SPD_UNIT;
            //Egyébként a lehető legkisebb lesz, tehát 1
            else
                speed = 1;
            //LogHelper.inline("robotSlowedDown id: " + id + " speed: " + speed);
        }
        //Amúgy semmit sem csinálunk
    }

    //Robot mozgatása
    @Override
    public void move() {
        // új változó felvétele szükséges, mert a belső position változó kell a rajzoláshoz
        Dimension destination = new Dimension(position);
        //Csak akkor tehető meg ha nem halott
        if(!dead) {
            switch (direction) {
                case Config.DIR_UP:
                    destination.setSize(destination.width, destination.height + speed * Config.TILE_SIZE);
                    break;
                case Config.DIR_RIGHT:
                    destination.setSize(destination.width + speed * Config.TILE_SIZE, destination.height);
                    break;
                case Config.DIR_DOWN:
                    destination.setSize(destination.width, destination.height - speed * Config.TILE_SIZE);
                    break;
                case Config.DIR_LEFT:
                    destination.setSize(destination.width - speed * Config.TILE_SIZE, destination.height);
                    break;
            }


            // majd a takeEffect fogja a pályának megfelelően eldönteni hogy a destination valid pozíció
            // és ha odaléptünk milyen hatás érvényesüljön a robotra, addig a robot jelenlegi pozíciója marad.
            arena.takeEffect(this, destination);
        }
    }

    //Effektek hatását leveszi a robotról
    public void clearEffects(){
        paralyzed = false;
        slowed = false;
    }

    //Robotra ragacs hat
    public void stuck() {
        speed *= 0.5; //sebesség feleződik
        slowed = true;
        //LogHelper.inline("robotSlowedDown id: " + id + " speed: " + speed);
    }

    //Robotra olajfolt hat
    public void slipping() {
        paralyzed = true;
        //LogHelper.inline("robotSlipped id: " + id);
    }

    //Robot megállítása (pl fallal ütközés esetén)
    public void stop(){
        speed = 0; //robot megállítása
        paralyzed = true; // muszáj feloldani mert irányváltás és sebességnövelés nélkül nem tudnál elmozdulni onnan. !?
        slowed = false; //Jófejségből ha voltál olyan szerencsétlen hogy falnak mentél akkor ezt is levesszük
        //LogHelper.inline("robotStopped "+"id: "+id+" speed: "+speed);
    }

    //Falba ütközéskor a robotot a fal előtti pozícióba állítja
    public void stepBack(Dimension wall){
        switch (direction) {
            case Config.DIR_UP:
                position.setSize(wall.width, wall.height - Config.TILE_SIZE);
                break;
            case Config.DIR_RIGHT:
                position.setSize(wall.width - Config.TILE_SIZE, wall.height);
                break;
            case Config.DIR_DOWN:
                position.setSize(wall.width, wall.height + Config.TILE_SIZE);
                break;
            case Config.DIR_LEFT:
                position.setSize(wall.width + Config.TILE_SIZE, wall.height);
                break;
        }
    }

    public int getPuttyLeft() {
        return puttyLeft;
    }

    public int getOilLeft() {
        return oilLeft;
    }

    public Image getBlue(){
        return blue;
    }

    public Image getRed(){
        return red;
    }
}
