package com.szoftlab4.java9esharomnegyed;

/**
 * Created by Ricsard on 2015.02.27..
 */
public class Record {
    private String name;
    private double score;

    public Record(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
