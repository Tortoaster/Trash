package com.rook.life;

import com.rook.world.Tile;
import com.rook.world.World;

public class Human extends Creature {
	
	public static final int WARRIOR = 0, RANGER = 1, SORCERER = 2;
	
	public Human(double x, double y, int profession, World world) {
		super(x, y, 15, 48, 1.5, Tile.SIZE, Tile.SIZE, profession == WARRIOR ? 8 : profession == SORCERER ? 3 : 5, profession == RANGER ? 8 : profession == WARRIOR ? 3 : 5, profession == SORCERER ? 8 : profession == RANGER ? 3 : 5, 5, 5, 5, Life.TEAM_PLAYER, Creature.HUMANOID, Creature.HUMAN, world);
		intelligent = false;
	}
}
