package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends AbstractSObject implements ActiveObject {

    public Hammer(Point2D point2d) {
        super(point2d, "Hammer", 2, true);

    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        if (obj instanceof Forklift)
            soko.getPlayer().setHasHammer(true);
        soko.removeObj_GUI(this);
    }

}
