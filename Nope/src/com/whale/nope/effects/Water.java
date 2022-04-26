package com.whale.nope.effects;

import java.awt.Color;
import java.awt.Graphics;

public class Water extends Particle {
	
	public Water(double x, double y, double xV, double yV, double time, Effects effects) {
		super(x, y, xV, yV, time, effects);
	}
	
	public void update() {
		yV = Math.min(5, yV + 0.2);
		if (effects.getGame().getWorld().isBlocked((int) (x + xV), (int) y)) {
			effects.remove(this);
		}
		if (effects.getGame().getWorld().isBlocked((int) x, (int) (y + yV))) {
			effects.remove(this);
		}
		x += xV;
		y += yV;
		age++;
		if (age >= time) effects.remove(this);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(80, 100, 200));
		g.fillRect((int) x - (int) Math.min(3, Math.abs(Math.sqrt(xV * xV + yV * yV))), (int) y - (int) Math.min(3, Math.abs(Math.sqrt(xV * xV + yV * yV))), (int) (Math.abs(Math.min(3, Math.sqrt(xV * xV + yV * yV))) * 2), (int) (Math.abs(Math.min(3, Math.sqrt(xV * xV + yV * yV)) * 2)));
	}
}
