package io.goncaloach.sokobanobjects.impl;

import Main.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractSObject implements ActiveObject {

    public Buraco(Point2D point2d) {
        super(point2d, "Buraco", 1, true);
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
