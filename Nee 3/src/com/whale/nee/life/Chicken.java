package com.whale.nee.life;

public class Chicken extends Creature {
	
	public Chicken(int x, int y, Life life) {
		super (x, y, 10, -0.1, (int) (Math.random() * 3) + 3, life);
	}
}
