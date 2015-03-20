package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {

    private static Arena arena;
    private static Leaderborad leaderborad;
    private static Clock clock;

    public static void main(String[] args) {
        System.out.println("main()");
        System.out.println("main() -> menu()");
        System.out.println("main(); Game;");
        System.out.println("menu() -> main()");
        menu();
    }

    public static void requestName(String name, int player) {
        System.out.println("RequestName()");
        System.out.println("requestName() -> setRobotName()");
        arena.setRobotName(name, player);
        System.out.println("requestName() returned with: void");
    }

    public static void showLeaderboard() {
    }

    public static void updateLeaderboard(String name, double score) {
    }

    public static void menu() {
        System.out.println("menu()");
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
                case 1 : System.out.println("newGame(); menu()");
                        newGame();
                         break;
                case 2 : System.out.println("newGame(); menu()");
                        newGame();
                        arena.movementControl(new Event(null, KeyEvent.VK_W, KeyEvent.VK_W));
                        break;
                case 3 : System.out.println("pauseGame(); menu()");
                        pauseGame();
                        break;
                case 0 : System.out.println("exitGame(); menu()");
                        exitGame();
                        break;
                default : throw new Throwable();
            }
        } catch (Throwable e) {
            System.out.println("Invalid menu argument");
        }
        System.out.println("menu() returned with: void");
    }

    public static void newGame(){
        System.out.println("Arena(); newGame()");
        arena = new Arena();
        leaderborad = new Leaderborad();
        clock = new Clock();

        System.out.println("requestGame(); newGame()");
        requestName("player1", 0);
        System.out.println("requestName(); newGame()");
        requestName("player2", 1);
        System.out.println("newGame() returned with: void");
    }

    public static void pauseGame(){
        clock.pauseClock();
        menu();
    }

    public static void resumeGame(){
        clock.startClock();
    }

    public static void rematch(){
        clock.pauseClock();
        clock.stopClock();
    }

    public static void gameOver(){
        clock.stopClock();
        System.out.println("menu(); gameOver()");
        menu();
        System.out.println("gameOver() returned with: void");
    }

    public static void leaveGame(){
        clock.stopClock();
        System.out.println("menu(); leaveGame()");
        menu();
        System.out.println("leaveGame() returned with: void");
    }

    public static void exitGame(){
        System.out.println("Kilépés");
        System.exit(0);
    }
}
