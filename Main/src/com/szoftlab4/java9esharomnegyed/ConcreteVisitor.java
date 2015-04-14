package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Interface.Visitor;

/**
 * Created by Ricsard on 2015.04.14..
 */
public class ConcreteVisitor implements Visitor {
    @Override
    public void effect(PuttySpot o) {
        System.out.println("putty");
    }

    @Override
    public void effect(OilSpot o) {
        System.out.println("oil");
    }

    @Override
    public void effect(Wall w) {
        System.out.println("wall");
    }
}
