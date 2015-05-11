package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private Canvas canvas;
    private GUI parent;

    //Konstruktor
    public GamePanel(GUI p){
        super();
        parent = p;

        this.addKeyListener(new keyAction());
        this.setFocusable(true);
        initLayout();
        canvas.paint(parent.getGraphics());
        invalidate();
    }

    //Felhasználói felület frissítése
    public void update() {
        canvas.paint(parent.getGraphics());
    }

    //Felület elrendezésének inicializálása
    private void initLayout(){
        setLayout(new BorderLayout());
        canvas = new Canvas(parent);

        this.add(canvas, BorderLayout.CENTER);
    }

    //----------------------------------------Key-listeners----------------------------------------
    //ESC gomb megnyomására (felengedésére) a játék szünetel és megjelenik a Pause Menu
    //egyébként a megnyomott gombnak megfelelő irányítás hajtódik végre
    private class keyAction extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Game.pauseGame();
                parent.loadPausePanel();
            } else
                Game.getArena().movementControl(e);
        }
    }
}
