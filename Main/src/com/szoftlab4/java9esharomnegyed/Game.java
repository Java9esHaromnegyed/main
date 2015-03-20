package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    private static Arena arena;
    private static Leaderborad leaderborad;
    private static Clock clock;

    public static void main(String[] args) {
        System.out.println("main() -> menu();");
        menu();
        System.out.println("main() returned with: void;");
    }

    public static void requestName(String name, int player) {
        System.out.println("requestName() -> setRobotName();");
        arena.setRobotName(name, player);
        System.out.println("requestName() returned with: void;");
    }

    public static void showLeaderboard() {
    }

    public static void updateLeaderboard(String name, double score) {
    }

    public static void menu() {
        System.out.println("menu();");

        System.out.println();
        System.out.println("[1] New Game");
        System.out.println("[2] Robot Movement");
        System.out.println("[3] Leave Game");
        System.out.println("[0] Exit");

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String menuOption = bufferedReader.readLine();
            int option = Integer.parseInt(menuOption);
            switch (option) {
                case 1 : System.out.println("menu() -> newGame();");
                        newGame();
                        break;
                case 2 : System.out.println("menu() -> newGame;()");
                        newGame();
                        System.out.println("#event: W lenyomása");
                        arena.movementControl(new Event(null, KeyEvent.VK_W, KeyEvent.VK_W));
                        break;
                /*case 3 : System.out.println("menu() -> pauseGame()");         nem kell a szekvenciája
                        pauseGame();
                        break;*/
                case 0 : System.out.println("menu() -> exitGame();");
                        exitGame();
                        break;
                default : throw new Throwable("out of range");
            }
        } catch (Throwable e) {
            System.out.println("Invalid menu argument; " + e.getMessage() + " @Game:menu_switch");
        }
        System.out.println("menu() returned with: void;");
    }

    public static void newGame(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            System.out.println("newGame() -> Arena();");
            arena = new Arena();
            leaderborad = new Leaderborad();
            clock = new Clock();

            System.out.println("newGame() -> requestGame();");
            System.out.println("player one: ");
            String p1 = bufferedReader.readLine();
            requestName(p1, 0);

            System.out.println("newGame() -> requestName();");
            System.out.println("player two: ");
            String p2 = bufferedReader.readLine();
            requestName(p2, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("newGame() returned with: void;");
    }

    public static void pauseGame(){
    }

    public static void resumeGame(){
    }

    public static void rematch(){
    }

    public static void gameOver(){
        System.out.println("gameOver() -> menu();");
        menu();
        System.out.println("gameOver() returned with: void;");
    }

    public static void leaveGame(){
        System.out.println("leaveGame() -> menu();");
        menu();
        System.out.println("leaveGame() returned with: void;");
    }

    public static void exitGame(){
        System.out.println("exitGame();");
        System.exit(0);
    }
}
