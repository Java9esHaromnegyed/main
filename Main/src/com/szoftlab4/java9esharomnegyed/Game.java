package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;

public class Game {

    private static Arena arena;
    private static Leaderborad leaderborad;
    private static Clock clock;

    public static void main(String[] args) {
        LogHelper.logFirst("main() -> menu();");
        menu();
        LogHelper.ret("\nmain() returned with: void;");
    }

    public static void menu() {
        while(true) {
            System.out.println();
            System.out.println("[1] New Game");
            System.out.println("[2] Robot Movement");
            //System.out.println("[3] Leave Game");
            System.out.println("[0] Exit");

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            try {
                String menuOption = bufferedReader.readLine();
                int option = Integer.parseInt(menuOption);
                switch (option) {
                    case 1:
                        LogHelper.logFirst("menu() -> newGame();");
                        newGame();
                        break;
                    case 2:
                        LogHelper.pause();  /*szükséges mezők inicializálása*/
                        arena = new Arena();
                        leaderborad = new Leaderborad();
                        clock = new Clock();
                        requestName("player1", 0);
                        requestName("player2", 1);
                        LogHelper.rec();
                        LogHelper.logFirst("#event: W lenyomása");
                        LogHelper.clear();
                        arena.movementControl(KeyEvent.VK_W);
                        break;
                    case 0:
                        LogHelper.logFirst("menu() -> exitGame();");
                        exitGame();
                        break;
                    default:
                        throw new Throwable("out of range");
                }
            } catch (Throwable e) {
                LogHelper.error("Invalid menu argument; " + e.getMessage() + "; @Game:menu_switch");
            }
        }
    }

    public static void requestName(String name, int player) {
        LogHelper.call("requestName() -> setRobotName();");
        arena.setRobotName(name, player);
        LogHelper.ret("requestName() returned with: void;");
    }

    public static void newGame(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            LogHelper.call("newGame();");
            arena = new Arena();
            leaderborad = new Leaderborad();
            clock = new Clock();

            //System.out.println("newGame() -> requestGame();");
            System.out.println("player one: ");
            String p1 = bufferedReader.readLine();
            requestName(p1, 0);

            //System.out.println("newGame() -> requestName();");
            System.out.println("player two: ");
            String p2 = bufferedReader.readLine();
            requestName(p2, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogHelper.ret("newGame() returned with: void;");
    }

    public static void gameOver(){
        LogHelper.call("gameOver() -> menu();");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogHelper.ret("gameOver() returned with: void;");
        menu();
    }

    public static void leaveGame(){
        System.out.println("leaveGame() -> menu();");
        menu();
        System.out.println("leaveGame() returned with: void;");
    }

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
