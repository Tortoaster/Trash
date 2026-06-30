package com.whale.rogue.world;

import java.awt.Graphics;

import com.whale.rogue.main.Assets;

public abstract class Tile {
	
	protected boolean solid;
	
	protected double height;
	
	public static int SIZE = 19;
	protected int id, x, y;
	
	protected final World world;
	
	public Tile(int id, int x, int y, boolean solid, World world) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.solid = solid;
		this.world = world;
		height = 0;
	}
	
	public Tile(int id, int x, int y, double height, World world) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.height = height;
		this.world = world;
		solid = true;
	}
	
	public void update() {}
	
	public void draw(Graphics g) {
		g.drawImage(Assets.TERRAIN[id], x * SIZE, (int) ((y - height) * SIZE), SIZE, SIZE, null);
		g.drawImage(Assets.TERRAIN[id + (int) Math.sqrt(Assets.TERRAIN.length)], x * SIZE, (int) ((y - height + 1) * SIZE), SIZE, SIZE, null);
	}
}
