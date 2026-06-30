package com.whale.rogue.effects;

import java.awt.Color;
import java.awt.Graphics;

import com.whale.rogue.world.Tile;

public class Particle {
	
	private double x, y, size, a, vel, acc, aVel, aAcc;
	
	private int life, mLife;
	
	private Color start, end;
	
	private final Effects effects;
	
	public Particle(double x, double y, double size, double a, double vel, double acc, double aVel, double aAcc, int life, Color start, Color end, Effects effects) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.a = a;
		this.vel = vel;
		this.acc = acc;
		this.aVel = aVel;
		this.aAcc = aAcc;
		this.life = life;
		mLife = life;
		this.start = start;
		this.end = end;
		this.effects = effects;
	}
	
	public void update() {
		x += Math.cos(Math.toRadians(a)) * vel;
		y -= Math.sin(Math.toRadians(a)) * vel;
		vel += acc;
		a += aVel;
		aVel += aAcc;
		life--;
		if(life <= 0) {
			effects.remove(this);
		}
	}
	
	public void draw(Graphics g) {
		double age = (double) life / mLife;
		g.setColor(new Color(Math.max(0, Math.min(255, (int) (age * start.getRed() + (1 - age) * end.getRed()))), Math.max(0, Math.min(255, (int) (age * start.getGreen() + (1 - age) * end.getGreen()))), Math.max(0, Math.min(255, (int) (age * start.getBlue() + (1 - age) * end.getBlue())))));
		g.fillRect((int) (x * Tile.SIZE - (age * size * Tile.SIZE) / 2), (int) (y * Tile.SIZE - (age * size * Tile.SIZE) / 2), (int) (age * size * Tile.SIZE), (int) (age * size * Tile.SIZE));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
