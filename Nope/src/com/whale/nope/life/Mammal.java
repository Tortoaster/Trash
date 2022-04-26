package com.whale.nope.life;

import com.whale.nope.main.Game;
import com.whale.nope.world.Tile;
import com.whale.nope.world.World;

public abstract class Mammal extends Creature {
	
	private int xD;
	
	public Mammal(int x, int y, int width, int height, int speed, Game game) {
		super(x, y, width, height, speed, game);
		xD = x;
	}
	
	public void update() {
		super.update();
		if (x == xD && random.nextInt(100) == 0) {
			xD = x + random.nextInt(20 * Tile.SIZE + 1) - 10 * Tile.SIZE / 2;
			if (xD < 0) xD += World.WIDTH * Tile.SIZE; else if (xD >= World.WIDTH * Tile.SIZE) xD -= World.WIDTH * Tile.SIZE;
		}
		if (xV < 0 && x < xD) xD = x; else if (xV > 0 && x > xD) xD = x;
		if (x < xD) xV = Math.min(speed, xV + 1); else if (x > xD) xV = Math.max(-speed, xV - 1);
		if (xCollision()) jump();
	}
}
