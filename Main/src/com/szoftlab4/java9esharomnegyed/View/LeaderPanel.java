package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;
import com.szoftlab4.java9esharomnegyed.Leaderboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderPanel extends JPanel {
    private GUI parent;
    private Leaderboard subject;
    private JButton backButton;
    private JScrollPane scrollableLeaderboardPane;
    private JTextArea leaderboardArea;


    //------Button listeners------
    public void backButtonFunction(){
        parent.loadMenuPanel();
    }

    private class BackAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            backButtonFunction();
        }
    }


    //---Constructor---
    public LeaderPanel(GUI p){
        //TODO leaderboardot átvenni
        parent = p;
        subject = new Leaderboard();
        backButton = new JButton("Back");
        backButton.addActionListener(new BackAction());
        leaderboardArea = new JTextArea(10, 50);
        scrollableLeaderboardPane = new JScrollPane(leaderboardArea);
        leaderboardArea.setLineWrap(true);
        leaderboardArea.setEditable(false);

        initLayout();

        for(int i = 0; i < subject.getList().size(); i++){
            if(subject.getList().get(i) != null)
                leaderboardArea.append(subject.getList().get(i).getName()+'\t'+subject.getList().get(i).getScore()+'\n');
        }
    }

    public void initLayout() {
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Dimension leaderboardSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point leaderboardPlace = new Point(Config.FRAME_SIZE.width / 8, Config.FRAME_SIZE.height / 20);
        //TODO vissza gomb helye
        Point buttonPlace = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);

        this.add(scrollableLeaderboardPane);
        scrollableLeaderboardPane.setSize(leaderboardSize);
        scrollableLeaderboardPane.setLocation(leaderboardPlace);

        this.add(backButton);
        backButton.setSize(buttonSize);
        backButton.setLocation(buttonPlace);
    }
}
