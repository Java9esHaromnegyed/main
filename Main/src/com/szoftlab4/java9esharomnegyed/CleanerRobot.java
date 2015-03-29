package com.szoftlab4.java9esharomnegyed;

/**
 * Created by Ricsard on 2015.03.29..
 */
public class CleanerRobot extends AbstractRobot {

    private int age = 0;
    Obstacle target = null;

    @Override
    public void move() {

    }

    public void clean(){

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Obstacle getTarget() {
        return target;
    }

    public void setTarget(Obstacle target) {
        this.target = target;
    }
}
