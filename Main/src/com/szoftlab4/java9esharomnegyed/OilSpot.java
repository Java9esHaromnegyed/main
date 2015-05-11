package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

//Egy fajta Obstacle
public class OilSpot extends Obstacle {

    //Olajfolt létrehozása adott pozícióra
    public OilSpot(Dimension pos){
        position = pos;

        InputStream is = this.getClass().getResourceAsStream(Config.OIL);
        Image temp = null;
        try {
            temp = ImageIO.read(is);
            image = temp.getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    @Override
    public void effect(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.slipping();
    }

    @Override
    public void age(){
        age++; //Olajfolt öregítése
        //LogHelper.inline("oilAged atPos: [" + position.width + "; " + position.height + "] age: " + age);
    }

    //Nem használt, csak Falnál
    @Override
    public void collide(Robot robot) {}

    //Adott Olajfolt adatainak kiírása Stringbe
    @Override
    public String toString() {
        return "OilSpot(" + position.width + ", " + position.height + ")";
    }
}
