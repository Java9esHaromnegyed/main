package com.szoftlab4.java9esharomnegyed;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard implements Serializable {
    private List<Record> records;

    public Leaderboard(){
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
        if(records.size() <= place)
            return null;
        return records.get(place).getName();
    }

    public double getScore(int place){
        if(records.size() <= place)
            return 0;
        return records.get(place).getScore();
    }


    //Saving the leaderboard to file
    public void saveLeaderboard () throws IOException {
        FileOutputStream fileOut = null ;
        ObjectOutputStream objectOut = null;
        try {
            fileOut=new FileOutputStream("leaderboard.ser");
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(objectOut!=null)
                objectOut.close();
            if(fileOut!=null)
                fileOut.close();
        }
    }


    //loading the leaderboard from file
    public Leaderboard loadLeaderboard() throws IOException {
        Leaderboard temp = null;
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try{
            fileIn = new FileInputStream("leaderboard.ser");
            objectIn = new ObjectInputStream(fileIn);
            temp = (Leaderboard)objectIn.readObject();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (objectIn!=null)
                objectIn.close();
            if (fileIn!=null)
                fileIn.close();
            return temp;
            //returns null if file not found
        }
    }
}
