package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;

public class ConcreteVisitor implements Visitor {
    @Override
    public void effect(PuttySpot p, Robot r) {
        System.out.println("putty");
    }

    @Override
    public void effect(OilSpot o, Robot r) {
        System.out.println("oil");
    }

    @Override
    public void effect(Wall w, Robot r) {
        System.out.println("wall");
    }
}
