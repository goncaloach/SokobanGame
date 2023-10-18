package io.goncaloach.sokobanobjects;

import pt.iul.ista.poo.utils.Direction;

public interface ActiveObject {

    void action(Direction d, MovableObject obj);
}
