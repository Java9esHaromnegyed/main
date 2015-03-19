package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class Game {

    private Arena arena;
    private Leaderborad leaderborad;
    private Clock clock;

    public static void main(String[] args) {

    }

    public void requestName(String name) {
    }

    public void showLeaderboard() {
    }

    public void updateLeaderboard(String name, double score) {
    }

    public void menu() {
    }

    public void newGame(){
        arena = new Arena();
        leaderborad = new Leaderborad();
        clock = new Clock();
    }

    public void pauseGame(){
        clock.pauseClock();
    }

    public void resumeGame(){
        clock.startClock();
    }

    public void rematch(){
        clock.pauseClock();
        clock.stopClock();
    }

    public void gameOver(){
        clock.stopClock();
    }

    public void leaveGame(){
        clock.stopClock();
    }
}
