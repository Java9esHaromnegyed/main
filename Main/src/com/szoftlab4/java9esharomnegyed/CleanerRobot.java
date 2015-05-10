package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.swing.*;
import java.awt.*;

public class CleanerRobot extends AbstractRobot {

    private int cleanTime = Config.CLN_TIME;
    private Obstacle target = null;
    private Image image;

    //CleanerRobot konstruktor
    public CleanerRobot(Arena a, Dimension pos, int dir, int ID, int cTime){
        super();
        arena = a;
        position = pos;
        direction = dir;
        id = ID;
        cleanTime = cTime;
        speed = Config.SPD_DEFFAULT;
        target = findTarget(); //Initnél megkeresi a legközelebbi foltot, hogy egyből el tudjon arra indulni
        image = new JPanel().getToolkit().getImage(getClass().getResource(Config.CLEANER_ROBOT)).getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, Image.SCALE_DEFAULT);
    }

    public CleanerRobot(Arena arena, Dimension pos, int dir, int ID){
        this(arena, pos, dir, ID, Config.CLN_TIME);
    }

    //Takarító robot meghal
    @Override
    public void die() {
        OilSpot oilSpot = new OilSpot(position);
        dead = true;
        LogHelper.inline("cleanerRobotDied id: " + id);
        //Halálakor egy olajfolt kerül a helyére
        arena.addObstacle(oilSpot);
        LogHelper.inline("obstacleAdded type: OIL  pos: [" + position.width + "; " + position.height + "]");
    }

    //Takarító robot mozgatása
    @Override
    public void move() {
        //Ha van cél akadály és nincs a cél akadályon akkor lép
        if(target != null) {
            if (!position.equals(target.getPosition())) { // ha még nem állunk a célponton
                Dimension destination;
                if (!dead) {        // (Csak akkor ha nem halott)
                    destination = tryFromY();   // megpróbáljuk Y tengely felől megközelíteni
                    CleanerRobot c = arena.getCleanerRobot(destination, id);
                    if (c == null) {            // ha nem ütközünk
                        position = destination;     // lépjünk oda
                    } else {                    // ha igen akkor
                        // if(destination == target.getPosition())
                        Obstacle temp = findTarget();   // nézzük meg van e közelebb másik folt("induljunk másfele")
                        if(temp != target) {            // ha van új
                            target = temp;                  // célozzuk meg
                            move();                         // és próbáljunk afelé elindulni...(először Y tengelyen aztán...
                            return;
                        } else {                        // ha nincs
                            destination = tryFromX();       // próbáljunk meg X tengely felől közelíteni
                            c = arena.getCleanerRobot(destination, id);
                            if (c == null) {        // ha nincs robot
                                position = destination;     // lépjünk oda
                            } // viszont ha így sem jó, várunk
                        }           // és nem lépünk sehova(esetleg animálhatunk egy ütközés/lepattanást)...
                    }
                    LogHelper.inline("cleanerRobotMoved id: " + id + " pos: [" + position.width + "; " + position.height + "]");
                }
            } else {  //Egyébként takarít
                clean();    // önmagában figyeli hogy halott-e a robot
            }
        } else {
            target = findTarget();
            if(target != null) {
                move();
                return;
            }
        }
    }

    //X tengelyen indulunk meg a folt felé
    private Dimension tryFromX(){
        Dimension x = new Dimension();

        if (position.getWidth() < target.getPosition().getWidth()) {
            //X tengelyen lép előre
            x.setSize(position.getWidth() + speed, position.getHeight());
        } else if (position.getWidth() > target.getPosition().getWidth()) {
            //X tengelyen lép vissza
            x.setSize(position.getWidth() - speed, position.getHeight());
        }else if (position.getHeight() < target.getPosition().getHeight()) {
            //Y tengelyen lép előre
            x.setSize(position.getWidth(), position.getHeight() + speed);
        } else if (position.getHeight() > target.getPosition().getHeight()) {
            //Y tengelyen lép vissza
            x.setSize(position.getWidth(), position.getHeight() - speed);
        }

        return x;
    }

    //Y tengelyen indulunk meg a folt felé
    private Dimension tryFromY(){
        Dimension y = new Dimension();

        if (position.getHeight() < target.getPosition().getHeight()) {
            //Y tengelyen lép előre
            y.setSize(position.getWidth(), position.getHeight() + speed);
        } else if (position.getHeight() > target.getPosition().getHeight()) {
            //Y tengelyen lép vissza
            y.setSize(position.getWidth(), position.getHeight() - speed);
        } else if (position.getWidth() < target.getPosition().getWidth()) {
            //X tengelyen lép előre
            y.setSize(position.getWidth() + speed, position.getHeight());
        } else if (position.getWidth() > target.getPosition().getWidth()) {
            //X tengelyen lép vissza
            y.setSize(position.getWidth() - speed, position.getHeight());
        }

        return y;
    }


    // CLN_TIME idő után törli az alatta lévő foltot
    public void clean(){
        if(!dead){
            if(cleanTime > 0)
                //A takarításból hátralévő idő csökkentése
                cleanTime--;
            LogHelper.inline("cleanerRobotClean id: " + id + " pos: [" + position.width + "; " + position.height + "]");
            if(cleanTime == 0){
                //Ha a cleantime == 0 akkor eltűntetjük a foltot
                arena.removeObstacle(target);
                target = null;
                cleanTime = Config.CLN_TIME;
                LogHelper.inline("obstacleRemoved pos: [" + position.width + "; " + position.height + "]");
            }
        }
    }

    public Obstacle getTarget() {
        return target;
    }

    //élesben majd ez kell
    public Obstacle findTarget(){
        double diffLengthMax = -1;
        Obstacle targ = null;
        //Végig iterálunk az obstacle-ökön és mindegyikre megnézzük a távolságot
        for(Obstacle o : arena.getObstacleList()){
            Dimension diff = new Dimension();
            //Távolságot számolunk a pitagorasz tétel alapján
            diff.setSize(Math.abs(position.getHeight() - o.getPosition().getHeight()),
                    Math.abs(position.getHeight() - o.getPosition().getHeight()));
            double diffLength = Math.sqrt(Math.pow(diff.getHeight(),2) + Math.pow(diff.getWidth(),2));
            //Ha közelebbit találtunk akkor azt állítjuk be targetnek
            if (Double.compare(diffLengthMax, -1) > 0){
                if(diffLength < diffLengthMax){
                    diffLengthMax = diffLength;
                    targ = o;
                }
            } else { //Először az első obstacle távolságát adjuk meg inicializálásként
                diffLengthMax = diffLength;
                targ = o;
            }
        }
        return targ;
    }

    //csak tesztelésre
    public void setTarget(Obstacle target) {
        if(!dead)
            this.target = target;
    }

    public Image getImage() {
        return image;
    }
}
