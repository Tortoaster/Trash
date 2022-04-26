package com.whale.nee.world;

import java.awt.Graphics;

import com.whale.nee.main.Game;

public class World {
	
	public static final int WIDTH = 50, HEIGHT = 50;
	
	private Tile[][][] world = new Tile[WIDTH][HEIGHT][2];
	
	private Game game;
	
	public World(Game game) {
		this.game = game;
		for (int y = 0; y < world[0].length; y++) for (int x = 0; x < world.length; x++) {
			if (y == world[0].length / 2) {
				world[x][y][0] = new Grass(x, y, 0, this);
				world[x][y][1] = new Grass(x, y, 1, this);
			} else if (y > world[0].length / 2) {
				world[x][y][0] = new Ground(x, y, 0, this);
				world[x][y][1] = new Ground(x, y, 1, this);
			} else {
				world[x][y][0] = new Ground(x, y, 0, this);
				world[x][y][1] = new Air(x, y, 1, this);
			}
		}
	}
	
	public void update() {
		for (int z = 0; z < 2; z++) for (int y = 0; y < world[0].length; y++) for (int x = 0; x < world.length; x++) world[x][y][z].update();
	}
	
	public void draw(Graphics g) {
		for (int z = 0; z < 2; z++) for (int y = 0; y < world[0].length; y++) for (int x = 0; x < world.length; x++) world[x][y][z].draw(g);
	}
	
	public Tile getTile(int x, int y, int z) {
		return world[x][y][z];
	}
	
	public void setTile(int x, int y, int z, Tile tile) {
		world[x][y][z] = tile;
	}
}
