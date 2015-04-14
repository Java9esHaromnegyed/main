package com.szoftlab4.java9esharomnegyed.Interface;

import com.szoftlab4.java9esharomnegyed.*;

/**
 * Created by Ricsard on 2015.04.14..
 */
public interface Visitor {
    public void effect(PuttySpot p, Robot r);
    public void effect(OilSpot o, Robot r);
    public void effect(Wall w, Robot r);
}
