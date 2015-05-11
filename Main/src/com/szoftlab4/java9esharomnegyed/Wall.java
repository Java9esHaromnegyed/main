package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

//Egy fajta Obstacle
public class Wall extends Obstacle {

    //Fal létrehozása adott pozícióra
    public Wall(Dimension pos){
        position = pos;
        InputStream is = this.getClass().getResourceAsStream(Config.WALL);
        Image temp = null;
        try {
            temp = ImageIO.read(is);
            image = temp.getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Absztrakt metódus megvalósítása az akadály hatásához
    //Falnál nem használt, csak Falnál
    @Override
    public void effect(Robot robot) {
    }

    //Falnál nem használt, csak Olajnál
    @Override
    public void age() {}

    @Override
    public void collide(Robot robot) {
        //Roboton megfelelő hatást kiváltó fgv meghívása
        robot.stepBack(this.position);
        robot.stop();
    }

    //Adott fal adatainak kiírása Stringbe
    @Override
    public String toString() {
        return "Wall(" + position.width + ", " + position.height + ")";
    }
}
