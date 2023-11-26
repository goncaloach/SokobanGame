package io.goncaloach.sokobanobjects;

import pt.iul.ista.poo.utils.Direction;

public interface ActivatableObject {

    void action(Direction d, MovableObject obj);
}
