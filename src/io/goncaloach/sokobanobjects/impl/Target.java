package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.ScorePrinterWriter;
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
    public void action(Direction direction, MovableObject movableObject) {
        SokobanGame sokoban = SokobanGame.getInstance();
        int positionSet = 0;
        if (movableObject instanceof Box) {
            for (AbstractSObject i : sokoban.getAllObjects()) {
                if (i instanceof Target) {
                    List<AbstractSObject> list = sokoban.getObjectsAt(i.getPosition());
                    for (AbstractSObject j : list) {
                        if (j instanceof Box)
                            positionSet++;
                    }
                }
            }
        }
        if (positionSet == sokoban.getNumberOfTargets()) {
            if (sokoban.getLevel() >= 2) {
                ScorePrinterWriter.writeScore();
                System.out.println("INFO: The end, thank you for playing!");
                ImageMatrixGUI.getInstance().dispose();
                return;
            }
            ScorePrinterWriter.writeScore();
            System.out.println("INFO: Next Level");
            sokoban.incrementLevel();
            sokoban.restartLevel();
        }
    }


}
