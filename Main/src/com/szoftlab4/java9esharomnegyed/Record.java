package com.szoftlab4.java9esharomnegyed;

import java.io.Serializable;

public class Record implements Serializable {
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
