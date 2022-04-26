package com.whale.nope.life;

import java.awt.Graphics;
import java.util.Random;
import com.whale.nope.main.Game;
import com.whale.nope.world.Tile;
import com.whale.nope.world.World;

public abstract class Creature {
	
	protected boolean jump;
	
	protected int x, y, xV, yV, width, height, speed;
	
	protected Game game;
	
	protected Random random = new Random();
	
	public Creature(int x, int y, int width, int height, int speed, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.game = game;
	}
	
	public void update() {
		yV = Math.min(16, yV + 1);
		if (xCollision()) {
			xV = 0;
			jump = true;
		}
		if (yCollision()) {
			yV = 0;
			jump = true;
		}
		x += xV;
		if (x < 0) {
			x += World.WIDTH * Tile.SIZE;
			if (this == game.getPlayer()) game.setXOffset(game.getXOffset() + World.WIDTH * Tile.SIZE);
		} else if (x >= World.WIDTH * Tile.SIZE) {
			x -= World.WIDTH * Tile.SIZE;
			if (this == game.getPlayer()) game.setXOffset(game.getXOffset() - World.WIDTH * Tile.SIZE);
		}
		y += yV;
	}
	
	public abstract void draw(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXV(int xV) {
		this.xV = xV;
	}
	
	public void setYV(int yV) {
		this.yV = yV;
	}
	
	public void jump() {
		if (jump) yV = -16;
		jump = false;
	}
	
	public boolean xCollision() {
		if (game.getWorld().isBlocked(x + xV, y) || game.getWorld().isBlocked(x + width + xV, y) || game.getWorld().isBlocked(x + xV, y + height) || game.getWorld().isBlocked(x + width + xV, y + height)) return true; else return false;
	}
	
	public boolean yCollision() {
		if (game.getWorld().isBlocked(x, y + yV) || game.getWorld().isBlocked(x + width, y + yV) || game.getWorld().isBlocked(x, y + height + yV) || game.getWorld().isBlocked(x + width, y + height + yV)) return true; else return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getXV() {
		return xV;
	}
	
	public int getYV() {
		return yV;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpeed() {
		return speed;
	}
}
