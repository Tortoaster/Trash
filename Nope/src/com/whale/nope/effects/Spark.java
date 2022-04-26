package com.whale.nope.effects;

import java.awt.Color;
import java.awt.Graphics;

public class Spark extends Particle {
	
	private Color color;
	
	public Spark(double x, double y, double xV, double yV, Color color, double time, Effects effects) {
		super(x, y, xV, yV, time, effects);
		this.color = color;
	}
	
	public void update() {
		yV = Math.max(-(Math.max(age, time - 30) - age), yV - 0.1);
		xV = Math.max(-(Math.max(age, time - 30) - age), Math.min((Math.max(age, time - 30) - age) / 5, xV));
		if (effects.getGame().getWorld().isBlocked((int) (x + xV), (int) y)) {
			xV *= -0.5;
		}
		if (effects.getGame().getWorld().isBlocked((int) x, (int) (y + yV))) {
			yV *= -0.5;
		}
		x += xV;
		y += yV;
		age++;
		if (age >= time) effects.remove(this);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (250 * (1 - ((double) age / time)))));
		g.fillRect((int) (x - Math.max(1, Math.abs(Math.sqrt(xV * xV + yV * yV)) / 2)), (int) (y - Math.max(1, Math.abs(Math.sqrt(xV * xV + yV * yV)) / 2)), (int) Math.max(1, Math.abs(Math.sqrt(xV * xV + yV * yV))), (int) Math.max(1, Math.abs(Math.sqrt(xV * xV + yV * yV))));
	}
}
