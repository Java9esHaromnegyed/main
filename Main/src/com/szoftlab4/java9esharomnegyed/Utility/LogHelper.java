package com.szoftlab4.java9esharomnegyed.Utility;

public class LogHelper {
    private static final int startLevel = 1;    //kezdő hierarchiai szint, default: 1, magasabb esetén 0. szint is kap tabot.
    private static int logLevel = startLevel;
    private static boolean record = true;

    public static void log(String log){     // kiír egy sort, sortöréssel
        if (record)                             // csak ha logolás folyik
            System.out.println(log);
    }

    public static void rec(){               // folytatja a logolást
        record = true;
    }

    public static void pause(){             // leállítja a logolást amíg nem indítják újra
        record = false;
    }

    public static void clear(){             // nulláza a hierarchiai szintet
        logLevel = startLevel;
    }

    private static void lift(){             // egyet emel a hierarchiai szinten
        if (record)                             // csak ha van log
            logLevel++;
    }

    private static void close(){            // lezár egy hierarchiai szintet
        if (record)                             // csak ha van log
            logLevel--;
    }

    public static void inline(Object object){   // hierarchiának megfelelően "kiír" egy sort
        String raw = String.valueOf(object);
        String fin;
        String tab = "";
        for(int i = 1; i < logLevel; i++){          // szintnek megfelelő tab előállítása
            tab = tab.concat("  ");
        }
        fin = tab.concat(raw);                      // tab összefűzése a szöveggel
        log(fin);                                   // átadás a kiírónak
    }

    public static void logFirst(String first){  // első hierarchia szint, 0 tab
        clear();
        inline(first);
    }

    public static void call(String call){       // ezt használod a függvény elején
        inline(call);                               // kiírod majd
        lift();                                     // nyit egy bekezdést
    }

    public static void ret(String ret){         // ezt pedig a függvény végén a return előtt
        close();                                    // bezárod a bekezdést, visszalépsz egy tabot
        inline(ret);                                // kiírod amit akartál
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
