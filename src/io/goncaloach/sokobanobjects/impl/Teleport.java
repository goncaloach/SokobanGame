package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractSObject implements ActivatableObject {

    public Teleport(Point2D point2d) {
        super(point2d, "Green_Portal", STATIC_LAYER, true);
    }

    @Override
    public void action(Direction direction, MovableObject movedObject) {
        SokobanGame sokoban = SokobanGame.getInstance();
        Point2D positionToTeleport = null;
        for (AbstractSObject teleport : sokoban.getAllTeleports()) {
            if (!teleport.getPosition().equals(this.getPosition())) {
                positionToTeleport = teleport.getPosition();
            }
        }
        for (AbstractSObject object : sokoban.getObjectsAt(positionToTeleport)) {
            if (object instanceof MovableObject){
                return;
            }
        }
        movedObject.setPosition(positionToTeleport);
    }

}
