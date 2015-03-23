package com.szoftlab4.java9esharomnegyed.Utility;

import java.util.ArrayList;

//Szkeleton logolás segítésére
public class LogHelper {

    //Osztály mezői
    private static final int startLevel = 1;    //kezdő hierarchiai szint, default: 1, magasabb esetén 0. szint is kap tabot.
    private static int logLevel = startLevel;
    private static ArrayList<Integer> hArchNum;
    private static int record = 0;      // felvételi szint ha 0 akkor logol ha bármi nagyobb akkor nem

    public LogHelper(){
        hArchNum = new ArrayList<Integer>();
        //Kezdő számozás elindítása
        hArchNum.add(1);
    }

    public static void log(String log){     // kiír egy sort, sortöréssel
        if (record == 0)                             // csak ha logolás folyik
            System.out.println(log);
    }

    public static void rec(){               // folytatja a logolást
        record--;
        if(record < 0)
            LogHelper.error("rec: " + record);
    }

    public static void pause(){             // leállítja a logolást amíg nem indítják újra
        record++;
    }

    public static void clear(){             // nulláza a hierarchiai szintet
        logLevel = startLevel;
        hArchNum = new ArrayList<Integer>();
        hArchNum.add(1);
    }

    private static void lift(){             // egyet emel a hierarchiai szinten
        if (record == 0)                             // csak ha van log
            logLevel++;
    }

    private static void close(){            // lezár egy hierarchiai szintet
        if (record == 0)                             // csak ha van log
            logLevel--;
    }

    public static void inline(Object object){   // hierarchiának megfelelően "kiír" egy sort
        LogHelper.pause();                        // amíg kiírunk nem jöhet új log
        String raw = String.valueOf(object);
        String fin;
        String tab = "";
        for(int i = 1; i < logLevel; i++){          // szintnek megfelelő tab előállítása
            tab = tab.concat("    ");
        }
        fin = tab.concat(raw);                      // tab összefűzése a szöveggel
        LogHelper.rec();                          // viszont újra kell indítani
        log(fin);                                   // átadás a kiírónak
    }

    public static void logFirst(String first){  // első hierarchia szint, 0 tab
        clear();
        inline("1. " + first);
        hArchNum.add(1);
    }

    public static void call(String call){                   // ezt használod a függvény elején
        String hArch = "";
        if (record == 0) {
            hArch = hArchNum.get(0).toString();              // hierarchikus szám első eleme
            for (int i = 1; i < hArchNum.size(); i++) {               // többi legenerálása
                hArch = hArch.concat("." + hArchNum.get(i).toString());
            }
            hArchNum.add(1);                                        // új szintre kezdőértéket beállítani
        }
        inline(hArch + ". " + call);                            // kiírod majd
        lift();                                                 // nyit egy bekezdést
    }

    public static void ret(String ret){                     // ezt pedig a függvény végén a return előtt
        close();                                                // bezárod a bekezdést, visszalépsz egy tabot
        if (record == 0) {
            for (int i = hArchNum.size() - 1; i >= logLevel; i--) {   // bezárt hierarchikus szintel együtt
                hArchNum.remove(i);                                 // a számozás addigi szintjét is törölni kell
            }
            hArchNum.set(hArchNum.size() - 1,
                    hArchNum.get(hArchNum.size() - 1) + 1);         // szinten lévő szám növelése
        }
        inline(ret);                                            // kiírod amit akartál
    }

    public static void comment(Object object){  // call utasítást kompenzáló egy szintet visszaugró log
        close();                                    // visszalép
        inline(object);                             // logol
        lift();                                     // előre lép az eredeti pozícióba
    }

    public static void question(String question){
        String fin;
        String tab = "";
        for(int i = 1; i < logLevel; i++){      // szintnek megfelelő tab előállítása
            tab = tab.concat("  ");
        }
        fin = tab.concat(question);                 // összefűzi a tabozást a kérdéssel
        System.out.print(fin);                      // kiírja, sortörés nélkül
    }



    public static void error(Object object){    // error trace try catch hierarchikus megjelenítéséhez
        String raw = String.valueOf(object);        // az error a megfelelő hierarchiánál áll meg
        String fin;
        String tab = "ERROR:\t";
        for(int i = 1; i < logLevel; i++){
            tab = tab.concat("  ");
        }
        fin = tab.concat(raw);                      // összefűzi a tabozást az error üzenettel
        log(fin);                                   // átadás a kiírónak
    }
}
