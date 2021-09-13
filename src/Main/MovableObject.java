package Main;

import java.util.List;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class MovableObject extends AbstractSObject{

	private boolean stuckable;
	
	public MovableObject(Point2D point2d, String Name, int Layer, boolean transp, boolean stuck) {
		super(point2d, Name, Layer, transp);
		this.stuckable=stuck;
	}
	
	public void move(Direction d) { 
		Point2D pos = getPosition().plus(d.asVector());
		moveThis(pos,d);
		activateObj(d);
	}
	
	public void moveThis(Point2D pos,Direction d) {
		SokobanGame soko = SokobanGame.getInstance();
		List<AbstractSObject> list = soko.getObjectsAt(pos);
		if(list.isEmpty() || soko.isTransp(list,this))
			setPosition(getPosition().plus(d.asVector()));
	}
	
	
	public void activateObj(Direction d) {
		SokobanGame soko = SokobanGame.getInstance();
		List<AbstractSObject> list = soko.getObjectsAt(getPosition());
		for(AbstractSObject o : list) {
			if(o instanceof ActiveObject)
				((ActiveObject) o).action(d,this);
		}
	}

	
	public boolean isStuckable() {
		return stuckable;
	}
	
	
}
