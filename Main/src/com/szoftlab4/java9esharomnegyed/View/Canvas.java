package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;
import com.szoftlab4.java9esharomnegyed.Obstacle;
import com.szoftlab4.java9esharomnegyed.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Ricsard on 2015.05.08..
 */
public class Canvas extends java.awt.Canvas {

    Image blue;
    Image red;
    Image cleanerRobot;

    public Canvas() {

        this.setSize(Config.FRAME_SIZE);

        blue = getToolkit().getImage(getClass().getResource(Config.BLUE)).getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
        red = getToolkit().getImage(getClass().getResource(Config.RED)).getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
        cleanerRobot = getToolkit().getImage(getClass().getResource(Config.CLEANER_ROBOT));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(255, 0, 0));
        for(int i = 0; i <Config.FRAME_SIZE.width-5;i+=Config.TILE_SIZE){
            for(int j = 0; j <Config.FRAME_SIZE.height-5;j+=Config.TILE_SIZE){
                g2.drawRect(i,j,Config.TILE_SIZE,Config.TILE_SIZE);
            }
        }

        //Kék robot kirajozlása
        g2.drawImage(blue, Game.getArena().getRobot(0).getPosition().width-(Config.TILE_SIZE/2),
                Game.getArena().getRobot(0).getPosition().height-(Config.TILE_SIZE/2), null);

        //Piros robot kirajozlása
        g2.drawImage(red, Game.getArena().getRobot(1).getPosition().width-(Config.TILE_SIZE/2),
                Game.getArena().getRobot(1).getPosition().height-(Config.TILE_SIZE/2), null);

        //Falak kirajozlása
        for(Wall w : Game.getArena().getWallList()){
            g2.drawImage(w.getImage(), w.getPosition().width-(Config.TILE_SIZE/2),
                    w.getPosition().height-(Config.TILE_SIZE/2), null);
        }

        //Olaj és Ragacs foltok kirajzolása
        for(Obstacle o : Game.getArena().getObstacleList()){
            g2.drawImage(o.getImage(), o.getPosition().width-(Config.TILE_SIZE/2),
                    o.getPosition().height-(Config.TILE_SIZE/2), null);
        }

        //Game over felirat kirajzolása
        if(Game.isGameOver()){
            g2.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            g2.drawString("Game over!", (Game.getArena().getSize().width) / 2,
                    Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        }

    }
}
