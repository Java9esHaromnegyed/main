package com.szoftlab4.java9esharomnegyed;

import java.awt.event.KeyEvent;

public class Config {
    //Program szinten egységes előre definiált konstans értékek
    //Pl irányok, limitek, stb.

    /*ROBOT related*/
    //speed limit
    public static final double SPD_LIMIT = 3;
    public static final double SPD_UNIT = 1;
    public static final double SPD_DEFFAULT = 1;

    //direction
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;

    public static final int OIL_TANK = 5;
    public static final int PUTTY_TANK = 5;

    /*
    public static final int EFF_NONE = 0;       cserélni karakter jelölésekre
    public static final int EFF_OIL = 1;
    public static final int EFF_PUTTY = 2;
    */

    //controll
    public static final int MOV_P2_UP = KeyEvent.VK_UP;
    public static final int MOV_P2_DOWN = KeyEvent.VK_DOWN;
    public static final int MOV_P2_LEFT = KeyEvent.VK_LEFT;
    public static final int MOV_P2_RIGHT = KeyEvent.VK_RIGHT;
    public static final int MOV_P2_OIL = KeyEvent.VK_C;
    public static final int MOV_P2_PUTTY = KeyEvent.VK_V;
    public static final int MOV_P1_UP = KeyEvent.VK_W;
    public static final int MOV_P1_DOWN = KeyEvent.VK_S;
    public static final int MOV_P1_LEFT = KeyEvent.VK_A;
    public static final int MOV_P1_RIGHT = KeyEvent.VK_D;
    public static final int MOV_P1_OIL = KeyEvent.VK_O;
    public static final int MOV_P1_PUTTY = KeyEvent.VK_P;
}
