package Main;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    private Empilhadora player;
    private List<AbstractSObject> objs = new ArrayList<>();
    private int level = 0;
    private int numAlvos;

    private static SokobanGame INSTANCE = null;


    private SokobanGame() {
        readLevel(level + "");
        objsToGUI();
        setStatusMessage();
    }


    public static SokobanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SokobanGame();
        return INSTANCE;
    }


    public List<AbstractSObject> readLevel(String numlevel) {
        try {
            Scanner scan = new Scanner(new File("maps//levels//level" + numlevel + ".txt"));
            int y = 0;
            AbstractSObject obj = null;
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                for (int x = 0; x != 10; x++) {
                    ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(x, y)));
                    obj = AbstractSObject.createObj(s.charAt(x), new Point2D(x, y));
                    if (obj != null) {
                        if (obj instanceof Alvo)
                            numAlvos++;
                        if (obj instanceof Empilhadora)
                            player = (Empilhadora) obj;
                        objs.add(obj);
                    }
                }
                y++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("ficheiro nao encontrado");
        }
        return objs;
    }


    public List<AbstractSObject> getObjectsAt(Point2D position) {
        List<AbstractSObject> list = new ArrayList<AbstractSObject>();
        for (AbstractSObject i : objs)
            if (i.getPosition().equals(position))
                list.add(i);
        return list;
    }


    public boolean isTransp(List<AbstractSObject> list, AbstractSObject obj) {
        for (AbstractSObject i : list)
            if (!i.isTransponivel()) {
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
        objs.clear();
        readLevel("GameOver");
        objsToGUI();
        player.setEnergia(1);
        ImageMatrixGUI.getInstance().setStatusMessage("GAME OVER - Press 'R' to restart "
                + "- Press 'ESC' to quit");
    }

    public void restartLevel() {
        ImageMatrixGUI.getInstance().clearImages();
        objs.clear();
        numAlvos = 0;
        readLevel(level + "");
        player.setEnergia(101);
        player.setMoves(-1);
        objsToGUI();
        setStatusMessage();
    }

    public void objsToGUI() {
        for (AbstractSObject o : objs)
            ImageMatrixGUI.getInstance().addImage(o);
    }

    public void setStatusMessage() {
        ImageMatrixGUI.getInstance().setStatusMessage("Energy:" + player.getEnergia() +
                "   Moves:" + player.getMoves() + "   Level:" + level +
                "           Press 'R' to restart");
    }

    public void removeObj(AbstractSObject obj) {
        objs.remove(obj);
    }

    public void addObj(AbstractSObject obj) {
        objs.add(obj);
    }

    public void removeObj_GUI(AbstractSObject obj) {
        objs.remove(obj);
        ImageMatrixGUI.getInstance().removeImage(obj);
    }

    public Empilhadora getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public int getNumAlvos() {
        return numAlvos;
    }

    public List<AbstractSObject> getAllObjs() {
        return new ArrayList<>(objs);
    }

    public void incrementLevel() {
        level++;
    }

}
