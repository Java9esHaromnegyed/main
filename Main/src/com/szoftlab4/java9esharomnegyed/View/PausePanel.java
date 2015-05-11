package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PausePanel extends JPanel {
    private GUI parent;
    private JButton resumeButton;
    private JButton rematchButton;
    private JButton leaveButton;

    //Konstruktor
    public PausePanel(GUI p){
        parent = p;

        resumeButton = new JButton("Resume Game");
        rematchButton = new JButton("Rematch");
        leaveButton =  new JButton("Leave Game");

        resumeButton.addActionListener(new resumeAction());
        rematchButton.addActionListener(new rematchAction());
        leaveButton.addActionListener(new leaveAction());

        this.addKeyListener(new escAction());

        initLayout();
    }

    //----------------------------------------Init-------------------------------------------------
    //Megfelelő elrendezés beállítása
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

    //Játék folytatása
    public void resumeButtonFunction(){
        Game.resumeGame();
        parent.showGamePanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust(Vissza a játékba)
    private class resumeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            resumeButtonFunction();
        }
    }

    //Új játék indítása az előzőleg megadott játékos nevekkel
    public void rematchButtonFunction(){
        Game.rematch();
        parent.loadGamePanel();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust(Új játék, adott nevekkel)
    private class rematchAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            rematchButtonFunction();
        }
    }

    //Játék elhagyása, főmenübe lépés
    public void leaveButtonFunction(){
        parent.loadMenuPanel();
        Game.leaveGame();
    }

    //Gombnyomásra meghívjuk a megfelelő metódust, megerősítés a kilépésről(Vissza a főmenübe)
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

    //----------------------------------------Key-listeners----------------------------------------
    //ESC gomb megnyomására (felengedésére) folytatódik a játék
    private class escAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                resumeButtonFunction();
        }
    }
}
