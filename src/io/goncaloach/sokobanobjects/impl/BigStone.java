package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends MovableObject {

    public BigStone(Point2D point2d) {
        super(point2d, "BigStone", MOVABLE_LAYER, false, true);
    }

}