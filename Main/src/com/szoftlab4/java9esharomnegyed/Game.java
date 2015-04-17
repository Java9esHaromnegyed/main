package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Prototype.Prototype;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import sun.org.mozilla.javascript.internal.ast.ArrayComprehensionLoop;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //Mezők a Game osztályhoz
    private static Arena arena;
    private static Leaderborad leaderborad;
    //private static Clock clock;

    //Program belépési pontja
    public static void main(String[] args) {
        arena = new Arena();
        leaderborad = new Leaderborad();
        Prototype prototype = new Prototype(arena);

        //leaderborad =  new Leaderborad();
    }



    //Játékosok nevének beállítása
    public static void requestName(String name, int player) {
        arena.setRobotName(name, player);
    }

    //Új játék indítása
    public static void newGame(){
        //Beolvasás konzolról
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //requestName(p1, 0);
            //requestName(p2, 1);

    }

    //Játék vége fgv
    //Leaderboardhoz hozzáadja a játékosok adatait, (később clockot megállítja, megjeleníti a leaderboardot)
    public static void gameOver(){
        updateLeaderboard(arena.getRobot(0).getName(), arena.getRobot(0).getCoveredDistance());
        updateLeaderboard(arena.getRobot(1).getName(), arena.getRobot(1).getCoveredDistance());
    }

    //Játék elhagyása fgv
    //Leaderboardhoz hozzáadja a játékosok adatait, (később clockot megállítja, megjeleníti a menüt)
    public static void leaveGame(){
        // TODO: Leave Game nem adja hozzá a leaderboardhoz az eredményt, még nincs eredmény. Igazság szerint csak visszalép a menübe. Soul.
        // updateLeaderboard(arena.getRobot(0).getName(), arena.getRobot(0).getCoveredDistance());
        // updateLeaderboard(arena.getRobot(1).getName(), arena.getRobot(1).getCoveredDistance());
    }

    //Kilépés a játékból fgv
    //Leállítja a játék program futását
    public static void exitGame(){
        System.exit(0);
    }

    //Játék szüneteltetése fgv
    //(később szünetelteti a clockot, megjeleníti a pause menüt)
    public static void pauseGame(){
    }

    //Játék folytatása fgv
    //(később a játék szüneteltetéséből vissza lép a játékba)
    public static void resumeGame(){
    }

    //Új játékot kezd a megadott pályával, és robot nevekkel, azok pontszámát nullázva
    public static void rematch(){
        List<Robot> robots = arena.getRobotList();
        arena = new Arena();
        // TODO: robotok visszatöltése. Soul.
            // kelleni fog:
            // - név
            // - ID
            // és valahogy a kezdőpozíció (csak hogy ez a szar prototype ne száljon el a gecibe)
    }

    //(később megjeleníti a leaderboardot)
    public static void showLeaderboard() {
        for(int i = 0; i < leaderborad.getListSize(); i++)
            LogHelper.inline((i+1) + ".\t" + leaderborad.getName(i) + " score: " + leaderborad.getScore(i));
    }

    //Hozzá fűz egy rekordot a leaderboardhoz
    public static void updateLeaderboard(String name, double score) {
        leaderborad.addRecord(name, score);
    }
}
