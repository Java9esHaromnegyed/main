package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Arena;
import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameInitPanel extends JPanel {
    GUI parent;
    Arena subject;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JButton doneButton;

    GameInitPanel(GUI p){
        parent = p;
        subject = Game.getArena();

        doneButton = new JButton("Done");
        doneButton.addActionListener(new doneAction());

        playerOneName = new JTextField("playerOne");
        playerTwoName = new JTextField("playerTwo");

        initLayout();
    }

    //----------------------------------------Init-------------------------------------------------
    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Dimension textFieldSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);


        this.add(playerOneName);
        playerOneName.setSize(textFieldSize);
        playerTwoName.setLocation(place);

        this.add(playerTwoName);
        playerTwoName.setSize(textFieldSize);
        playerTwoName.setLocation(place.x, place.y + textFieldSize.height + 10);

        this.add(doneButton);
        doneButton.setSize(buttonSize);
        doneButton.setLocation(place.x, place.y*7);
    }

    //----------------------------------------Button-listeners-------------------------------------

    //Új játékot indítunk, a megadott nevekkel elnevezve a robotokat
    public void doneButtonFunction(){
        try {
            subject.loadMap(Config.DEF_MAP);
            subject.initArena();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Game.newGame(playerOneName.getText(), playerTwoName.getText());
        parent.loadGamePanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust
    private class doneAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            doneButtonFunction();
        }
    }

    public void backButtonFunction(){
        parent.loadMenuPanel();
    }

    private class backAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            backButtonFunction();
        }
    }
}
