package com.whale.nope.effects;

import java.awt.Color;
import java.awt.Graphics;

public class Flame extends Particle {
	
	private Color start, end;
	
	public Flame(double x, double y, double xV, double yV, Color start, Color end, double time, Effects effects) {
		super(x, y, xV, yV, time, effects);
		this.start = start;
		this.end = end;
	}
	
	public void update() {
		yV = Math.max(-10, yV - 0.1);
		xV -= xV / 8;
		if (effects.getGame().getWorld().isBlocked((int) (x + xV), (int) y)) {
			xV = 0;
		}
		if (effects.getGame().getWorld().isBlocked((int) x, (int) (y + yV))) {
			yV = 0;
		}
		x += xV;
		y += yV;
		age++;
		if (age >= time) effects.remove(this);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(start.getRed() + (int) ((double) age / time * (end.getRed() - start.getRed())), start.getGreen() + (int) ((double) age / time * (end.getGreen() - start.getGreen())), start.getBlue() + (int) ((double) age / time * (end.getBlue() - start.getBlue())), (int) Math.max(0, 250 - 250 * ((double) age / time))));
		g.fillRect((int) (x - Math.min(12, time - age) / 2), (int) (y - Math.min(12, time - age) / 2), Math.min(12, time - age), Math.min(12, time - age));
	}
}
