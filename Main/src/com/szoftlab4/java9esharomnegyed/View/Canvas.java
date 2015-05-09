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

    Image cleanerRobot;

    public Canvas(GUI parent) {
        this.parent = parent;
        this.setSize(Config.FRAME_SIZE);
        this.addKeyListener(new keyAction());

        cleanerRobot = getToolkit().getImage(getClass().getResource(Config.CLEANER_ROBOT));
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

        /* observer tervezési minta */
        Arena temp = Game.getArena();
        Robot blueRobot = temp.getRobot(0);
        Robot redRobot = temp.getRobot(1);
        cleaners = temp.getCleanersList();
        obstacles = temp.getObstacleList();
        walls = temp.getWallList();


        /*g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(255, 0, 0));
        for(int i = 0; i <Config.FRAME_SIZE.width-5;i+=Config.TILE_SIZE){
            for(int j = 0; j <Config.FRAME_SIZE.height-5;j+=Config.TILE_SIZE){
                g2.drawRect(i,j,Config.TILE_SIZE,Config.TILE_SIZE);
            }
        }*/

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
        g2.setColor(new Color(255, 0, 0));
        int padding = 10;
        g2.fillRect(padding, Game.getArena().getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;

        //Robot1 nevének kiírása
        g2.drawString(Game.getArena().getRobot(0).getName(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(Game.getArena().getRobot(0).getName() + 10);
        //Robot1 ragacskészlet kiírása
        g2.drawString("Putty left: " + Game.getArena().getRobot(0).getPuttyLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty left: " + Game.getArena().getRobot(0).getPuttyLeft() + 10);
        //Robot1 olajkészlet kiírása
        g2.drawString("Oil left: " + Game.getArena().getRobot(0).getOilLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Oil left: " + Game.getArena().getRobot(0).getOilLeft() + 10 + 30);
        g2.setColor(new Color(0, 0, 255));
        g2.fillRect(padding, Game.getArena().getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;

        //Robot2 nevének kiírása
        g2.drawString(Game.getArena().getRobot(1).getName(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(Game.getArena().getRobot(1).getName() + 10);
        //Robot2 ragacskészlet kiírása
        g2.drawString("Putty left: " + Game.getArena().getRobot(1).getPuttyLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty left: " + Game.getArena().getRobot(1).getPuttyLeft() + 10);
        //Robot2 olajkészlet kiírása
        g2.drawString("Oil left: " + Game.getArena().getRobot(1).getOilLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Oil left: " + Game.getArena().getRobot(1).getOilLeft() + 10);
        g2.setColor(new Color(0, 0, 0));

        //Hátralévő idő kiírása
        g2.drawString("Time left: " + Game.getTime(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));

        //Game over felirat kirajzolása
        if(Game.isGameOver()){
            g2.setColor(new Color(255, 0, 0));
            Font f2 = new Font("Arial", Font.CENTER_BASELINE, 50);
            g2.setFont(f2);
            FontMetrics metrics2 = g2.getFontMetrics(f2);
            g2.drawString("Game over!", (Game.getArena().getSize().width/2) - (metrics2.stringWidth("Game over!")/2),
                    Game.getArena().getSize().height/2);
        }

    }

    //----------------------------------------Key-listeners----------------------------------------
    private class keyAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Game.pauseGame();
                parent.loadPausePanel();
            }
        }
    }
}
