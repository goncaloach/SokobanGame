package Main;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractSObject implements ActiveObject{
	
	public Bateria(Point2D pos) {
		super(pos,"Bateria",1,true);
	}

	@Override
	public void action(Direction d,MovableObject obj) {
		SokobanGame soko = SokobanGame.getInstance();
			if(obj instanceof Empilhadora) 
				soko.getPlayer().setEnergia(101);
			soko.removeObj_GUI(this);		
	}
	
}
