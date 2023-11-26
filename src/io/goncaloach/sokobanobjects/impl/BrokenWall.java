package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BrokenWall extends AbstractSObject implements ActivatableObject {

    public BrokenWall(Point2D point2d) {
        super(point2d, "Broken_Wall", STATIC_LAYER, false);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame sokoban = SokobanGame.getInstance();
        sokoban.removeObjectFromGUI(this);
        sokoban.removeObjectFromList(this);
    }

}
