package com.whale.rogue.life;

import com.whale.rogue.main.Assets;

public class Creature extends Entity {
	
	protected int id;
	
	public Creature(int id, int x, int y, Life life) {
		super(id, x, y, 5 / 12.0, life);
		this.id = id;
		animations = Assets.CREATURES;
	}
}
