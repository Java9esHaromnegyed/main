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
        menu();
    }

    public static void requestName(String name, int player) {
        arena.setRobotName(name, player);
    }

    public static void showLeaderboard() {
    }

    public static void updateLeaderboard(String name, double score) {
    }

    public static void menu() {
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
                case 1 : newGame();
                         break;
                case 2 : newGame();
                        arena.movementControl(new Event(null, KeyEvent.VK_W, KeyEvent.VK_W));
                        break;
                case 3 : pauseGame();
                        break;
                case 0 : exitGame();
                        break;
                default : throw new Throwable();
            }
        } catch (Throwable e) {
            System.out.println("Invalid menu argument");
        }
    }

    public static void newGame(){
        arena = new Arena();
        leaderborad = new Leaderborad();
        clock = new Clock();

        requestName("player1", 0);
        requestName("player2", 1);
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
        menu();
    }

    public static void leaveGame(){
        clock.stopClock();
        menu();
    }

    public static void exitGame(){
        System.exit(0);
    }
}
