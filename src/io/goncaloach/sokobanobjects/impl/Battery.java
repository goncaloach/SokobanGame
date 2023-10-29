package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractSObject implements ActivatableObject {

    public Battery(Point2D pos) {
        super(pos, "Battery", STATIC_LAYER, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        if (obj instanceof Forklift)
            soko.getPlayer().resetEnergy(); //todo fix bug
        soko.removeObjectFromGUI(this);
    }

}
