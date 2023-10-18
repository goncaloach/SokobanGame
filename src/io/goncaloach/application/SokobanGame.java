package io.goncaloach.application;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActiveObject;
import io.goncaloach.sokobanobjects.impl.*;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;


public class SokobanGame implements Observer {

    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;
    private Empilhadora player;
    private final List<AbstractSObject> sokobanObjects = new ArrayList<>();
    private int level = 1;
    private int numberOfTargets;

    private static SokobanGame INSTANCE = null;

    private SokobanGame() {
        readMap(level);
        objsToGUI();
        setStatusMessage();
    }

    public static SokobanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SokobanGame();
        return INSTANCE;
    }

    private void readMap(int level) {
        readMap("levels//lvl_" + level);
    }

    private void readMap(String mapName) {
        try (Scanner scanner = new Scanner(new File("maps//" + mapName + ".txt"))) {
            int lineReadIndex = 0;
            while (scanner.hasNextLine() && lineReadIndex < MAP_HEIGHT) {
                processLine(lineReadIndex, scanner.nextLine());
                lineReadIndex++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void processLine(int y, String lineRead) {
        for (int x = 0; x != MAP_WIDTH; x++) {
            ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(x, y)));
            Optional<AbstractSObject> optionalSokobanObject =
                    AbstractSObject.createSokobanObject(lineRead.charAt(x), new Point2D(x, y));
            optionalSokobanObject.ifPresent(this::initializeVariablesFromObjects);
        }
    }

    private void initializeVariablesFromObjects(AbstractSObject sokobanObject) {
        if (sokobanObject instanceof Target) {
            numberOfTargets++;
        }
        if (sokobanObject instanceof Empilhadora) {
            player = (Empilhadora) sokobanObject;
        }
        sokobanObjects.add(sokobanObject);
    }

    public List<AbstractSObject> getObjectsAt(Point2D position) {
        List<AbstractSObject> list = new ArrayList<>();
        for (AbstractSObject i : sokobanObjects)
            if (i.getPosition().equals(position))
                list.add(i);
        return list;
    }


    public boolean isTraversable(List<AbstractSObject> list, AbstractSObject obj) {
        for (AbstractSObject i : list)
            if (!i.isTraversable()) {
                if (i instanceof ActiveObject) {
                    return obj instanceof Empilhadora && ((Empilhadora) obj).hasMartelo();
                }
                return false;
            }
        return true;
    }


    @Override
    public void update(Observed arg0) {
        int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();

        if (lastKeyPressed == KeyEvent.VK_R)
            restartLevel();

        if (lastKeyPressed == KeyEvent.VK_ESCAPE)
            ImageMatrixGUI.getInstance().dispose();

        if (Direction.isDirection(lastKeyPressed))
            player.move(Direction.directionFor(lastKeyPressed));

        if (player.getEnergia() == 0) {
            gameOver();
            return;
        }
        player.decEnergia();
        player.incMoves();
        ImageMatrixGUI.getInstance().update();
        setStatusMessage();
    }

    public void gameOver() {
        ImageMatrixGUI.getInstance().clearImages();
        sokobanObjects.clear();
        readMap("gameover");
        objsToGUI();
        player.setEnergia(1);
        ImageMatrixGUI.getInstance().setStatusMessage("GAME OVER - Press 'R' to restart "
                + "- Press 'ESC' to quit");
    }

    public void restartLevel() {
        ImageMatrixGUI.getInstance().clearImages();
        sokobanObjects.clear();
        numberOfTargets = 0;
        readMap(level);
        player.setEnergia(101);
        player.setMoves(-1);
        objsToGUI();
        setStatusMessage();
    }

    public void objsToGUI() {
        for (AbstractSObject o : sokobanObjects)
            ImageMatrixGUI.getInstance().addImage(o);
    }

    public void setStatusMessage() {
        ImageMatrixGUI.getInstance().setStatusMessage("Energy:" + player.getEnergia() +
                "   Moves:" + player.getMoves() + "   Level:" + level +
                "           Press 'R' to restart");
    }

    public void removeObj(AbstractSObject obj) {
        sokobanObjects.remove(obj);
    }

    public void addObj(AbstractSObject obj) {
        sokobanObjects.add(obj);
    }

    public void removeObj_GUI(AbstractSObject obj) {
        sokobanObjects.remove(obj);
        ImageMatrixGUI.getInstance().removeImage(obj);
    }

    public Empilhadora getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    public List<AbstractSObject> getAllObjects() {
        return new ArrayList<>(sokobanObjects);
    }

    public void incrementLevel() {
        level++;
    }

}
