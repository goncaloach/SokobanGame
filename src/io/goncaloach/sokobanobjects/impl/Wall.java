package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.AbstractSObject;
import pt.iul.ista.poo.utils.Point2D;

public class Wall extends AbstractSObject {

    public Wall(Point2D pos) {
        super(pos, "Wall", 1, false);
    }

}
