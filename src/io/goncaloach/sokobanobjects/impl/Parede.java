package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.AbstractSObject;
import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractSObject {

    public Parede(Point2D pos) {
        super(pos, "Parede", 1, false);
    }

}
