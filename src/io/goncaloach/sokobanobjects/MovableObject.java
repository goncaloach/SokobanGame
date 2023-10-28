package io.goncaloach.sokobanobjects;

import java.util.List;

import io.goncaloach.application.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class MovableObject extends AbstractSObject {

    private final boolean isStuckable;

    public MovableObject(Point2D point2d, String Name, int Layer, boolean isTraversable, boolean stuck) {
        super(point2d, Name, Layer, isTraversable);
        this.isStuckable = stuck;
    }

    public void move(Direction direction) {
        Point2D nextPosition = getPosition().plus(direction.asVector());
        moveThis(nextPosition);
        activateObject(direction);
    }

    public void moveThis(Point2D nextPosition) {
        SokobanGame sokoban = SokobanGame.getInstance();
        List<AbstractSObject> objectsAtNextPosition = sokoban.getObjectsAt(nextPosition);
        if (objectsAtNextPosition.isEmpty() || sokoban.isPositionTraversable(objectsAtNextPosition, this)){
            setPosition(nextPosition);
        }
    }


    public void activateObject(Direction d) {
        SokobanGame sokoban = SokobanGame.getInstance();
        List<AbstractSObject> list = sokoban.getObjectsAt(getPosition());
        for (AbstractSObject o : list) {
            if (o instanceof ActiveObject)
                ((ActiveObject) o).action(d, this);
        }
    }


    public boolean isStuckable() {
        return isStuckable;
    }


}
