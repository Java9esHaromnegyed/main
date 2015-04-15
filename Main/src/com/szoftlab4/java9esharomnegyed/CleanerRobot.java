package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class CleanerRobot extends AbstractRobot {

    private int cleanTime = Config.CLN_TIME;
    Obstacle target = null;

    CleanerRobot(Arena a, Dimension pos, int dir, int ID, int cTime){
        super();
        arena = a;
        position = pos;
        direction = dir;
        id = ID;
        cleanTime = cTime;
    }

    CleanerRobot(Arena arena, Dimension pos, int dir, int ID){
        this(arena, pos, dir, ID, Config.CLN_TIME);
    }

    @Override
    public void die() {
        PuttySpot puttySpot = new PuttySpot(position);
        dead = true;
        arena.addObstacle(puttySpot);
    }

    @Override
    public void move() {
        if(position != target.getPosition()) {
            Dimension destination = position;
            //Csak akkor tehető meg ha nem halott
            if (!dead) {
                switch (direction) {
                    case Config.DIR_UP:
                        destination.setSize(destination.width, destination.height + speed);
                        break;
                    case Config.DIR_RIGHT:
                        destination.setSize(destination.width + speed, destination.height);
                        break;
                    case Config.DIR_DOWN:
                        destination.setSize(destination.width, destination.height - speed);
                        break;
                    case Config.DIR_LEFT:
                        destination.setSize(destination.width - speed, destination.height);
                        break;
                }
            }
        } else {
            clean();
        }
    }


    // CLN_TIME idő után törli az alatta lévő foltot
    public void clean(){
        if(!dead){
            if(cleanTime > 0)
                cleanTime--;
            if(cleanTime == 0){
                arena.removeObstacle(target);
                cleanTime = Config.CLN_TIME;
            }
        }
    }

    public Obstacle getTarget() {
        return target;
    }

    //élesben majd ez kell
    public void findTarget(){

    }

    //csak tesztelésre
    public void setTarget(Obstacle target) {
        if(!dead)
            this.target = target;
    }
}
