package com.szoftlab4.java9esharomnegyed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Clock{
    private int counter;
    private Timer timer;

    public Clock(){
        counter = 0;
        timer = new Timer(Config.TIMER_TICK, new action());
    }

    public void tick() {
        if(counter < Config.TIME_OVER +1) {
            counter++;
            Game.tick();
        } else {
            Game.gameOver();
            timer.stop();
        }
    }

    public void pauseClock(){
        timer.stop();
    }

    public void startClock(){
        timer.start();
    }

    public void stopClock(){
        counter = 0;
        timer.stop();
    }

    public int getTime(){
        return Config.TIME_OVER - counter +1;
    }

    private class action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    }


}
