package com.whale.nee.life;

import java.awt.Graphics;

import com.whale.nee.main.Assets;
import com.whale.nee.main.Game;

public abstract class Creature {
	
	protected double health, regen;
	
	public static final int SIZE = 8;
	
	protected int x, y, maxHealth;
	
	private int id;
	
	private Life life;
	
	public Creature(int x, int y, int health, double regen, int id, Life life) {
		this.x = x;
		this.y = y;
		this.health = health;
		maxHealth = health;
		this.regen = regen;
		this.id = id;
		this.life = life;
	}
	
	public void update() {
		if (health <= 0) life.remove(this);
		health = Math.min(maxHealth, health + regen);
	}
	
	public void draw(Graphics g) {
		g.drawImage(Assets.SPRITES[id], x * Game.SCALE, y * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
	}
}
