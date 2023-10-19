package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractSObject implements ActiveObject {

    public Teleport(Point2D point2d) {
        super(point2d, "Green_Portal", 1, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        Point2D point = null;
        for (AbstractSObject i : soko.getAllObjects()) {
            if (i instanceof Teleport)
                if (!i.getPosition().equals(this.getPosition()))
                    point = i.getPosition();
        }
        List<AbstractSObject> list = soko.getObjectsAt(point);
        for (AbstractSObject i : list) {
            if (i instanceof MovableObject)
                return;
        }
        obj.setPosition(point);
    }

}
