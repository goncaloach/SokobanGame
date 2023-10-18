package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import Main.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractSObject implements ActiveObject {

    public Gelo(Point2D point2d) {
        super(point2d, "Gelo", 1, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        List<AbstractSObject> list2 = soko.getObjectsAt(getPosition().plus(d.asVector()));
        if (soko.isTransp(list2, obj)) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ImageMatrixGUI.getInstance().update();
            obj.move(d);
        }
    }

}
