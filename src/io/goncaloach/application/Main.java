package io.goncaloach.application;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

import static io.goncaloach.application.SokobanGame.MAP_HEIGHT;
import static io.goncaloach.application.SokobanGame.MAP_WIDTH;

public class Main {

    public static void main(String[] args) {
        ImageMatrixGUI.setSize(MAP_WIDTH, MAP_HEIGHT);
        SokobanGame s = SokobanGame.getInstance();
        ImageMatrixGUI.getInstance().registerObserver(s);
        ImageMatrixGUI.getInstance().go();
    }

}
