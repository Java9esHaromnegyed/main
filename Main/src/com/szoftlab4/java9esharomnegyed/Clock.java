package com.szoftlab4.java9esharomnegyed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Clock{
    private int counter;
    private Timer timer;

    //Konstruktor
    public Clock(){
        counter = 0;
        timer = new Timer(Config.TIMER_TICK, new action());
    }

    //A játékmenet órajelhez ütemezése
    public void tick() {
        if(counter < Config.TIME_OVER +1) {
            counter++;
            Game.tick();
        } else {
            Game.gameOver();
            timer.stop();
        }
    }

    //Játék szüneteltetésénél szüneteltetjük az órát
    public void pauseClock(){
        timer.stop();
    }

    //Játék folytatásánál folytatjuk az óra számlálást
    public void startClock(){
        timer.start();
    }

    //Játék megállításánál megállítjuk, nullázzuk az óra számlálóját
    public void stopClock(){
        counter = 0;
        timer.stop();
    }

    //Vissza adja a hátralévő időt
    public int getTime(){
        return Config.TIME_OVER - counter +1;
    }

    //Az időzítő által kiváltott esemény, mely TIME_TICK- enként hívódik meg
    private class action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    }


}
