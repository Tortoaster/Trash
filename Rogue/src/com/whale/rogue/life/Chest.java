package com.whale.rogue.life;

public class Chest extends Entity {
	
	public static final int ID = 3;
	
	public Chest(int x, int y, Life life) {
		super(ID, x, y, 0.25, life);
	}
}
