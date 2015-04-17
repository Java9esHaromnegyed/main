package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class CleanerRobot extends AbstractRobot {

    private int cleanTime = Config.CLN_TIME;
    Obstacle target = null;
    Dimension directionVector = new Dimension();

    CleanerRobot(Arena a, Dimension pos, int dir, int ID, int cTime){
        super();
        arena = a;
        position = pos;
        direction = dir;
        id = ID;
        cleanTime = cTime;
        speed = Config.SPD_DEFFAULT;
        target = findTarget();
    }

    CleanerRobot(Arena arena, Dimension pos, int dir, int ID){
        this(arena, pos, dir, ID, Config.CLN_TIME);
    }

    //Takarító robot meghal
    @Override
    public void die() {
        OilSpot oilSpot = new OilSpot(position);
        dead = true;
        //Halálakor egy olajfolt kerül a helyére
        arena.addObstacle(oilSpot);
    }

    //Takarító robot mozgatása
    @Override
    public void move() {
        //Ha nincs a cél akadályon akkor lép
        if(target != null) {
            if (position != target.getPosition()) {
                Dimension destination = target.getPosition();
                //Csak akkor tehető meg ha nem halott
                if (!dead) {
                    position.setSize(position.getWidth() + (directionVector.getWidth()*speed),
                            position.getHeight() + (directionVector.getHeight()*speed));
                }
            } else {  //Egyébként takarít
                clean();
            }
        } else {
            target = findTarget();
            move();
        }
    }


    // CLN_TIME idő után törli az alatta lévő foltot
    public void clean(){
        if(!dead){
            if(cleanTime > 0)
                cleanTime--;
            if(cleanTime == 0){
                arena.removeObstacle(target);
                target = null;
                cleanTime = Config.CLN_TIME;
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
        for(Obstacle o : arena.getObstacles()){
            Dimension diff = new Dimension();
            diff.setSize(Math.abs(position.getHeight() - o.getPosition().getHeight()),
                    Math.abs(position.getHeight() - o.getPosition().getHeight()));
            double diffLength = Math.sqrt(Math.pow(diff.getHeight(),2) + Math.pow(diff.getWidth(),2));
            if (Double.compare(diffLengthMax, -1) > 0){
                if(diffLength < diffLengthMax){
                    diffLengthMax = diffLength;
                    directionVector.setSize((position.getHeight() - o.getPosition().getHeight()) / diffLengthMax,
                            (position.getHeight() - o.getPosition().getHeight()) / diffLengthMax);
                }
            } else {
                diffLengthMax = diffLength;
                directionVector.setSize((position.getHeight() - o.getPosition().getHeight()) / diffLengthMax,
                        (position.getHeight() - o.getPosition().getHeight()) / diffLengthMax);
            }
        }
    }

    //csak tesztelésre
    public void setTarget(Obstacle target) {
        if(!dead)
            this.target = target;
    }
}
