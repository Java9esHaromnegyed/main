package com.szoftlab4.java9esharomnegyed.Prototype;

import java.io.IOException;

/**
 * Created by Abel on 2015.04.15..
 */
public class Command {
    boolean exitPrototype=false;
    private String name;
    private String[] args;

    Command (String n, String[] a){
        name=n;
        args=a;
    }

    //Visszatérési értéke a futtatott parancs kimenete
    protected String execute() throws IOException {
        //Mindig a kapott string első szavát nézzük, az lesz a parancs, utána következnek a parancs argumentummal
        if (name=="loadArena") return loadArena(args);
        return "Unknown command!";
    };

    /*Ide jönnek a fügvények amik a már értelmezett parancsot végrehajtják a kapott argumentumokkal*/

    private String loadArena(String [] args){
        //Itt dolgozik a fos


        return "arenaLoaded "+args;
    }
}
