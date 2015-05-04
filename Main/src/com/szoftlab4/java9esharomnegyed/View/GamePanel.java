package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.swing.*;
import java.awt.*;
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
    JLabel time;

    private GUI parent;

    public GamePanel(GUI p){
        super();
        parent = p;

        this.addKeyListener(new keyAction());
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

        if (one != null && !(one.getText().equals(temp.getRobot(0).getName()))) {
            this.remove(one);
            one = new JLabel(temp.getRobot(0).getName());
            this.add(one);
            this.invalidate();
        }

        if (two != null && !(two.getText().equals(temp.getRobot(1).getName()))) {
            this.remove(two);
            two = new JLabel(temp.getRobot(1).getName());
            this.add(two);
            this.invalidate();
        }

        if(time != null && !(time.getText().equals("" + Game.getTime()))){
            this.remove(time);
            time = new JLabel("" + Game.getTime());
            this.add(time);
        }
    }

    private void initLayout(){
        one = new JLabel(Game.getArena().getRobot(0).getName());
        two = new JLabel(Game.getArena().getRobot(1).getName());
        time = new JLabel("" + Game.getTime());
        this.add(one);
        this.add(two);
        this.add(time);
    }

    //----------------------------------------Key-listeners----------------------------------------
    private class keyAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Game.pauseGame();
                parent.loadPausePanel();
            } else
                Game.getArena().movementControl(e);
        }
    }
}
