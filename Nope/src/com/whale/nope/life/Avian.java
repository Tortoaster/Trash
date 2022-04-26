package com.whale.nope.life;

import com.whale.nope.main.Game;

public abstract class Avian extends Creature {
	
	protected boolean flying = random.nextBoolean();
	
	private int flight;
	
	public Avian(int x, int y, int width, int height, int speed, Game game) {
		super(x, y, width, height, speed, game);
		flight = y;
	}
	
	public void update() {
		super.update();
		if (flying) {
			if (xV == 0) {
				while (xV == 0) xV = random.nextInt(3) - 1;
				xV *= speed;
			}
			if (y > flight) jump();
			jump = true;
			if (xCollision()) xV *= -1;
			if (random.nextInt(500) == 0) {
				flying = false;
				xV = 0;
			}
		} else if (random.nextInt(500) == 0) {
			flying = true;
			flight = y - 150;
			yV -= 16;
		}
	}
}
