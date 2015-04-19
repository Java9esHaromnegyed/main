package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import java.awt.*;

public class CleanerRobot extends AbstractRobot {

    private int cleanTime = Config.CLN_TIME;
    Obstacle target = null;

    public CleanerRobot(Arena a, Dimension pos, int dir, int ID, int cTime){
        super();
        arena = a;
        position = pos;
        direction = dir;
        id = ID;
        cleanTime = cTime;
        speed = Config.SPD_DEFFAULT;
        target = findTarget();
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
        //Ha nincs a cél akadályon akkor lép
        if(target != null) {
            if (!position.equals(target.getPosition())) {
                Dimension destination;
                //Csak akkor tehető meg ha nem halott
                if (!dead) {
                    destination = tryFromY();   // megpróbáljuk Y tengely felől megközelíteni
                    CleanerRobot c = arena.getCleanerRobot(destination, id);
                    if (c == null) {            // ha nem ütközünk
                        position = destination;     // lépjünk oda
                    } else {                    // ha igen akkor
                        // if(destination == target.getPosition())
                        // TODO: find new target if another robot cleaning yours
                        Obstacle temp = findTarget();   // nézzük meg van e közelebb másik folt("induljunk másfele")
                        if(temp != target) {            // ha van új
                            target = temp;                  // célozzuk meg
                            move();                         // és próbáljunk afelé elindulni...(először Y tengelyen aztán...
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
                clean();
            }
        } else {
            target = findTarget();
            if(target != null)
                move();
            else
                return;
        }
    }

    private Dimension tryFromX(){
        Dimension x = new Dimension();

        if (position.getWidth() < target.getPosition().getWidth()) {
            x.setSize(position.getWidth() + speed, position.getHeight());
        } else if (position.getWidth() > target.getPosition().getWidth()) {
            x.setSize(position.getWidth() - speed, position.getHeight());
        }else if (position.getHeight() < target.getPosition().getHeight()) {
            x.setSize(position.getWidth(), position.getHeight() + speed);
        } else if (position.getHeight() > target.getPosition().getHeight()) {
            x.setSize(position.getWidth(), position.getHeight() - speed);
        }

        return x;
    }

    private Dimension tryFromY(){
        Dimension y = new Dimension();

        if (position.getHeight() < target.getPosition().getHeight()) {
            y.setSize(position.getWidth(), position.getHeight() + speed);
        } else if (position.getHeight() > target.getPosition().getHeight()) {
            y.setSize(position.getWidth(), position.getHeight() - speed);
        } else if (position.getWidth() < target.getPosition().getWidth()) {
            y.setSize(position.getWidth() + speed, position.getHeight());
        } else if (position.getWidth() > target.getPosition().getWidth()) {
            y.setSize(position.getWidth() - speed, position.getHeight());
        }

        return y;
    }


    // CLN_TIME idő után törli az alatta lévő foltot
    public void clean(){
        if(!dead){
            if(cleanTime > 0)
                cleanTime--;
            LogHelper.inline("cleanerRobotClean id: " + id + " pos: [" + position.width + "; " + position.height + "]");
            if(cleanTime == 0){
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
        double length = 0;
        double diffLengthMax = -1;
        Obstacle targ = null;
        for(Obstacle o : arena.getObstacles()){
            Dimension diff = new Dimension();
            diff.setSize(Math.abs(position.getHeight() - o.getPosition().getHeight()),
                    Math.abs(position.getHeight() - o.getPosition().getHeight()));
            double diffLength = Math.sqrt(Math.pow(diff.getHeight(),2) + Math.pow(diff.getWidth(),2));
            if (Double.compare(diffLengthMax, -1) > 0){
                if(diffLength < diffLengthMax){
                    diffLengthMax = diffLength;
                    targ = o;
                }
            } else {
                diffLengthMax = diffLength;
                targ = o;
            }
        }
        //if(targ != null)
        //    LogHelper.inline("Target " + targ.toString());
        return targ;
    }

    //csak tesztelésre
    public void setTarget(Obstacle target) {
        if(!dead)
            this.target = target;
    }
}
