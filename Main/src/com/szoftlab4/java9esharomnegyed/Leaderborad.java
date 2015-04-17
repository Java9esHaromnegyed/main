package com.szoftlab4.java9esharomnegyed;

import java.util.ArrayList;
import java.util.List;

public class Leaderborad {
    private List<Record> records;

    public Leaderborad(){
        records = new ArrayList<Record>();
    }

    public List<Record> getList() {
        return records;
    }

    public void addRecord(String playerName, double score) {
        Record temp = new Record(playerName, score);

        int j = 0;
        while(score < this.getScore(j)){    // megfelelő pozíció keresése
            j++;
        }

        records.add(j, temp);

        if(records.size() > Config.LEADERBOARD_SIZE){       // megfelelő méretűnek tartja a leaderboardot
            while(records.size() > Config.LEADERBOARD_SIZE)
                records.remove(Config.LEADERBOARD_SIZE);
        }
    }

    public int getListSize(){
        return records.size();
    }

    public String getName(int place){
        return records.get(place).getName();
    }

    public double getScore(int place){
        return records.get(place).getScore();
    }
}
