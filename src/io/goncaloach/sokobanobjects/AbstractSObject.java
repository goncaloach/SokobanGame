package io.goncaloach.sokobanobjects;

import io.goncaloach.sokobanobjects.impl.*;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Optional;

public abstract class AbstractSObject implements ImageTile {

    private Point2D position;
    private String name;
    private final int layer;
    private final boolean isTraversable;


    public AbstractSObject(Point2D position, String name, int layer, boolean isTraversable) {
        this.position = position;
        this.name = name;
        this.layer = layer;
        this.isTraversable = isTraversable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Optional<AbstractSObject> createSokobanObject(char objectCode, Point2D position) {

        return switch (objectCode) {
            case '#' -> Optional.of(new Wall(position));
            case 'C' -> Optional.of(new Box(position));
            case 'E' -> Optional.of(new Forklift(position));
            case 'X' -> Optional.of(new Target(position));
            case 'b' -> Optional.of(new Battery(position));
            case 'O' -> Optional.of(new Hole(position));
            case 'p' -> Optional.of(new SmallStone(position));
            case 'P' -> Optional.of(new BigStone(position));
            case 'g' -> Optional.of(new Ice(position));
            case '%' -> Optional.of(new BrokenWall(position));
            case 'm' -> Optional.of(new Hammer(position));
            case 't' -> Optional.of(new Teleport(position));
            default -> Optional.empty();
        };

    }


}
