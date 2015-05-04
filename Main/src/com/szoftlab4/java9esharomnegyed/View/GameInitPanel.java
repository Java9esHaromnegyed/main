package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Arena;
import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameInitPanel extends JPanel {
    GUI parent;
    Arena subject;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JButton doneButton;
    private JButton backButton;

    GameInitPanel(GUI p){
        parent = p;
        subject = Game.getArena();

        doneButton = new JButton("Done");
        doneButton.addActionListener(new doneAction());

        backButton = new JButton("Back");
        backButton.addActionListener(new backAction());

        playerOneName = new JTextField("Player One");
        playerOneName.addKeyListener(new keyAction());
        playerTwoName = new JTextField("Player Two");
        playerTwoName.addKeyListener(new keyAction());

        initLayout();
    }

    public void setToDefault(){
        playerOneName = new JTextField("Player One");
        playerTwoName = new JTextField("Player Two");
        invalidate();
    }

    //----------------------------------------Init-------------------------------------------------
    //Megfelelő elrendezés beállítása
    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Dimension textFieldSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 15);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);


        this.add(playerOneName);
        playerOneName.setSize(textFieldSize);
        playerOneName.setLocation(place);

        this.add(playerTwoName);
        playerTwoName.setSize(textFieldSize);
        playerTwoName.setLocation(place.x, place.y + textFieldSize.height + 10);

        this.add(doneButton);
        doneButton.setSize(buttonSize);
        doneButton.setLocation(place.x, place.y*7 - (Config.FRAME_SIZE.height / 8));

        this.add(backButton);
        backButton.setSize(buttonSize);
        backButton.setLocation(place.x, place.y*7);
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


        Game.newGame(playerOneName.getText(), playerTwoName.getText());
        parent.loadGamePanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust(Új játék indítása)
    private class doneAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean one = (playerOneName.getText().length() != 0);
            boolean two = (playerTwoName.getText().length() != 0);
            if(one && two)
                doneButtonFunction();
            else {
                if(!one) {
                    playerOneName.setBackground(new Color(255, 200, 200));
                    playerOneName.requestFocus();
                }
                if(!two) {
                    playerTwoName.setBackground(new Color(255, 200, 200));
                    playerTwoName.requestFocus();
                }
                doneButton.setEnabled(false);
                invalidate();
            }
        }
    }

    private class keyAction extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            JTextField temp = (JTextField) e.getSource();
            if(temp.getBackground().equals(new Color(255, 200, 200))) {
                temp.setBackground(new JTextField().getBackground());
                doneButton.setEnabled(true);
                invalidate();
            }
        }
    }

    //Vissza lépünk a főmenübe
    public void backButtonFunction(){
        parent.loadMenuPanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust(Vissza a főmenübe)
    private class backAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            backButtonFunction();
        }
    }
}
