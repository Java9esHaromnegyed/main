package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;
import com.szoftlab4.java9esharomnegyed.Obstacle;
import com.szoftlab4.java9esharomnegyed.Wall;
import com.szoftlab4.java9esharomnegyed.Robot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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

    private Image rotateRobot(Image image, Robot robot)
    {
        int angle =(2-robot.getDirection())*90;
        BufferedImage bimage = new BufferedImage(Config.TILE_SIZE, Config.TILE_SIZE, BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        double radians = Math.toRadians(angle);

        int srcWidth = Config.TILE_SIZE;
        int srcHeight = Config.TILE_SIZE;

        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
        int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

        BufferedImage result = new BufferedImage(newWidth, newHeight,
                bimage.getType());
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
        g.rotate(radians, srcWidth / 2, srcHeight / 2);
        g.drawRenderedImage(bimage, null);

        return result;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        /*g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(255, 0, 0));
        for(int i = 0; i <Config.FRAME_SIZE.width-5;i+=Config.TILE_SIZE){
            for(int j = 0; j <Config.FRAME_SIZE.height-5;j+=Config.TILE_SIZE){
                g2.drawRect(i,j,Config.TILE_SIZE,Config.TILE_SIZE);
            }
        }*/

        //Kék robot kirajozlása
        g2.drawImage(rotateRobot(blue,Game.getArena().getRobot(0)), Game.getArena().getRobot(0).getPosition().width-(Config.TILE_SIZE/2),
                Game.getArena().getRobot(0).getPosition().height-(Config.TILE_SIZE/2), null);


        //Piros robot kirajozlása
        g2.drawImage(rotateRobot(red,Game.getArena().getRobot(1)), Game.getArena().getRobot(1).getPosition().width-(Config.TILE_SIZE/2),
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

        Font f = new Font("Arial", Font.PLAIN, 14);
        g2.setFont(f);
        FontMetrics metrics = g2.getFontMetrics(f);
        g2.setColor(new Color(255, 0, 0));
        int padding = 10;
        g2.fillRect(padding, Game.getArena().getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;
        g2.drawString(Game.getArena().getRobot(0).getName(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(Game.getArena().getRobot(0).getName() + 10);
        g2.drawString("Putty left: " + Game.getArena().getRobot(0).getPuttyLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty left: " + Game.getArena().getRobot(0).getPuttyLeft() + 10);
        g2.drawString("Oil left: " + Game.getArena().getRobot(0).getOilLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));

        padding += metrics.stringWidth("Oil left: " + Game.getArena().getRobot(0).getOilLeft() + 10 + 30);
        g2.setColor(new Color(0, 0, 255));
        g2.fillRect(padding, Game.getArena().getSize().height + (Config.TILE_SIZE/2) - (Config.TILE_SIZE/2)/2, (Config.TILE_SIZE/2), (Config.TILE_SIZE/2));
        padding += (Config.TILE_SIZE/2) + 10;
        g2.drawString(Game.getArena().getRobot(1).getName(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth(Game.getArena().getRobot(1).getName() + 10);
        g2.drawString("Putty left: " + Game.getArena().getRobot(1).getPuttyLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Putty left: " + Game.getArena().getRobot(1).getPuttyLeft() + 10);
        g2.drawString("Oil left: " + Game.getArena().getRobot(1).getOilLeft(), padding,
                Game.getArena().getSize().height + (Config.TILE_SIZE/2));
        padding += metrics.stringWidth("Oil left: " + Game.getArena().getRobot(1).getOilLeft() + 10);
        g2.setColor(new Color(0, 0, 0));
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
}
