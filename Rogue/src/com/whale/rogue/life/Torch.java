package com.whale.rogue.life;

import com.whale.rogue.effects.Fire;

public class Torch extends Entity {
	
	public static final int ID = 1;
	
	private Fire fire;
	
	public Torch(int x, int y, Life life) {
		super(ID, x, y, 0.25, life);
		fire = new Fire(x, y - position - 0.25, 1, life.game.effects);
	}
	
	public void update() {
		fire.update();
	}
}
