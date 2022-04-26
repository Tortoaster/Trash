package com.whale.stoff.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import com.whale.stoff.effect.Particle;
import com.whale.stoff.main.Loop;

public class Torch extends Creature {
	
	public Torch(double x, double y, Entity entities) {
		super(x, y, entities);
	}
	
	public void update() {
		xV = Math.cos(Math.atan2(yD - y, xD - x));
		yV = Math.sin(Math.atan2(yD - y, xD - x));
		List<Projectile> list = entities.getProjectiles();
		boolean found = false;
		for (Projectile p: list) {
			if (p.getType() == Projectile.Type.BUTTERFLY) {
				xD = p.getX();
				yD = p.getY();
				found = true;
				break;
			}
		}
		if (!found) {
			xD = x;
			yD = y;
			xV = 0;
			yV = 0;
		}
		x += xV;
		y += yV;
		entities.getGame().getEffects().add(new Particle(x, y, 0.8, Math.toDegrees(Math.atan2(yV, xV) + 170 + random.nextInt(21)), Loop.UPS, Particle.Type.FIRE, entities.getGame().getEffects()));
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(150, 50, 100));
		g.fillRect((int) x - 2, (int) y, 4, 8);
	}
}
