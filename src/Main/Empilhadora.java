package Main;

import java.util.List;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Empilhadora extends MovableObject{
	
	private boolean martelo=false;
	private int energia=100;
	private int moves=0;
	
	public Empilhadora(Point2D position){
		super(position,"Empilhadora_D",2,false,false);
	}
	
	@Override
	public void move(Direction d) {
		Point2D pos = getPosition().plus(d.asVector());
		setName("Empilhadora_"+d.toString().charAt(0));
		moveObjInFront(pos,d);
		moveThis(pos,d);
		activateObj(d);	
	}	
	
	public void moveObjInFront(Point2D pos,Direction d) {
		SokobanGame soko = SokobanGame.getInstance();
		List<AbstractSObject> list = soko.getObjectsAt(pos);
		for(AbstractSObject o : list) {
			if(o instanceof MovableObject)
				((MovableObject) o).move(d);
		}
	}
	
	public boolean hasMartelo() {
		return martelo;
	}
	
	public void setMartelo(boolean b) {
		martelo=b;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public void decEnergia() {
		energia--;
	}
	
	public void incMoves() {
		moves++;
	}

	public int getMoves() {
		return moves;
	}
	
	public void setMoves(int m) {
		moves=m;
	}
	
	
}
