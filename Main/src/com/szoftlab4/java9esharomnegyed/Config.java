package com.szoftlab4.java9esharomnegyed;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Config {
    // Program szinten egységes előre definiált konstans értékek
    // Pl irányok, limitek, stb.

    /* ROBOT related */
    // speed limit
    public static final double SPD_LIMIT = 3;
    public static final double SPD_UNIT = 1;
    public static final double SPD_DEFFAULT = 1;

    /* Cleaner ROBOT related */
    // clean time
    public static final int CLN_TIME = 2;

    // direction
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;

    // oil, putty reserves
    public static final int OIL_TANK = 2;
    public static final int PUTTY_TANK = 2;

    // control
    public static final int MOV_P2_UP = KeyEvent.VK_UP;
    public static final int MOV_P2_DOWN = KeyEvent.VK_DOWN;
    public static final int MOV_P2_LEFT = KeyEvent.VK_LEFT;
    public static final int MOV_P2_RIGHT = KeyEvent.VK_RIGHT;
    public static final int MOV_P2_OIL = KeyEvent.VK_O;
    public static final int MOV_P2_PUTTY = KeyEvent.VK_P;

    public static final int MOV_P1_UP = KeyEvent.VK_W;
    public static final int MOV_P1_DOWN = KeyEvent.VK_S;
    public static final int MOV_P1_LEFT = KeyEvent.VK_A;
    public static final int MOV_P1_RIGHT = KeyEvent.VK_D;
    public static final int MOV_P1_OIL = KeyEvent.VK_C;
    public static final int MOV_P1_PUTTY = KeyEvent.VK_V;

    /* ARENA related */
    public static final int TILE_SIZE = 32;     // obstacles, robots and generally "tile"-s dimension in pixel
    public static final int DEF_MAPSIZE = 64;
    public static String ARENA_FOLDER = "/assets/arenas/";
    public static final String DEF_MAP = "testArena1.txt";
    public static final int AGE_LIMIT = 10;
    public static final int DECAY_LIMIT = 4;

    /* TEST related */
    public static String TEST_FOLDER = "Main/data/tests/";

    /* LEADERBOARD related */
    public static final int LEADERBOARD_SIZE = 10;
    public static final String LEADERBOARD_FILE = "leaderboard.ser";

    /* Clock related */
    public static final int TIME_OVER = 50;    //in ticks
    public static final int TIMER_TICK = 1000; // 1000 means that the clock will tick every second

    /* GUI related */
    public static final String MAIN_TITLE = "Phoebe";
    public static final Dimension FRAME_SIZE = new Dimension(670,390);

    // picture paths for game-init-panel and game-panel
    public static final String BLUE = "/assets/initPanel/blue.png";
    public static final String RED = "/assets/initPanel/red.png";
    public static final String PUTTY = "/assets/initPanel/putty.png";
    public static final String OIL = "/assets/initPanel/oil.png";
    public static final String CLEANER_ROBOT = "/assets/initPanel/cleanerRobot.png";
    public static final String WALL = "/assets/initPanel/wall.png";
}
