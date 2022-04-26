package com.whale.nee.main;

import java.awt.Graphics;

public abstract class Entity {
	
	protected double x, y, xV, yV;
	
	protected int z;
	
	protected Handler handler;
	
	public Entity(double x, double y, int z, double xV, double yV, Handler handler) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.xV = xV;
		this.yV = yV;
		this.handler = handler;
	}
	
	public void update() {
		x += xV;
		y += yV;
		yV = Math.min(1, yV + 0.2);
		if (y >= 16) handler.remove(this);
	}
	
	public abstract void draw(Graphics g);
}
