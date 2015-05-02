package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;
import com.szoftlab4.java9esharomnegyed.Leaderboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderPanel extends JPanel {
    private Leaderboard subject;
    JScrollPane table;
    JTable leaderTable;
    TableModel tableModel;
    private JButton backButton;
    private GUI parent;

    public LeaderPanel(GUI p){
        parent = p;
        subject = Game.getLeaderboard();

        backButton = new JButton("Back");
        backButton.addActionListener(new backAction());

        String[] columnNames = {"Player name", "Covered distance"};
        tableModel = new DefaultTableModel(columnNames, Config.LEADERBOARD_SIZE);
        leaderTable = new JTable(tableModel);

        initData();
        initLayout();
    }

    //Ha már létezik, csak frissítjük
    public void update(){
        subject = Game.getLeaderboard();
        initData();
    }

    //----------------------------------------Init-------------------------------------------------
    //Megfelelő elrendezés beállítása
    public void initLayout(){
        setLayout(null);
        Dimension buttonSize = new Dimension(Config.FRAME_SIZE.width / 2, Config.FRAME_SIZE.height / 10);
        Point place = new Point(Config.FRAME_SIZE.width / 4, Config.FRAME_SIZE.height / 10);

        this.add(backButton);
        backButton.setSize(buttonSize);
        backButton.setLocation(place.x, place.y*7);

        table = new JScrollPane(leaderTable);
        leaderTable.setFillsViewportHeight(true);
        leaderTable.setEnabled(false);
        leaderTable.getTableHeader().setReorderingAllowed(false);
        leaderTable.setShowVerticalLines(false);
        this.add(table);

        leaderTable.setSize(Config.FRAME_SIZE.width / 3 * 2, leaderTable.getPreferredSize().height);
        table.setSize(leaderTable.getSize().width, Math.min(Config.FRAME_SIZE.height / 2,                               // kb 10 elem fér ki utánna görgő
                leaderTable.getPreferredSize().height + leaderTable.getTableHeader().getPreferredSize().height + 1));   // ha 10 vagy annál kissebb elem van
        table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        if(Config.LEADERBOARD_SIZE > 10)
            table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        else
            table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        table.setLocation(new Point(Config.FRAME_SIZE.width / 6, Config.FRAME_SIZE.height / 10));
    }

    //Táblázat feltöltése a ranglistának megfelelő adatokkal
    private void initData(){
        for(int i = 0; i < Config.LEADERBOARD_SIZE; i++){
            if(subject.getListSize() > i) {
                leaderTable.setValueAt(subject.getName(i), i, 0);
                leaderTable.setValueAt(subject.getScore(i), i, 1);
            }
        }
    }


    //----------------------------------------Button-listeners-------------------------------------

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
