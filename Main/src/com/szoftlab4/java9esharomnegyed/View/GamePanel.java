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

    JLabel one;
    JLabel two;

    private GUI parent;

    public GamePanel(GUI p){
        super();
        parent = p;

        this.addKeyListener(new escAction());
        this.setFocusable(true);
        initLayout();
        invalidate();
    }

    public void update() {
        Arena temp = Game.getArena();
        robots = temp.getRobotList();
        cleaners = temp.getCleanersList();
        obstacles = temp.getObstacleList();
        walls = temp.getWallList();

        if (one != null && one.getText() != temp.getRobot(0).getName()) {
            this.remove(one);
            one = new JLabel(temp.getRobot(0).getName());
            this.add(one);
        }

        if (two != null && two.getText() != temp.getRobot(1).getName()) {
            this.remove(two);
            two = new JLabel(temp.getRobot(1).getName());
            this.add(two);
        }

        this.invalidate();
    }

    private void initLayout(){
        one = new JLabel(Game.getArena().getRobot(0).getName());
        two = new JLabel(Game.getArena().getRobot(1).getName());
        this.add(one);
        this.add(two);
    }

    //----------------------------------------Key-listeners----------------------------------------
    private class escAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                parent.loadPausePanel();
        }
    }
}
