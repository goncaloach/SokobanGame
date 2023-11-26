package io.goncaloach.sokobanobjects.impl;

import io.goncaloach.application.SokobanGame;
import io.goncaloach.sokobanobjects.MovableObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Forklift extends MovableObject {

    public static int MAX_ENERGY = 100;

    private boolean hasHammer = false;
    private int energy = MAX_ENERGY;
    private int moves = 0;

    public Forklift(Point2D position) {
        super(position, "Forklift_U", MOVABLE_LAYER, false, false);
    }

    @Override
    public void move(Direction direction) {
        SokobanGame sokobanGame = SokobanGame.getInstance();
        Point2D nextPosition = getPosition().plus(direction.asVector());
        super.setName("Forklift_" + direction.toString().charAt(0));
        int currentLevel = sokobanGame.getLevel();
        moveObjectInFront(nextPosition, direction);
        int levelAfterAction = sokobanGame.getLevel();
        if (currentLevel != levelAfterAction) { // this is a way to check if the level was completed
            return;
        }
        super.moveItself(nextPosition);
        super.activateObject(direction);
    }

    private void moveObjectInFront(Point2D nextPosition, Direction direction) {
        SokobanGame sokoban = SokobanGame.getInstance();
        sokoban.getObjectsAt(nextPosition).stream()
                .filter(object -> object instanceof MovableObject)
                .findFirst()
                .ifPresent(object -> ((MovableObject) object).move(direction));
    }

    public void updateStatsOnMove() {
        energy--;
        moves++;
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

    public void refillEnergy() {
        energy = MAX_ENERGY;
    }

}
