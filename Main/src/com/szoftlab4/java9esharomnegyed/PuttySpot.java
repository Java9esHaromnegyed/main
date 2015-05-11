package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

//Egy fajta Obstacle
public class PuttySpot extends Obstacle {

    //Ragacsfolt létrehozása adott pozícióra
    public PuttySpot(Dimension pos){
        position = pos;

        InputStream is = this.getClass().getResourceAsStream(Config.PUTTY);
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
        robot.stuck();
        decay++; //ragacs öregítése
        //LogHelper.inline("puttyDecayed atPos: [" + position.width + "; " + position.height + "] decay: " + decay);
    }

    //Csak Olajfoltnál használt
    @Override
    public void age() {}

    //Nem használt, csak Falnál
    @Override
    public void collide(Robot robot) {}

    //Adott Ragacsfolt adatait kiírja Stringbe
    @Override
    public String toString() {
        return "PuttySpot(" + position.width + ", " + position.height + ")";
    }


}
