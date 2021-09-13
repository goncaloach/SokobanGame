package Main;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Martelo extends AbstractSObject implements ActiveObject {
	
	public Martelo(Point2D point2d) {
		super(point2d, "Martelo", 2, true);
		
	}

	@Override
	public void action(Direction d,MovableObject obj) {
		SokobanGame soko = SokobanGame.getInstance();
			if(obj instanceof Empilhadora) 
				soko.getPlayer().setMartelo(true);
			soko.removeObj_GUI(this);			
	}	
	
}
