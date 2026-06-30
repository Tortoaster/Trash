package net.melting;

import java.awt.Graphics;

public abstract class Entity {
	
	protected int x, y;
	
	public Entity(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void setX(double x) {
		this.x = (int) (x * Tile.SIZE);
	}
	
	public void setY(double y) {
		this.y = (int) (y * Tile.SIZE);
	}
	
	public double getX() {
		return (double) x / Tile.SIZE;
	}
	
	public double getY() {
		return (double) y / Tile.SIZE;
	}
}
