package com.whale.rogue.world;

public class Wall extends Tile {
	
	public static final int ID = 0;
	
	public Wall(int x, int y, World world) {
		super(ID, x, y, 2 / 3.0, world);
	}
}
