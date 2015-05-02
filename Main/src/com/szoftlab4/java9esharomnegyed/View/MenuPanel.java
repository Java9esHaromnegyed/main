package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private GUI parent;
    private JButton play;
    private JButton leaderBoard;
    private JButton exit;

    public MenuPanel(GUI p){
        parent = p;

        play = new JButton("Play");
        play.addActionListener(new playAction());

        leaderBoard = new JButton("Leaderboard");
        leaderBoard.addActionListener(new leaderAction());

        exit = new JButton("Exit");
        exit.addActionListener(new exitAction());

        initLayout();
    }

    //Elrendezés beállítása
    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);

        this.add(play);
        play.setSize(buttonSize);
        play.setLocation(place);

        this.add(leaderBoard);
        leaderBoard.setSize(buttonSize);
        leaderBoard.setLocation(place.x, place.y + buttonSize.height + 10);

        this.add(exit);
        exit.setSize(buttonSize);
        exit.setLocation(place.x, place.y*7);

    }

    //----------------------------------------Button-listeners-------------------------------------

    //Új játék indítása
    public void playButtonFunction(){
        parent.loadGameInitPanel();
    }

    //Gombnyomásra megfelelő metódus meghívása(Új játék indítása)
    private class playAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            playButtonFunction();
        }
    }

    //Ranglista megjelenítése
    public void leaderBoardButtonFunction(){
        parent.loadLeaderPanel();
    }

    //Gombnyomásra megfelelő metódus meghívása(Ranglista megjelenítése)
    private class leaderAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            leaderBoardButtonFunction();
        }
    }

    //Kilépés a programból
    public void exitButtonFunction(){
        int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if(i == 0) {
            Game.exitGame();
        }
    }

    //Gombnyomásra megfelelő metódus meghívása(Kilépés a programból)
    private class exitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            exitButtonFunction();
        }
    }
}
