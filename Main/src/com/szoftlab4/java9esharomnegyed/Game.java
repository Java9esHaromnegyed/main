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
        LogHelper.comment("main() -> menu();");
        menu();
        LogHelper.comment("\nmain() returned with: void;");
    }

    public static void takeEffectSequence(){
        LogHelper.pause();  /*szükséges mezők inicializálása*/
        arena = new Arena(); //Arena init
        leaderborad = new Leaderborad();
        clock = new Clock();
        requestName("player1", 0); //Játékosok nevének beállítása
        requestName("player2", 1);
        LogHelper.rec();
        LogHelper.clear();
        arena.takeEffect(arena.getRobot(0));
    }

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
            System.out.println("[4] Akadály robotra hatása");
            System.out.println("[0] Exit");

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
                        LogHelper.comment("menu() -> newGame();");
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
                        LogHelper.rec();
                        LogHelper.comment("#event: W,S,Up,A,Right lenyomása");
                        LogHelper.clear();
                        //Robotnak különböző irányok megadása szimulációképp
                        arena.movementControl(KeyEvent.VK_W);
                        arena.movementControl(KeyEvent.VK_S);
                        arena.movementControl(KeyEvent.VK_UP);
                        arena.movementControl(KeyEvent.VK_A);
                        arena.movementControl(KeyEvent.VK_RIGHT);
                        break;
                    //Arena initialization választása esetén
                    case 3:
                        LogHelper.comment("menu() -> new Arena();");
                        arena = new Arena(); //Arena init
                        requestName("player1", 0); //Játékosok nevének beállítása
                        requestName("player2", 1);
                        break;
                    //Robot akadályralépése és annak hatáskiváltása
                    case 4:
                        takeEffectSequence();
                        break;
                    //Exit választása esetén
                    case 0:
                        //Megfelelő log és exitGame() meghívása
                        LogHelper.inline("menu() -> exitGame();");
                        exitGame();
                        break;
                    default:
                        //Érvénytelen menüpont esetén hibát dobunk amit majd a catch ágban le is kezelünk
                        throw new Throwable("out of range");
                }
            } catch (Throwable e) {
                //Bármely hiba esetén hiba konzolra íratása
                LogHelper.error("Invalid menu argument; " + e.getMessage() + "; @Game:menu_switch");
            }
        }
    }

    //Játékosok nevének beállítása
    public static void requestName(String name, int player) {
        LogHelper.call("requestName(); Game;");
        arena.setRobotName(name, player);
        LogHelper.ret("requestName() returned with: void;");
    }

    //Új játék indítása
    public static void newGame(){
        //Beolvasás konzolról
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            LogHelper.call("newGame(); Game;");
            arena = new Arena();
            leaderborad = new Leaderborad();
            clock = new Clock();
            //Első játékos nevének beolvasása
            LogHelper.question("player one: ");
            String p1 = bufferedReader.readLine();
            requestName(p1, 0);
            //Második játékos nevének beolvasása
            LogHelper.question("player two: ");
            String p2 = bufferedReader.readLine();
            requestName(p2, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogHelper.ret("newGame() returned with: void;");
    }

    //Játék vége fgv
    public static void gameOver(){
        LogHelper.call("gameOver() -> menu();");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogHelper.ret("gameOver() returned with: void;");
        //Végül visszatérünk a menübe
        menu();
    }

    //Játék elhagyása fgv
    public static void leaveGame(){
        System.out.println("leaveGame() -> menu();");
        //Visszatérünk a menübe
        menu();
        System.out.println("leaveGame() returned with: void;");
    }

    //Kilépés a játékból fgv
    public static void exitGame(){
        LogHelper.call("exitGame();");
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
