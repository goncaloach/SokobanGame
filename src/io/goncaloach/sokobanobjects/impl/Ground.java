package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.AbstractSObject;
import pt.iul.ista.poo.utils.Point2D;

public class Ground extends AbstractSObject {

    public Ground(Point2D pos) {
        super(pos, "Ground", BACKGROUND_LAYER, true);
    }

}
