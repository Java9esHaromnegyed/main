package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class GamePanel extends JPanel {
    private Canvas playGround;
    private Canvas statusCanvas;

    private List<Robot> robots;
    private List<CleanerRobot> cleaners;
    private List<Obstacle> obstacles;
    private List<Wall> walls;

    private GUI parent;

    public GamePanel(GUI p){
        super();
        parent = p;

        update();

        this.addKeyListener(new escAction());
        this.setFocusable(true);
    }

    public void update(){
        Arena temp = Game.getArena();
        robots = temp.getRobotList();
        cleaners = temp.getCleanersList();
        obstacles = temp.getObstacleList();
        walls = temp.getWallList();

        this.invalidate();
    }

    //----------------------------------------Key-listeners----------------------------------------
    private class escAction extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                parent.loadPausePanel();
        }
    }
}
