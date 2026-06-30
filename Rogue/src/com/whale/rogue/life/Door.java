package com.whale.rogue.life;

public class Door extends Entity {
	
	public static final int ID = 0;
	
	public Door(int x, int y, Life life) {
		super(ID, x, y, 5 / 12.0, life);
	}
}
