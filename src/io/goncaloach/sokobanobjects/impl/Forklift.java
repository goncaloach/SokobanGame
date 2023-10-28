package io.goncaloach.sokobanobjects.impl;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Forklift extends MovableObject {

    private boolean hasHammer = false;
    private int energy = 100;
    private int moves = 0;

    public Forklift(Point2D position) {
        super(position, "Forklift_U", 2, false, false);
    }

    @Override
    public void move(Direction direction) {
        Point2D nextPosition = getPosition().plus(direction.asVector());
        setName("Forklift_" + direction.toString().charAt(0));
        moveObjectInFront(nextPosition, direction);
        moveThis(nextPosition);
        activateObject(direction);
    }

    public void moveObjectInFront(Point2D nextPosition, Direction d) {
        SokobanGame sokoban = SokobanGame.getInstance();
        List<AbstractSObject> list = sokoban.getObjectsAt(nextPosition);
        for (AbstractSObject o : list) {
            if (o instanceof MovableObject)
                ((MovableObject) o).move(d);
        }
    }

    public boolean hasHammer() {
        return hasHammer;
    }

    public void setHasHammer(boolean b) {
        hasHammer = b;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void decEnergy() {
        energy--;
    }

    public void incMoves() {
        moves++;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int m) {
        moves = m;
    }


}
