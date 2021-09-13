package pt.iul.ista.poo.utils;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author POO2016
 * @version 16-Mar-2018
 * 
 * Cardinal directions
 *
 */
public enum Direction implements Serializable {
	LEFT(new Vector2D(-1,0)), UP(new Vector2D(0,-1)), RIGHT(new Vector2D(1,0)), DOWN(new Vector2D(0,1));

	private Vector2D vector;

	Direction(Vector2D vector) {
		this.vector = vector;
	}
	
	public Vector2D asVector() {
		return vector;
	}
	
	public static Direction directionFor(int keyCode) {
		switch(keyCode){
			case KeyEvent.VK_DOWN: 
				return DOWN;
			case KeyEvent.VK_UP:
				return UP;
			case KeyEvent.VK_LEFT:
				return LEFT;
			case KeyEvent.VK_RIGHT:
				return RIGHT;
		}
		throw new IllegalArgumentException();
	}

	public static boolean isDirection(int lastKeyPressed) {		
		return lastKeyPressed >= KeyEvent.VK_LEFT && lastKeyPressed <= KeyEvent.VK_DOWN;
	}

	
	public static List<Point2D> getNeighbourhoodPoints(Point2D p) {
		
		List<Point2D> neighbours = new ArrayList<>();
		
		for (Direction d : values()) {
			Vector2D v = d.asVector();
			neighbours.add(p.plus(v));
		}
		
		return neighbours;
	}

	public static Direction random() {
		Random generator = new Random();
		return values()[generator.nextInt(values().length)];
	}
}
