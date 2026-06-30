package com.whale.rogue.world;

public class Floor extends Tile {
	
	public static final int ID = 2;
	
	public Floor(int x, int y, World world) {
		super(ID, x, y, false, world);
	}
}
