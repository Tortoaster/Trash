package com.whale.rogue.life;

import java.awt.Graphics;

import com.whale.rogue.main.Assets;
import com.whale.rogue.main.Window;
import com.whale.rogue.mech.Animator;
import com.whale.rogue.world.Tile;

public abstract class Entity {
	
	protected double position;
	
	protected int id, x, y, action;
	
	protected Animator[] animations = Assets.ENTITIES;
	
	protected final Life life;
	
	public Entity(int id, int x, int y, double position, Life life) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.position = position;
		this.life = life;
	}
	
	public void update() {}
	
	public void draw(Graphics g) {
		g.drawImage(animations[id].getImage(action, Window.getTime()), x * Tile.SIZE, (int) ((y - position) * Tile.SIZE), Tile.SIZE, Tile.SIZE, null);
	}
	
	public void act(double t) {}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
