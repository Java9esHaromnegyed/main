package com.szoftlab4.java9esharomnegyed;

import java.awt.*;

public class CleanerRobot extends AbstractRobot {

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

    private int cleanTime = Config.CLN_TIME;
    Obstacle target = null;

    @Override
    public void turnLeft() {
        if(!dead) {
            direction++;
            if (direction > Config.DIR_LEFT)     // when direction reached 4 we have to change it to 0.  direction only goes from 0 to 3
                direction = Config.DIR_UP;
        }
    }

    @Override
    public void turnRight() {
        if(!dead) {
            direction--;
            if (direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
                direction = Config.DIR_LEFT;
        }
    }

    @Override
    public void die() {
        PuttySpot puttySpot = new PuttySpot(position);
        dead = true;
        arena.addObstacle(puttySpot);
    }

    @Override
    public void move() {
        if(!dead){

        }
    }


    // CLN_TIME idő után törli az alatta lévő foltot
    public void clean(){
        if(!dead){
            if(cleanTime > 0)
                cleanTime--;
            else if(cleanTime == 0){
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
