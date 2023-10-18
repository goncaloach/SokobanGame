package Main;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

    public static void main(String[] args) {
        ImageMatrixGUI.setSize(10, 10);
        SokobanGame s = SokobanGame.getInstance();
        ImageMatrixGUI.getInstance().registerObserver(s);
        ImageMatrixGUI.getInstance().go();
    }

}
