package com.szoftlab4.java9esharomnegyed.Prototype;

import com.szoftlab4.java9esharomnegyed.Arena;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import sun.rmi.runtime.Log;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Command {
    Arena inGameArena;
    boolean exitPrototype = false;
    private String name;
    private String[] args;

    Command (Arena arena, String n, String[] a){
        inGameArena = arena;
        name=n;
        args=a;
    }

    protected void execute() throws IOException {
        //Mindig a kapott string első szavát nézzük, az lesz a parancs, utána következnek a parancs argumentummal
        //Argumentum nélküli parancsoknál (pl testAll) megnézzük hogy args==null -> ha igen, akkor helyes az utasítás, tehát a paraméter nélküli parancs után nem írt a tesztelő semmit
        if (name.equals("loadArena"))  loadArena(args);
        if (name.equals("initArena")) initArena(args);
        if (name.equals("robotMovement")) robotMovement(args);
        if (name.equals("robotMove")) robotMove(args);
        if (name.equals("endGame")) endGame(args);
        if (name.equals("pauseGame")) pauseGame(args);
        if (name.equals("addCleanerRobot")) addCleanerRobot(args);
        if (name.equals("cleanerRobotTest")) cleanerRobotTest(args);
        if (name.equals("exitGame")) exitGame(args);
        if (name.equals("testAll")) testAll(args);
        if (name.equals("addRobot")) addRobot(args);
        if (name.equals("tick")) tick(args);
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
            int id = Integer.valueOf(args[0]);
            String command = args[1];

            int chars[] = {KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_C, KeyEvent.VK_V,
                    KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_O, KeyEvent.VK_P};
            int key = 0;

            if(command.equals("RIGHT"))
                key = 1;
            else if(command.equals("DOWN"))
                key = 2;
            else if(command.equals("LEFT"))
                key = 3;
            else if(command.equals("OIL"))
                key = 4;
            else if(command.equals("PUTTY"))
                key = 5;

            if(id == inGameArena.getRobot(0).getID())
                key += 0;
            else if(id == inGameArena.getRobot(0).getID())
                key += 6;
            else {
                LogHelper.log("Nincs ilyen id-val rendelkező Robot");
                return;
            }

            inGameArena.movementControl(chars[key]);
        }
        else{

        }
    }


    private void loadArena(String[] args){
        String txt = "";
        if(args!=null && args.length==1){
            txt = args[0].replace("\"", ""); //loadArena "arena1.txt" a formátumunk így le kell vágni mindkét "-t
            inGameArena.loadMap(txt);
        }
        else if(args != null) {
            for(int i = 0; i < args.length; i++)
                txt.concat(" " + args[i]);
            txt = txt.replace("\"", "");
            inGameArena.loadMap(txt);
        } else {
            LogHelper.error("Not enough arguments!");
        }
    }

    private void initArena(String[] args) {
        if(args == null){
            inGameArena.initArena();
        }
        else{
            LogHelper.error("Too many arguments!");
        }
    }

}
