package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.ReadWrite;
import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractSObject implements ActiveObject {

    public Target(Point2D point2d) {
        super(point2d, "Target", 1, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        int positionSet = 0;
        if (obj instanceof Caixote) {
            for (AbstractSObject i : soko.getAllObjects()) {
                if (i instanceof Target) {
                    List<AbstractSObject> list = soko.getObjectsAt(i.getPosition());
                    for (AbstractSObject j : list) {
                        if (j instanceof Caixote)
                            positionSet++;
                    }
                }
            }
        }
        if (positionSet == soko.getNumberOfTargets()) {
            if (soko.getLevel() >= 2) {
                ReadWrite.writeScore();
                System.out.println("The end :)");
                ImageMatrixGUI.getInstance().dispose();
                return;
            }
            ReadWrite.writeScore();
            System.out.println("next level");
            soko.incrementLevel();
            soko.restartLevel();

        }
    }


}
