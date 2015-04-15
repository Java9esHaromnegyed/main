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

    protected void execute() throws IOException {
        //Mindig a kapott string első szavát nézzük, az lesz a parancs, utána következnek a parancs argumentummal
        //Argumentum nélküli parancsoknál (pl testAll) megnézzük hogy args==null -> ha igen, akkor helyes az utasítás, tehát a paraméter nélküli parancs után nem írt a tesztelő semmit
        if (name=="loadArena")  loadArena(args);
        if (name=="initArena") initArena(args);
        if (name=="robotMovement") robotMovement(args);
        if (name=="robotMove") robotMove(args);
        if (name=="endGame") endGame(args);
        if (name=="pauseGame") pauseGame(args);
        if (name=="addCleanerRobot") addCleanerRobot(args);
        if (name=="cleanerRobotTest") cleanerRobotTest(args);
        if (name=="exitGame") exitGame(args);
        if (name=="testAll") testAll(args);
        if (name=="addRobot") addRobot(args);
        if (name=="tick") tick(args);
    }

    /*Ide jönnek a fügvények amik a már értelmezett parancsot végrehajtják a kapott argumentumokkal*/


    private void tick(String[] args) {
        if(args!=null && args.length==1){

        }
        else{

        }
    }

    private void addRobot(String[] args) {
        if(args!=null && args.length==4){

        }
        else{

        }

    }

    private void testAll(String[] args) {
        if(args==null){

        }
        else{

        }

    }

    private void exitGame(String[] args) {
        if(args==null){

        }
        else{

        }

    }

    private void cleanerRobotTest(String[] args) {
        if(args!=null && args.length==2){

        }
        else{

        }

    }

    private void addCleanerRobot(String[] args) {
        if(args!=null && args.length==3){

        }
        else{

        }

    }

    private void pauseGame(String[] args) {
        if(args!=null && args.length==1){

        }
        else{

        }

    }

    private void endGame(String[] args) {
        if(args!=null && args.length==1){

        }
        else{

        }

    }

    private void robotMove(String[] args) {
        if(args!=null && args.length==1){

        }
        else{

        }

    }

    private void robotMovement(String[] args) {
        if(args!=null && args.length==2){

        }
        else{

        }
    }


    private void loadArena(String [] args){
        if(args!=null && args.length==1){

        }
        else{

        }
    }

    private void initArena(String [] args) {
        if(args==null){

        }
        else{

        }
    }

}
