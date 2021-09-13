package Main;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ParedeRachada extends AbstractSObject implements ActiveObject{

	public ParedeRachada(Point2D point2d) {
		super(point2d, "Parede_Partida", 1, false);
	}

	@Override
	public void action(Direction d,MovableObject obj) {
		SokobanGame.getInstance().removeObj_GUI(this);
	}
	
}
