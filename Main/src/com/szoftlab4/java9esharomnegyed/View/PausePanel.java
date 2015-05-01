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

    public PausePanel(GUI p){
        //TODO
        parent = p;

        resumeButton = new JButton("Resume Game");
        rematchButton = new JButton("Rematch");
        leaveButton =  new JButton("Leave Game");

        resumeButton.addActionListener(new resumeAction());
        rematchButton.addActionListener(new rematchAction());
        leaveButton.addActionListener(new leaveAction());

        initLayout();
    }

    //----------------------------------------Init-------------------------------------------------
    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 4);

        this.add(resumeButton);
        resumeButton.setSize(buttonSize);
        resumeButton.setLocation(place);

        this.add(rematchButton);
        rematchButton.setSize(buttonSize);
        rematchButton.setLocation(place.x, place.y + buttonSize.height + 10);

        this.add(leaveButton);
        leaveButton.setSize(buttonSize);
        leaveButton.setLocation(place.x, place.y + (buttonSize.height + 10) * 2);

    }


    //----------------------------------------Button-listeners-------------------------------------
    public void resumeButtonFunction(){
        Game.resumeGame();
    }

    private class resumeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            resumeButtonFunction();
        }
    }

    public void rematchButtonFunction(){
        Game.rematch();
        parent.loadGamePanel();
    }

    private class rematchAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            rematchButtonFunction();
        }
    }

    public void leaveButtonFunction(){
        parent.loadMenuPanel();
    }

    private class leaveAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave this game?",
                    "Confirm Leaving", JOptionPane.YES_NO_OPTION);
            if(i == 0) {
                leaveButtonFunction();
            }
        }
    }
}
