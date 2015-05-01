package com.szoftlab4.java9esharomnegyed.View;


import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel {
    private GUI parent;
    private JButton resumeButton;
    private JButton rematchButton;
    private JButton leaveButton;


    //--------------Button listeners---------
    public void resumeButtonFunction(){
        Game.resumeGame();
    }

    private class ResumeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            resumeButtonFunction();
        }
    }

    public void rematchButtonFunction(){
        Game.rematch();
        parent.loadGamePanel();
    }

    private class RematchAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            rematchButtonFunction();
        }
    }

    public void leaveButtonFunction(){
        parent.loadMenuPanel();
    }

    private class LeaveAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          leaveButtonFunction();
        }
    }

    //---Constructor---
    public PausePanel(GUI p){
        //TODO
        parent = p;
        resumeButton = new JButton("Resume Game");
        rematchButton = new JButton(("Rematch"));
        leaveButton =  new JButton("Leave Game");

        resumeButton.addActionListener(new ResumeAction());
        rematchButton.addActionListener(new RematchAction());
        leaveButton.addActionListener(new LeaveAction());

        initLayout();
    }

    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);

        this.add(resumeButton);
        resumeButton.setSize(buttonSize);
        resumeButton.setLocation(place);

        this.add(rematchButton);
        rematchButton.setSize(buttonSize);
        rematchButton.setLocation(place.x, place.y + buttonSize.height + 10);

        this.add(leaveButton);
        leaveButton.setSize(buttonSize);
        leaveButton.setLocation(place.x, place.y*7);

    }

}
