package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractSObject implements ActivatableObject {

    public Hole(Point2D point2d) {
        super(point2d, "Hole", STATIC_LAYER, true);
    }

    @Override
    public void action(Direction direction, MovableObject movedObject) {
        SokobanGame sokoban = SokobanGame.getInstance();
        if (movedObject instanceof Forklift) {
            sokoban.gameOver();
            return;
        }
        if (movedObject.isStuckable()) {
            sokoban.removeObjectFromList(this);
            sokoban.removeObjectFromGUI(this);
            sokoban.addObjectToList(new Wall(getPosition()));
        } else {
            sokoban.removeObjectFromGUI(movedObject);
        }
        sokoban.removeObjectFromList(movedObject);
    }


}
