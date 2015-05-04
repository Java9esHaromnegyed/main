package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Prototype.Prototype;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import com.szoftlab4.java9esharomnegyed.View.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //Mezők a Game osztályhoz
    private static Arena arena;
    private static Leaderboard leaderboard;
    private static String[] arguments = null;
    private static Clock clock;
    private static GUI gui;

    //Program belépési pontja
    public static void main(String[] args) {
        //arguments = args;
        arena = new Arena();

        leaderboard = new Leaderboard();
        //Loading the leaderboard from file
        try {
            if (leaderboard.loadLeaderboard() != null)
                leaderboard = leaderboard.loadLeaderboard();
        }
        catch (IOException e){
            //e.printStackTrace();
        }

        clock = new Clock();
        /*
        for(int i = 1; i < 15; i++){
            leaderboard.addRecord("test" + i, i);
        }
        */

        gui = new GUI();

        //Prototype prototype = new Prototype(arena);

        /*if(args.length == 4){
            Config.TEST_FOLDER = args[2];
            Config.ARENA_FOLDER = args[3];
        }*/

        /*try {
            prototype.runPrototype(args);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }



    //Játékosok nevének beállítása
    public static void requestName(String name, int player) {
        arena.setRobotName(name, player);
    }

    //Új játék indítása
    public static void newGame(String playerOne, String playerTwo){
        //Beolvasás konzolról
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        requestName(playerOne, 0);
        requestName(playerTwo, 1);
        clock.startClock();
    }

    //Játék vége fgv
    //Leaderboardhoz hozzáadja a játékosok adatait, (később clockot megállítja, megjeleníti a leaderboardot)
    public static void gameOver(){
        updateLeaderboard(arena.getRobot(0).getName(), arena.getRobot(0).getCoveredDistance());
        updateLeaderboard(arena.getRobot(1).getName(), arena.getRobot(1).getCoveredDistance());
        //Saving the leaderboard
        try {
            leaderboard.saveLeaderboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseGame();
    }

    //Játék elhagyása fgv
    //Leaderboardhoz hozzáadja a játékosok adatait, (később clockot megállítja, megjeleníti a menüt)
    public static void leaveGame(){
        clock.stopClock();
    }

    //Kilépés a játékból fgv
    //Leállítja a játék program futását
    public static void exitGame(){
        System.exit(0);
    }

    //Játék szüneteltetése fgv
    //(később szünetelteti a clockot, megjeleníti a pause menüt)
    public static void pauseGame(){
        clock.pauseClock();
    }

    //Játék folytatása fgv
    //(később a játék szüneteltetéséből vissza lép a játékba)
    public static void resumeGame(){
        clock.startClock();
    }

    //Új játékot kezd a megadott pályával, és robot nevekkel, azok pontszámát nullázva
    public static void rematch(){
        clock.stopClock();  // nullázás

        List<Robot> robots = arena.getRobotList();
        arena.initArena();
        arena.setRobotName(robots.get(0).getName(), 0);
        arena.setRobotName(robots.get(1).getName(), 1);

        clock.startClock(); // újra indítás
    }

    //(később megjeleníti a leaderboardot)
    public static void showLeaderboard() {
        for(int i = 0; i < leaderboard.getListSize(); i++)
            LogHelper.inline((i+1) + ".\t" + leaderboard.getName(i) + " score: " + leaderboard.getScore(i));
    }

    //Hozzá fűz egy rekordot a leaderboardhoz
    public static void updateLeaderboard(String name, double score) {
        leaderboard.addRecord(name, score);
    }

    public static Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public static Arena getArena(){
        return arena;
    }

    public static void tick(){
        arena.tick();
        gui.updateGame();
    }

    public static int getTime(){
        return clock.getTime();
    }

    public static String[] getArguments() {
        return arguments;
    }

}
