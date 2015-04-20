package com.szoftlab4.java9esharomnegyed.Prototype;

import com.szoftlab4.java9esharomnegyed.Arena;
import com.szoftlab4.java9esharomnegyed.Config;
import com.szoftlab4.java9esharomnegyed.Leaderboard;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Prototype {
    private ArrayList<String> inputCommands;
    private String[] args;
    private Arena arena;

    public Prototype(Arena inGame){
        arena = inGame;
    }

    //    args[0]= fileIn; args[1]= fileOut
    public void runPrototype(String[] args) throws IOException {
        //Beolvassuk a bemeneti fájlt
        initInput(args[0]);
        //Végrehajtjuk a parancsokat
        executeInput();
    }

    // beolvassa fájlt!
    private void initInput(String in) throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Config.TEST_FOLDER + in);
            br = new BufferedReader(fr);
            inputCommands = new ArrayList<String>();
            String lastLine;

            if(br != null) {
                lastLine = br.readLine();
                while (lastLine != null) {
                    inputCommands.add(lastLine);
                    lastLine = br.readLine();
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }
    }

    public void executeInput() throws IOException {
        //A parancsok értelmezése már a Command osztályban zajlik, miután beolvastuk az összes parancsot a bemeneti fájlból
        //Minden parancsot vélgrehajtunk
        Command command;
        for (String c : inputCommands){
            String[] cmd = c.split(" ");
            //A parancs neve a cmd tömb első eleme lesz
            String commandName = cmd[0];
            //A cmd tömb többi eleme a parancs argumentumai lesznek
            //Abban az esetben ha a tesztelő nem írt argumentumot,csak egy parancsot:
            String[] commandArguments = null;
            if (cmd.length>1) {
                commandArguments = new String[cmd.length - 1];
                for (int i = 1; i < cmd.length; i++)
                    commandArguments[i - 1] = cmd[i];
            }
            command = new Command(arena, commandName, commandArguments);
            command.execute();
        }
    }
}
