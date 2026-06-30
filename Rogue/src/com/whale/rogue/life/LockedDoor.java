package com.whale.rogue.life;

public class LockedDoor extends Entity {
	
	public static final int ID = 2;
	
	public LockedDoor(int x, int y, Life life) {
		super(ID, x, y, 5 / 12.0, life);
	}
}
