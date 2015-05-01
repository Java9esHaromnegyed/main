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

    private void initFrame(){

        //menuPanel = new MenuPanel();
        this.setLayout(new BorderLayout());

        this.setTitle(Config.MAIN_TITLE);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new exitApp());

        //setContentPane(menuPanel);
        setFrameSize(Config.FRAME_SIZE, this);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    //----------------------------------------Panel-placers----------------------------------------
    public void loadGamePanel(){

    }
    public void loadMenuPanel(){
        if (menuPanel == null)
            menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        setVisible(true);
    }
    public void loadGameInitPanel(){
        LogHelper.inline("letsPlay!");
        if(gameInitPanel == null)
            gameInitPanel = new GameInitPanel(this);
    }

    public void loadLeaderPanel(){
        if (leaderPanel == null)
            leaderPanel = new LeaderPanel(this);
        else
            leaderPanel.update();
        setContentPane(leaderPanel);
        setVisible(true);
    }
    public void loadPausePanel(){

    }

    public void showGamePanel(){

    }

    public void updateGame(){

    }

    //---------------------------------------Windows-Listeners--------------------------------------
    private class exitApp extends WindowAdapter {
        public void windowClosing(WindowEvent e){
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if(i == 0) {
                Game.exitGame();
            }
        }
    }

    //---------------------------------------utility-functions--------------------------------------
    private void setFrameSize(Dimension d, JFrame frame){
        frame.setMinimumSize(d);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
    }

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
