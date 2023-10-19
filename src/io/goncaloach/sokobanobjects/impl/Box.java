package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends MovableObject {

    public Box(Point2D pos) {
        super(pos, "Box", 2, false, false);
    }

}
