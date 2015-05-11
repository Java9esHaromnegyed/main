package com.szoftlab4.java9esharomnegyed;

import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private double score;

    //Konstruktor, adott névvel és pontszámmal
    //Ranglista elemeit tárolja
    public Record(String name, double score) {
        this.name = name;
        this.score = score;
    }

    //Vissza adja az adott Rekordhoz tartozó nevet
    public String getName() {
        return name;
    }

    //Vissza adja az adott Rekordhoz tartozó pontszámot
    public double getScore() {
        return score;
    }
}
