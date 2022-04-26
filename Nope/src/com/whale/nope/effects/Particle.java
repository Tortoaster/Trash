package com.whale.nope.effects;

import java.awt.Graphics;
import java.util.Random;
import com.whale.nope.main.Loop;

public abstract class Particle {
	
	protected double x, y, xV, yV;
	
	protected int age, time;
	
	protected Effects effects;
	
	protected Random random = new Random();
	
	public Particle(double x, double y, double xV, double yV, double time, Effects effects) {
		this.x = x;
		this.y = y;
		this.xV = xV;
		this.yV = yV;
		this.time = (int) (time * Loop.UPS);
		this.effects = effects;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
}
