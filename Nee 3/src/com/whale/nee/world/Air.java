package com.whale.nee.world;

public class Air extends Tile {
	
	public Air(int x, int y, int z, World world) {
		super (x, y, z, 0, 100, 0, world);
	}
	
	public void update() {
		super.update();
		strength = maxStrength;
	}
}
