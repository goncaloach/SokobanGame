package io.goncaloach.sokobanobjects;

import io.goncaloach.application.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.util.List;

public abstract class MovableObject extends AbstractSObject {

    private final boolean isStuckable;

    public MovableObject(Point2D position, String name, int layer, boolean isTraversable, boolean isStuckable) {
        super(position, name, layer, isTraversable);
        this.isStuckable = isStuckable;
    }

    public void move(Direction direction) {
        Point2D nextPosition = getPosition().plus(direction.asVector());
        System.out.println("DEBUG: default move");
        moveItself(nextPosition);
        activateObject(direction);
    }

    public void moveItself(Point2D nextPosition) {
        System.out.println("DEBUG: move itself " + getName());
        SokobanGame sokoban = SokobanGame.getInstance();
        if (isPositionOutOfBounds(nextPosition)) {
            return;
        }
        List<AbstractSObject> objectsAtNextPosition = sokoban.getObjectsAt(nextPosition);
        if (sokoban.isPositionTraversable(objectsAtNextPosition, this)) {
            setPosition(nextPosition);
        }
    }

    private boolean isPositionOutOfBounds(Point2D nextPosition) {
        int width = nextPosition.getX();
        int height = nextPosition.getY();
        return width > SokobanGame.MAP_WIDTH - 1 || width < 0
                || height > SokobanGame.MAP_HEIGHT - 1 || height < 0;
    }

    public void activateObject(Direction direction) {
        System.out.println("DEBUG: activate object " + getName());
        SokobanGame sokoban = SokobanGame.getInstance();
        sokoban.getObjectsAt(getPosition()).stream()
                .filter(object -> object instanceof ActivatableObject)
                .findFirst()
                .ifPresent(object -> ((ActivatableObject) object).action(direction, this));
    }

    public boolean isStuckable() {
        return isStuckable;
    }

}
