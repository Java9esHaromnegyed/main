package com.szoftlab4.java9esharomnegyed.View;

import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Game;
import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame{
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private GameInitPanel gameInitPanel;
    private LeaderPanel leaderPanel;
    private PausePanel pausePanel;

    public GUI(){
        setWindowTheme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        initFrame();
        loadMenuPanel();

        setVisible(true);
    }

    //Játékot tartalmazó ablak beállítása
    private void initFrame(){
        this.setLayout(new BorderLayout());

        this.setTitle(Config.MAIN_TITLE);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new exitApp());

        setFrameSize(Config.FRAME_SIZE, this);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    //----------------------------------------Panel-placers----------------------------------------

    //Főmenü megjelenítése
    public void loadMenuPanel(){
        if (menuPanel == null)
            menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        setVisible(true);
    }

    //Játék nézet megjelenítése
    public void loadGamePanel(){
        if(gamePanel == null)
            gamePanel = new GamePanel(this);

        gamePanel.update();
        setContentPane(gamePanel);
        setVisible(true);
        gamePanel.requestFocus();
    }

    public void showGamePanel(){
        setContentPane(gamePanel);
        setVisible(true);
        gamePanel.requestFocus();
    }

    //Játékos nevek bekérése menü megjelenítése
    public void loadGameInitPanel(){
        gameInitPanel = new GameInitPanel(this);
        setContentPane(gameInitPanel);
        setVisible(true);
    }

    //Játék szüneteltetése menü megjelenítése
    public void loadPausePanel(){
        if(pausePanel == null)
            pausePanel = new PausePanel(this);
        setContentPane(pausePanel);
        setVisible(true);
        pausePanel.requestFocus();
    }

    //Ranglista megjelenítése
    public void loadLeaderPanel(){
        if (leaderPanel == null)
            leaderPanel = new LeaderPanel(this);
        else
            leaderPanel.update();
        setContentPane(leaderPanel);
        setVisible(true);
    }



    //Játék nézet frissítése
    public void updateGame(){
        gamePanel.update();
        //nem kell elkérni megint a focust, stb, majd a gamePanelben kérünk canvas.repaint()-et --> nem fog villogni
        // így sem villog, és azért van rá szükség, hogy az exit optionPane után müködjön a pausePanel
        setContentPane(gamePanel);
        setVisible(true);
        gamePanel.requestFocus();

    }

    //---------------------------------------Window-Listeners--------------------------------------
    //Ablak bezárásakor megerősítés kérése és kilépés a programból
    private class exitApp extends WindowAdapter {
        public void windowClosing(WindowEvent e){
            requestFocus(false); // letiltjuk, hogy ESC-re ne hozza be a pausePanelt, amíg fent van az OptionPane
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if(i == 0) {
                Game.exitGame();
            }
            requestFocus(); // visszaadjuk, ha nem léptünk ki
        }
    }

    //---------------------------------------utility-functions--------------------------------------
    //Ablak méretének beállítása
    private void setFrameSize(Dimension d, JFrame frame){
        frame.setMinimumSize(d);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
    }

    //Ablak megjelésének beállítása
    public static void setWindowTheme(String set){
        try {
            UIManager.setLookAndFeel(set);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
