package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInitPanel extends JPanel {
    GUI parent;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JButton doneButton;


    //------Button listeners------
    //Új játékot indítunk, a megadott nevekkel elnevezve a robotokat
    public void doneButtonFunction(){
        Game.newGame(playerOneName.getText(), playerTwoName.getText());
        parent.loadGamePanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust
    private class backAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            doneButtonFunction();
        }
    }

    GameInitPanel(GUI p){
        //TODO
        parent = p;
        doneButton = new JButton("Back");
        doneButton.addActionListener(new backAction());

        initLayout();

    }

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
}
