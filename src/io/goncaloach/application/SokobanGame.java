package io.goncaloach.application;

import io.goncaloach.sokobanobjects.AbstractSObject;
import io.goncaloach.sokobanobjects.ActivatableObject;
import io.goncaloach.sokobanobjects.impl.*;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class SokobanGame implements Observer {

    private static SokobanGame INSTANCE = null;

    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;
    private static final int MAX_LEVEL = 3;

    private Forklift player;
    private final List<AbstractSObject> sokobanObjects = new ArrayList<>();
    private final List<Target> targets = new ArrayList<>();
    private int level = 1;
    private boolean isGameOver = false;

    private SokobanGame() {
        readMap(level);
        addObjectsToGUI();
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
            ImageMatrixGUI.getInstance().addImage(new Ground(new Point2D(x, y)));
            Optional<AbstractSObject> optionalSokobanObject =
                    AbstractSObject.createSokobanObject(lineRead.charAt(x), new Point2D(x, y));
            optionalSokobanObject.ifPresent(this::initializeVariablesFromObjects);
        }
    }

    private void initializeVariablesFromObjects(AbstractSObject sokobanObject) {
        if (sokobanObject instanceof Target) {
            targets.add((Target) sokobanObject);
        } else if (sokobanObject instanceof Forklift) {
            player = (Forklift) sokobanObject;
        }
        sokobanObjects.add(sokobanObject);
    }

    public List<AbstractSObject> getObjectsAt(Point2D position) {

        return sokobanObjects.stream()
                .filter(object -> object.getPosition().equals(position))
                .toList();
    }


    public boolean isPositionTraversable(List<AbstractSObject> objectsAtPosition, AbstractSObject objectToMove) {
        for (AbstractSObject object : objectsAtPosition) {
            if (!object.isTraversable()) {
                if (object instanceof ActivatableObject) {
                    return objectToMove instanceof Forklift && ((Forklift) objectToMove).hasHammer();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void update(Observed arg0) {
        int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();

        if (lastKeyPressed == KeyEvent.VK_R) {
            restartLevel();
            refreshScreen();
            return;
        }

        if (lastKeyPressed == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (isGameOver) {
            return;
        }

        if (Direction.isDirection(lastKeyPressed)) {
            player.move(Direction.directionFor(lastKeyPressed));
            player.decEnergy();
            player.incMoves();
            refreshScreen();
            setStatusMessage();
        }

        if (player.getEnergy() == 0) {
            gameOver();
            refreshScreen();
        }

    }

    private static void refreshScreen() {
        ImageMatrixGUI.getInstance().update();
    }

    private static void clearScreen() {
        ImageMatrixGUI.getInstance().clearImages();
    }

    public void gameOver() {
        isGameOver = true;
        clearScreen();
        sokobanObjects.clear();
        readMap("gameover");
        addObjectsToGUI();
        displayGameOverMessage();
    }

    public void restartLevel() {
        clearScreen();
        resetVariables();
        readMap(level);
        addObjectsToGUI();
        isGameOver = false;
        setStatusMessage();
    }

    private void resetVariables() {
        sokobanObjects.clear();
        targets.clear();
        player.resetEnergy();
        player.resetMoves();
    }

    public void addObjectsToGUI() {
        sokobanObjects.forEach(object -> ImageMatrixGUI.getInstance().addImage(object));
    }

    public void setStatusMessage() {
        ImageMatrixGUI.getInstance().setStatusMessage("Energy:" + player.getEnergy() +
                "   Moves:" + player.getMoves() + "   Level:" + level +
                "           Press 'R' to restart");
    }

    private static void displayGameOverMessage() {
        ImageMatrixGUI.getInstance().setStatusMessage("GAME OVER - Press 'R' to restart - Press 'ESC' to quit");
    }

    public void removeObjectFromList(AbstractSObject object) {
        sokobanObjects.remove(object);
    }

    public void addObjectToList(AbstractSObject obj) {
        sokobanObjects.add(obj);
    }

    public void removeObjectFromGUI(AbstractSObject obj) {
        sokobanObjects.remove(obj);
        ImageMatrixGUI.getInstance().removeImage(obj);
    }

    public Forklift getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public void checkIfLevelIsCompleted() {

        boolean isLevelCompleted = targets.stream().allMatch(this::containsBox);
        if (!isLevelCompleted) {
            return;
        }
        ScorePrinterWriter.writeScore();
        if (level == MAX_LEVEL) {
            endGame();
        }
        startNextLevel();
    }

    private void startNextLevel() {
        System.out.println("INFO: Next Level");
        level++;
        restartLevel(); //TODO check this
    }

    private void endGame() {
        System.out.println("INFO: The end, thank you for playing!");
        System.exit(0);
    }

    private boolean containsBox(Target target) {
        return getObjectsAt(target.getPosition()).stream()
                .anyMatch(object -> object instanceof Box);
    }

    public List<AbstractSObject> getAllTeleports() {
        return sokobanObjects.stream()
                .filter(object -> object instanceof Teleport)
                .toList();
    }

}
