package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractSObject implements ActiveObject {

    public Hole(Point2D point2d) {
        super(point2d, "Hole", 1, true);
    }

    @Override
    public void action(Direction d, MovableObject obj) {
        SokobanGame soko = SokobanGame.getInstance();
        if (obj.equals(soko.getPlayer()))
            soko.gameOver();
        if (obj.isStuckable()) {
            soko.removeObj(obj);
            soko.addObj(new Parede(getPosition()));
            soko.removeObj(this);
            return;
        }
        soko.removeObj_GUI(obj);
    }


}
