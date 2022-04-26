package com.whale.nope.world;

import java.awt.Graphics;
import java.util.Random;

public abstract class Tile {
	
	protected boolean block;
	
	public static final int SIZE = 32;
	
	protected Random random = new Random();
	
	protected World world;
	
	public Tile(boolean block, World world) {
		this.block = block;
		this.world = world;
	}
	
	public void onHit(int x, int y) {
		world.setTile(x, y, new Air(world));
	}
	
	public void update(int x, int y) {
		
	}
	
	public void draw(int x, int y, Graphics g) {
		
	}
	
	public boolean isBlocked() {
		return block;
	}
}
