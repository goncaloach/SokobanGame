package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractSObject implements ActiveObject {

    public Battery(Point2D pos) {
        super(pos, "Battery", 1, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        if (obj instanceof Forklift)
            soko.getPlayer().setEnergy(101);
        soko.removeObj_GUI(this);
    }

}
