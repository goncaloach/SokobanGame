package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Ice extends AbstractSObject implements ActivatableObject {

    public Ice(Point2D point2d) {
        super(point2d, "Ice", 1, true);
    }

    @Override
    public void action(Direction direction, MovableObject obj) {
        SokobanGame sokoban = SokobanGame.getInstance();
        List<AbstractSObject> objectsAtNextPosition =
                sokoban.getObjectsAt(getPosition().plus(direction.asVector()));
        if (sokoban.isPositionTraversable(objectsAtNextPosition, obj)) {
            ImageMatrixGUI.getInstance().update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.move(direction);
        }
    }

}
