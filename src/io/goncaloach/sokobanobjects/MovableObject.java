package io.goncaloach.sokobanobjects;

import io.goncaloach.application.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.util.List;

public abstract class MovableObject extends AbstractSObject {

    private final boolean isStuckable;

    public MovableObject(Point2D position, String name, int layer, boolean isTraversable, boolean stuck) {
        super(position, name, layer, isTraversable);
        this.isStuckable = stuck;
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

    public void activateObject(Direction d) {
        SokobanGame sokoban = SokobanGame.getInstance();
        sokoban.getObjectsAt(getPosition()).stream()
                .filter(object -> object instanceof ActivatableObject)
                .map(object -> (ActivatableObject) object)
                .forEach(activeObject -> activeObject.action(d, this));
    }

    public boolean isStuckable() {
        return isStuckable;
    }

}
