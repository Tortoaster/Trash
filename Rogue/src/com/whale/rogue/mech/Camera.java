package com.whale.rogue.mech;

import com.whale.rogue.main.Window;
import com.whale.rogue.world.Tile;

public class Camera {
	
	private int x, y, width, height;
	
	public void update(double x, double y) {
		this.x = (int) x - Window.WIDTH  / Tile.SIZE / 2;
		this.y = (int) y - Window.HEIGHT  / Tile.SIZE / 2;
		width = Window.WIDTH / Tile.SIZE;
		height = Window.HEIGHT / Tile.SIZE;
	}
	
	public int getX() {
		return x - 1;
	}
	
	public int getY() {
		return y - 1;
	}
	
	public int getWidth() {
		return width + 2;
	}
	
	public int getHeight() {
		return height + 3;
	}
}
