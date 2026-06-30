package net.melting;

public class Tile {
	
	private boolean solid;
	
	public static final int SIZE = 16;
	
	private int id;
	
	public static final Tile AIR = new Tile(-1, false), BRICKS = new Tile(0, true), ICE = new Tile(1, true), TUT = new Tile(2, true);
	
	public Tile(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return solid;
	}
}
