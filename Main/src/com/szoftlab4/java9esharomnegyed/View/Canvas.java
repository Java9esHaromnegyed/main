package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class Canvas extends java.awt.Canvas {
    private GUI parent;
    private List<CleanerRobot> cleaners;
    private List<Obstacle> obstacles;
    private List<Wall> walls;

    //Konstruktor
    public Canvas(GUI parent) {
        super();
        this.parent = parent;
        this.setSize(Config.FRAME_SIZE.width, Config.FRAME_SIZE.height);
        this.addKeyListener(new keyAction());
    }

    //Turning the robots image in the direction of robot movement
    private Image rotateRobot(Image image, AbstractRobot robot)
    {
        int width = Config.TILE_SIZE;
        int height = Config.TILE_SIZE;
        int angle = (2-robot.getDirection())*90;
        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        double radians = Math.toRadians(angle);
        BufferedImage result = new BufferedImage(width, height,bimage.getType());
        Graphics2D g = result.createGraphics();
        g.rotate(radians, width / 2, height / 2);
        g.drawRenderedImage(bimage, null);

        return result;
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // observer tervezési minta
        Arena tempArena = Game.getArena();
        Robot blueRobot = tempArena.getRobot(0);
        Robot redRobot = tempArena.getRobot(1);
        cleaners = tempArena.getCleanersList();
        obstacles = tempArena.getObstacleList();
        walls = tempArena.getWallList();

        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(0, 0, 0));
        g2.drawRect(0, 0, tempArena.getSize().width, tempArena.getSize().height);


        //Falak kirajozlása
        for(Wall w : walls){
            g2.drawImage(w.getImage(), w.getPosition().width-(Config.TILE_SIZE/2),
                    w.getPosition().height-(Config.TILE_SIZE/2), null);
        }

        //Olaj és Ragacs foltok kirajzolása
        for(Obstacle o : obstacles){
            g2.drawImage(o.getImage(), o.getPosition().width-(Config.TILE_SIZE/2),
                    o.getPosition().height-(Config.TILE_SIZE/2), null);
        }

        //Cleaner robotok kirajzolása
        for(CleanerRobot c : cleaners){
            g2.drawImage(rotateRobot(c.getImage(), c), c.getPosition().width-(Config.TILE_SIZE/2),
                    c.getPosition().height-(Config.TILE_SIZE/2), null);
        }

        //Kék robot kirajozlása
        if(!blueRobot.dead)
            g2.drawImage(rotateRobot(blueRobot.getBlue(), blueRobot), blueRobot.getPosition().width-(Config.TILE_SIZE/2),
                    blueRobot.getPosition().height-(Config.TILE_SIZE/2), null);


        //Piros robot kirajozlása
        if(!redRobot.dead)
            g2.drawImage(rotateRobot(redRobot.getRed(), redRobot), redRobot.getPosition().width-(Config.TILE_SIZE/2),
                    redRobot.getPosition().height-(Config.TILE_SIZE/2), null);


        //HUD kirajzolása
        Font f = new Font("Arial", Font.PLAIN, 14);
        g2.setFont(f);
        FontMetrics metrics = g2.getFontMetrics(f);
        g2.setColor(new Color(0, 0, 255));
        int padding = 10;
        g2.fillRect(padding, tempArena.getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;


        //Robot1 nevének kiírása
        String name = blueRobot.getName();
        if(name.length() > 11)
            name = name.substring(0, 10) + "..";
        g2.drawString(name, padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(name + 10);
        //Robot1 ragacskészlet kiírása
        g2.drawString("Putty: " + blueRobot.getPuttyLeft(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty: " + blueRobot.getPuttyLeft() + 10);
        //Robot1 olajkészlet kiírása
        g2.drawString("Oil: " + blueRobot.getOilLeft(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Oil: " + blueRobot.getOilLeft() + 10);
        // Robot1 sebesség kiírása
        g2.drawString("Speed: " + blueRobot.getSpeed(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Speed: " + blueRobot.getSpeed() + 10 + 10);

        g2.setColor(new Color(255, 0, 0));
        g2.fillRect(padding, tempArena.getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;

        //Robot2 nevének kiírása
        name = redRobot.getName();
        if(name.length() > 11)
            name = name.substring(0, 10) + "..";
        g2.drawString(name, padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(name + 10);
        //Robot2 ragacskészlet kiírása
        g2.drawString("Putty: " + redRobot.getPuttyLeft(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty: " + redRobot.getPuttyLeft() + 10);
        //Robot2 olajkészlet kiírása
        g2.drawString("Oil: " + redRobot.getOilLeft(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Oil: " + redRobot.getOilLeft() + 10);
        //Robot2 sebesség kiírása
        g2.drawString("Speed: " + redRobot.getSpeed(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE/2));
        //padding += metrics.stringWidth("Speed: " + redRobot.getSpeed() + 10);
        g2.setColor(new Color(0, 0, 0));

        //Hátralévő idő kiírása
        g2.drawString("Time left: " + Game.getTime(), padding,
                tempArena.getSize().height + (Config.TILE_SIZE));

        //Game over felirat kirajzolása
        if(Game.isGameOver()){
            g2.setColor(new Color(255, 0, 0));
            Font f2 = new Font("Arial", Font.CENTER_BASELINE, 50);
            g2.setFont(f2);
            FontMetrics metrics2 = g2.getFontMetrics(f2);
            g2.drawString("Game over!", (tempArena.getSize().width/2) - (metrics2.stringWidth("Game over!")/2),
                    tempArena.getSize().height/2);
        }

    }

    //----------------------------------------Key-listeners----------------------------------------
    //ESC gomb megnyomásakor (felengedésekor) szüneteltetjük a játékot és megjelenik a Pause Menu
    private class keyAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Game.pauseGame();
                parent.loadPausePanel();
            }
        }
    }
}
