package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Forklift extends MovableObject {

    public static int DEFAULT_ENERGY = 100;

    private boolean hasHammer = false;
    private int energy = DEFAULT_ENERGY;
    private int moves = 0;

    public Forklift(Point2D position) {
        super(position, "Forklift_U", 2, false, false);
    }

    @Override
    public void move(Direction direction) {
        Point2D nextPosition = getPosition().plus(direction.asVector());
        super.setName("Forklift_" + direction.toString().charAt(0));
        moveObjectInFront(nextPosition, direction);
        super.moveItself(nextPosition);
        super.activateObject(direction);
    }

    public void moveObjectInFront(Point2D nextPosition, Direction direction) {
        SokobanGame sokoban = SokobanGame.getInstance();
        sokoban.getObjectsAt(nextPosition).stream()
                .filter(object -> object instanceof MovableObject)
                .map(object -> (MovableObject) object)
                .forEach(movableObject -> movableObject.move(direction));
    }

    public boolean hasHammer() {
        return hasHammer;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMoves() {
        return moves;
    }

    public void addHammer() {
        hasHammer = true;
    }

    public void decEnergy() {
        energy--;
    }

    public void incMoves() {
        moves++;
    }

    public void resetMoves() {
        moves = 0;
    }

    public void resetEnergy() {
        this.energy = DEFAULT_ENERGY;
    }

}
