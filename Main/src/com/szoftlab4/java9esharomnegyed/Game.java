package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    //Mezők a Game osztályhoz
    private static Arena arena;
    private static Leaderborad leaderborad;
    //private static Clock clock;

    //Program belépési pontja
    public static void main(String[] args) {
        newGame();
    }

/*
    public static void moveRobotSequence(){
        //szükséges mezők inicializálása
        arena = new Arena(); //Arena init
        leaderborad = new Leaderborad();
        clock = new Clock();
        requestName("player1", 0); //Játékosok nevének beállítása
        requestName("player2", 1);
        LogHelper.rec();
        LogHelper.clear();
        arena.getRobot(0).move();
        arena.getRobot(1).move();
        arena.getRobot(1).move();
    }
*/


    //Játékosok nevének beállítása
    public static void requestName(String name, int player) {
        arena.setRobotName(name, player);
    }

    //Új játék indítása
    public static void newGame(){
        //Beolvasás konzolról
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //LogHelper.pause();
            arena = new Arena();        // Arena felépítést kihagyjuk a newGame sekvenciából
            //LogHelper.rec();
            leaderborad = new Leaderborad();
            //clock = new Clock();
            //requestName(p1, 0);
            //requestName(p2, 1);

        //TODO játékos név átvétele
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
        updateLeaderboard(arena.getRobot(0).getName(), arena.getRobot(0).getCoveredDistance());
        updateLeaderboard(arena.getRobot(1).getName(), arena.getRobot(1).getCoveredDistance());

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
        arena = new Arena();
        //TODO biztos kell új Arena, Robot stb?
    }

    //(később megjeleníti a leaderboardot)
    public static void showLeaderboard() {
    }

    //Hozzá fűz egy rekordot a leaderboardhoz
    public static void updateLeaderboard(String name, double score) {
        leaderborad.addRecord(new Record(name, score));
    }
}
