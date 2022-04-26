package com.rook.life;

import com.rook.world.Tile;
import com.rook.world.World;

public class Skeleton extends Creature {
	
	public Skeleton(double x, double y, World world) {
		super(x, y, 15, 32, 1, Tile.SIZE, Tile.SIZE, 2, 8, 0, 3, 5, 5, Life.TEAM_ENEMY, Creature.HUMANOID, Creature.SKELETON, world);
	}
}
