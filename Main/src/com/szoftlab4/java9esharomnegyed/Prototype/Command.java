package com.szoftlab4.java9esharomnegyed.Prototype;

import com.szoftlab4.java9esharomnegyed.*;
import com.szoftlab4.java9esharomnegyed.Robot;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class Command {
    Arena inGameArena;
    boolean exitPrototype = false;
    private String name;
    private String[] args;
    private Leaderboard leaderB;

    Command (Arena arena, String n, String[] a){
        inGameArena = arena;
        name=n;
        args=a;
    }

    protected void execute() throws IOException {
        //Mindig a kapott string első szavát nézzük, az lesz a parancs, utána következnek a parancs argumentummal
        //Argumentum nélküli parancsoknál (pl testAll) megnézzük hogy args==null -> ha igen, akkor helyes az utasítás, tehát a paraméter nélküli parancs után nem írt a tesztelő semmit
        if (name.equals("loadArena"))  loadArena(args);
        else if (name.equals("initArena")) initArena(args);
        else if (name.equals("robotMovement")) robotMovement(args);
        else if (name.equals("robotMove")) robotMove(args);
        else if (name.equals("pauseGame")) pauseGame(args);
        else if (name.equals("addCleanerRobot")) addCleanerRobot(args);
        else if (name.equals("exitGame")) exitGame(args);
        // else if (name.equals("testAll")) testAll(args); nem kell
        else if (name.equals("addRobot")) addRobot(args);
        else if (name.equals("tick")) tick(args);
        else
            LogHelper.error("Unknown command!");
    }

    /*Ide jönnek a fügvények amik a már értelmezett parancsot végrehajtják a kapott argumentumokkal*/


    private void tick(String[] args) {
        if(args!=null && args.length==1){
            try {
                int count = Integer.valueOf(args[0]);
                for (int i = 0; i < count; i++)
                    inGameArena.tick();
            } catch (NumberFormatException e) {
                LogHelper.error("Invalid argument! Argument has to be a number");
            }
        }
        else{

        }
    }

    private void addRobot(String[] args) {
        if(args!=null && args.length==4){
            Dimension dim = new Dimension(Integer.valueOf(args[2]), Integer.valueOf(args[3]));
            inGameArena.addRobot(args[1], dim, 0, Integer.valueOf(args[0]));
        }
        else{
            LogHelper.error("You have to give four arguments for the addRobot test.");
        }
    }

    /* nem kell
    private void testAll(String[] args) {
        if(args==null){

        }
        else{
            LogHelper.error("There is no option needed for testAll command.");
        }

    }*/

    private void exitGame(String[] args) {
        if(args==null){
            Game.exitGame();
        }
        else{
            LogHelper.error("There is no option needed for exitGame command.");
        }

    }

    private void addCleanerRobot(String[] args) {
        if(args!=null && args.length==3){
            Dimension dim = new Dimension(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
            inGameArena.addCleanerRobot(dim, Config.DIR_RIGHT, Integer.valueOf(args[0]));
        }
        else{
            LogHelper.error("Please give me exactly 3 arguments. - [id] [x] [y]");
        }

    }

    // kár hogy egyszer sem fog lefutni...
    private void pauseGame(String[] args) {
        if(args!=null && args.length==1){
            Game.pauseGame();
            if (args[0].equals("leaveGame")) {
                Game.leaveGame();
            } else if (args[0].equals("resume")) {
                Game.resumeGame();
            } else if (args[0].equals("rematch")){
                Game.rematch();
            } else {
                LogHelper.error("Not valid option!");
            }
        }
        else{
            LogHelper.error("Not enough arguments!");
        }

    }

    private void robotMove(String[] args) {
        if(args!=null && args.length==1){
            inGameArena.getRobot(Integer.valueOf(args[0])).move();
        }
        else{
            LogHelper.error("This command requires exactly one argument which is the robot ID");
        }

    }

    private void robotMovement(String[] args) {
        if(args!=null && args.length==2){
            int id = Integer.valueOf(args[0]);
            String command = args[1];

            int chars[] = {KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_C, KeyEvent.VK_V,
                    KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_O, KeyEvent.VK_P};
            int key = 0;

            if(command.equals("UP"))
                key = 0;
            else if(command.equals("RIGHT"))
                key = 1;
            else if(command.equals("DOWN"))
                key = 2;
            else if(command.equals("LEFT"))
                key = 3;
            else if(command.equals("OIL"))
                key = 4;
            else if(command.equals("PUTTY"))
                key = 5;
            else
                LogHelper.error("Wrong command!");

            if(id == inGameArena.getRobotList().get(0).getID())
                key += 0;
            else if(id == inGameArena.getRobotList().get(1).getID())
                key += 6;
            else {
                LogHelper.error("There is no such robot with id: " + id + "!");
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
            try {
                inGameArena.loadMap(txt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(args != null) {
            for(int i = 0; i < args.length; i++)
                txt.concat(" " + args[i]);
            txt = txt.replace("\"", "");
            try {
                inGameArena.loadMap(txt);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
