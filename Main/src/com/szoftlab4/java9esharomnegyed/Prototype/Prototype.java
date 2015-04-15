package com.szoftlab4.java9esharomnegyed.Prototype;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Abel on 2015.04.15..
 */
public class Prototype {
    private ArrayList<String> inputCommands;
    private ArrayList<String> outputMessages;
    private String[] args;

    public Prototype(String[] a){
        args=a;
    }

    //    args[0]= fileIn; args[1]= fileOut
    public void runPrototype(String[] args) throws IOException {
        //Beolvassuk a bemeneti fájlt
        initInput(args[0]);
        //Végrehajtjuk a parancsokat
        executeInput();
    }

    private void initInput(String in) throws IOException {
        FileReader fr =null;
        BufferedReader br =null;
        try {
            fr = new FileReader(in);
            br = new BufferedReader(fr);
            inputCommands = new ArrayList<String>();
            String lastLine;
            //Soronként beolvassuk a bemeneti fájlt
            while ((lastLine = br.readLine()) != null)
                inputCommands.add(new String(lastLine));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br!=null) br.close();
            if (fr!=null) fr.close();
        }
    }

    public void executeInput() throws IOException {

        //A parancsok értelmezése már a Command osztályban zajlik, miután beolvastuk az összes parancsot a bemeneti fájlból
        //Minden parancsot vélgrehajtunk
        Command command;
        for (String c : inputCommands){
            String[] cmd=c.split(" ");
            //A parancs neve a cmd tömb első eleme lesz
            String commandName = cmd[0];
            //A cmd tömb többi eleme a parancs argumentumai lesznek
            String[] commandArguments = new String[cmd.length-1];
            for (int i=1; i<cmd.length;i++)
                commandArguments[i-1]=cmd[i];
            command=new Command(commandName, commandArguments);
            //A kimeneti üzenetek közé felvesszük a legutóbb végrehajtott parancs visszatérési értékét
            outputMessages.add(command.execute());
        }
    }
}