package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends MovableObject {

    public SmallStone(Point2D point2d) {
        super(point2d, "SmallStone", MOVABLE_LAYER, false, false);
    }

}
