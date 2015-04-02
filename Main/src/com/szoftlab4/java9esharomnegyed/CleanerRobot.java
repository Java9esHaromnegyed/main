package com.szoftlab4.java9esharomnegyed;

/**
 * Created by Ricsard on 2015.03.29..
 */
public class CleanerRobot extends AbstractRobot {

    private int cleanTime;
    Obstacle target = null;

    @Override
    public void turnLeft() {
        direction++;
        if (direction > Config.DIR_LEFT)     // when direction reached 4 we have to change it to 0.  direction only goes from 0 to 3
            direction = Config.DIR_UP;
    }

    @Override
    public void turnRight() {
        direction--;
        if(direction < Config.DIR_UP)       // when direction reached -1 we have to change it to 3.  direction only goes from 0 to 3
            direction = Config.DIR_LEFT;
    }

    @Override
    public void move() {

    }

    public void clean(){

    }

    public Obstacle getTarget() {
        return target;
    }

    public void setTarget(Obstacle target) {
        this.target = target;
    }
}
