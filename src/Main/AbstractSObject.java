package Main;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractSObject implements ImageTile{

	private Point2D position;
	private String name;
	private int layer;
	private boolean transponivel;
	

	public AbstractSObject(Point2D point2d, String Name, int Layer, boolean transp) {
		this.position = point2d;
		this.name=Name;
		this.layer=Layer;
		this.transponivel=transp;
	}

	
	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public Point2D getPosition() {
		return position;
	}

	
	@Override
	public int getLayer() {
		return layer;
	}

	
	public boolean isTransponivel() {
		return transponivel;
	}

	
	public void setPosition(Point2D point2d) {
		position = point2d;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setTransponivel(boolean transponivel) {
		this.transponivel = transponivel;
	}


	public static AbstractSObject createObj(char c, Point2D point) {
		
		switch(c){
		
			case '#' : return new Parede(point);
			case 'C' : return new Caixote(point);
			case 'E' : return new Empilhadora(point);
			case 'X' : return new Alvo(point); 
			case 'b' : return new Bateria(point);
			case 'O' : return new Buraco(point);
			case 'p' : return new SmallStone(point);
			case 'P' : return new BigStone(point);
			case 'g' : return new Gelo(point);
			case '%' : return new ParedeRachada(point);
			case 'm' : return new Martelo(point);
			case 't' : return new Teleporte(point);
			
			default: return null;
		}	
		
	}

	

}
