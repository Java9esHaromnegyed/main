package com.szoftlab4.java9esharomnegyed.Utility;

public class LogHelper {
    private static final int startLevel = 1;    //kezdő hierarchiai szint, default: 1, magasabb esetén 0. szint is kap tabot.
    private static int logLevel = startLevel;
    private static boolean record = true;

    public static void log(String log){     // kiír egy sort
        if (record)                         // csak ha logolás folyik
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
        if (record)
            logLevel++;
    }

    private static void close(){            // lezár egy hierarchiai szintet
        if (record)
            logLevel--;
    }

    public static void inline(Object object){   // hierarchiának megfelelően "kiír" egy sort
        String raw = String.valueOf(object);
        String fin;
        String tab = "";
        for(int i = 1; i < logLevel; i++){      // szintnek megfelelő tab előállítása
            tab = tab.concat("  ");
        }
        fin = tab.concat(raw);
        log(fin);
    }

    public static void logFirst(String first){  // első hierarchia szint, 0 tab
        clear();
        inline(first);
    }

    public static void call(String call){
        inline(call);
        lift();
    }

    public static void ret(String ret){
        close();
        inline(ret);
    }

    public static void comment(Object object){  // call utasítást kompenzáló egy szintet visszaugró log
        close();                                // visszalép
        inline(object);                         // logol
        lift();                                 // előre lép az eredeti pozícióba
    }



    public static void error(Object object){    // error trace try catch hierarchikus megjelenítéséhez
        String raw = String.valueOf(object);    // az error a megfelelő hierarchiánál áll meg
        String fin;
        String tab = "ERROR:\t";
        for(int i = 1; i < logLevel; i++){
            tab = tab.concat("  ");
        }
        fin = tab.concat(raw);
        log(fin);
    }
}
