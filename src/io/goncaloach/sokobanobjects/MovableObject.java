package io.goncaloach.sokobanobjects;

import io.goncaloach.application.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.util.List;
import java.util.Objects;

public abstract class MovableObject extends AbstractSObject {

    private final boolean isStuckable;

    public MovableObject(Point2D position, String name, int layer, boolean isTraversable, boolean isStuckable) {
        super(position, name, layer, isTraversable);
        this.isStuckable = isStuckable;
    }

    public void move(Direction direction) {
        Point2D nextPosition = getPosition().plus(direction.asVector());
        moveItself(nextPosition);
        activateObject(direction);
    }

    public void moveItself(Point2D nextPosition) {
        SokobanGame sokoban = SokobanGame.getInstance();
        List<AbstractSObject> objectsAtNextPosition = sokoban.getObjectsAt(nextPosition);
        if (sokoban.isPositionTraversable(objectsAtNextPosition, this)) {
            setPosition(nextPosition);
        }
    }

    public void activateObject(Direction direction) {
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
