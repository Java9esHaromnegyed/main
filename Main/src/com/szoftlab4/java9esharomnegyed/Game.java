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
    private static Clock clock;

    //Program belépési pontja
    public static void main(String[] args) {
        //Első log, és a menü megjelenítése
        menu();
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

    //Menü megjelenítésért és kezelésért felelős fgv.
    public static void menu() {
        while(true) {
            //Log ürítése
            LogHelper.clear();
            System.out.println();
            //Menüpontok kiíratása
            System.out.println("[1] New Game");
            System.out.println("[2] Robot Movement");
            System.out.println("[3] Arena initialization");
            System.out.println("[4] Robot mozgás, akadály hatása");
            System.out.println("[0] Exit");
            System.out.println();

            //Választott menüpont beolvasásához szükséges readerek
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            try {
                //Választott menüpont beolvasása
                String menuOption = bufferedReader.readLine();
                int option = Integer.parseInt(menuOption);
                switch (option) {
                    //New Game választása esetén
                    case 1:
                        //Megfelelő log és newGame() fgv mehívása
                        newGame();
                        break;
                    //Robot Movement választása esetén
                    case 2:
                        LogHelper.pause();  /*szükséges mezők inicializálása*/
                        arena = new Arena(); //Arena init
                        leaderborad = new Leaderborad();
                        clock = new Clock();
                        requestName("player1", 0); //Játékosok nevének beállítása
                        requestName("player2", 1);
                        //Robotnak különböző irányok megadása szimulációképp
                        arena.movementControl(KeyEvent.VK_W);
                        arena.movementControl(KeyEvent.VK_S);
                        arena.movementControl(KeyEvent.VK_UP);
                        arena.movementControl(KeyEvent.VK_A);
                        arena.movementControl(KeyEvent.VK_RIGHT);
                        break;
                    //Arena initialization választása esetén
                    case 3:
                        arena = new Arena(); //Arena init
                        requestName("player1", 0); //Játékosok nevének beállítása
                        requestName("player2", 1);
                        break;
                    //Robot mozgása akadályralépése és annak hatáskiváltása
                    case 4:
                        //moveRobotSequence();
                        break;
                    //Exit választása esetén
                    case 0:
                        //Megfelelő log és exitGame() meghívása
                        exitGame();
                        break;
                    default:
                        //Érvénytelen menüpont esetén hibát dobunk amit majd a catch ágban le is kezelünk
                        throw new Throwable("out of range");
                }
                System.in.read();
            } catch (Throwable e) {
                //Bármely hiba esetén hiba konzolra íratása
            }
        }
    }

    //Játékosok nevének beállítása
    public static void requestName(String name, int player) {
        arena.setRobotName(name, player);
    }

    //Új játék indítása
    public static void newGame(){
        //Beolvasás konzolról
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            //LogHelper.pause();
            arena = new Arena();        // Arena felépítést kihagyjuk a newGame sekvenciából
            //LogHelper.rec();
            leaderborad = new Leaderborad();
            clock = new Clock();
            //Első játékos nevének beolvasása
            String p1 = bufferedReader.readLine();
            requestName(p1, 0);
            //Második játékos nevének beolvasása
            String p2 = bufferedReader.readLine();
            requestName(p2, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Játék vége fgv
    public static void gameOver(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Végül visszatérünk a menübe
        menu();
    }

    //Játék elhagyása fgv
    public static void leaveGame(){
        //Visszatérünk a menübe
        menu();
    }

    //Kilépés a játékból fgv
    public static void exitGame(){
        System.exit(0);
    }

    public static void pauseGame(){
    }

    public static void resumeGame(){
    }

    public static void rematch(){
    }

    public static void showLeaderboard() {
    }

    public static void updateLeaderboard(String name, double score) {
    }
}
