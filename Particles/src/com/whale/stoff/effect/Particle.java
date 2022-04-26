package com.whale.stoff.effect;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import com.whale.stoff.main.Loop;

public class Particle {
	
	public enum Type {
		FIRE, ICE, GRASS, AETHER;
	}
	
	private double x, xV, y, yV;
	
	private int life, time;
	
	private Effect effects;
	
	private Random random = new Random();
	
	private Type type;
	
	public Particle(double x, double y, double s, double a, Type type, Effect effects) {
		this.x = x;
		this.y = y;
		this.xV = Math.cos(Math.toRadians(a)) * s;
		this.yV = Math.sin(Math.toRadians(a)) * s;
		this.type = type;
		this.effects = effects;
		life = (int) (random.nextInt(5) * random.nextDouble() * Loop.UPS) + 1;
	}
	
	public Particle(double x, double y, double s, double a, int life, Type type, Effect effects) {
		this.x = x;
		this.y = y;
		this.xV = Math.cos(Math.toRadians(a)) * s;
		this.yV = Math.sin(Math.toRadians(a)) * s;
		this.life = random.nextInt(life + 1) + 1;
		this.type = type;
		this.effects = effects;
	}
	
	public void update() {
		x += xV;
		y += yV;
		if (time >= life) effects.remove(this);
		time++;
	}
	
	public void draw(Graphics g) {
		switch (type) {
		case FIRE:
			g.setColor(new Color(250, Math.max(0, Math.min(250, 250 - (int) (400.0 * time / life))), Math.max(0, Math.min(250, 100 - (int) (800.0 * time / life)))));
			g.fillRect((int) (x - 4 + 4 * time / life), (int) (y - 4 + 4 * time / life), 8 - (int) (8.0 * time / life), 8 - (int) (8.0 * time / life));
			break;
		case ICE:
			g.setColor(new Color(Math.max(0, Math.min(250, 250 - (int) (500.0 * time / life))), Math.max(0, Math.min(250, 250 - (int) (150.0 * time / life))), 250));
			g.fillRect((int) (x - 2 + 2 * time / life), (int) (y - 2 + 2 * time / life), 4 - (int) (4.0 * time / life), 4 - (int) (4.0 * time / life));
			break;
		case GRASS:
			g.setColor(new Color(Math.max(0, Math.min(250, 250 - (int) (500.0 * time / life))), 250, Math.max(0, Math.min(250, 100 - (int) (500.0 * time / life)))));
			g.fillRect((int) (x - 2 + 2 * time / life), (int) (y - 2 + 2 * time / life), 4 - (int) (4.0 * time / life), 4 - (int) (4.0 * time / life));
			break;
		case AETHER:
			g.setColor(new Color(Math.max(0, Math.min(250, 250 - (int) (500.0 * time / life))), 0, 250));
			g.fillRect((int) (x - 2 + 2 * time / life), (int) (y - 2 + 2 * time / life), 4 - (int) (4.0 * time / life), 4 - (int) (4.0 * time / life));
			break;
		default:
			g.setColor(new Color(100, 100, 100));
			g.fillRect((int) x - 1, (int) y - 1, 2, 2);
		}
	}
}
