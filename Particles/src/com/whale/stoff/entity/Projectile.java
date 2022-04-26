package com.whale.stoff.entity;

import java.awt.Graphics;
import java.util.Random;
import com.whale.stoff.effect.Particle;
import com.whale.stoff.main.Loop;

public class Projectile {
	
	public enum Type {
		FIREBALL, ICEBALL, BUTTERFLY, STAR;
	}
	
	private double x, y, xV, yV;
	
	private int life, time;
	
	private Entity entities;
	
	private Random random = new Random();
	
	private Type type;
	
	public Projectile(double x, double y, double s, double a, Type type, Entity entities) {
		switch (type) {
		case FIREBALL:
			a -= 45;
			break;
		default:
		}
		this.x = x;
		this.y = y;
		this.xV = Math.cos(Math.toRadians(a)) * s;
		this.yV = Math.sin(Math.toRadians(a)) * s;
		this.type = type;
		this.entities = entities;
		life = Loop.UPS * 2;
	}
	
	public void update() {
		switch (type) {
		case FIREBALL:
			entities.getGame().getEffects().add(new Particle(x, y, 0.8, Math.toDegrees(Math.atan2(yV, xV) + 170 + random.nextInt(21)), life - time + 1, Particle.Type.FIRE, entities.getGame().getEffects()));
			double angle = Math.toDegrees(Math.atan2(yV, xV));
			angle += Math.sin(Math.toRadians(time * 6)) * 5;
			xV = Math.cos(Math.toRadians(angle)) * Math.sqrt(xV * xV + yV * yV);
			yV = Math.sin(Math.toRadians(angle)) * Math.sqrt(xV * xV + yV * yV);
			break;
		case ICEBALL:
			entities.getGame().getEffects().add(new Particle(x, y, 0.4, Math.toDegrees(Math.atan2(yV, xV)), Particle.Type.ICE, entities.getGame().getEffects()));
			break;
		case BUTTERFLY:
			for (double i = 0; i < 6.2; i += random.nextDouble() / 10) {
				double x = this.x + (30.0 * time / life) * Math.cos(i + Math.atan2(yV, xV)) * (Math.pow(Math.E, Math.cos(i)) - 2 * Math.cos(4 * i));
				double y = this.y + (30.0 * time / life) * Math.sin(i + Math.atan2(yV, xV)) * (Math.pow(Math.E, Math.cos(i)) - 2 * Math.cos(4 * i));
				entities.getGame().getEffects().add(new Particle(x, y, 4.6, Math.toDegrees(Math.atan2(yV, xV)), Loop.UPS / 6, Particle.Type.GRASS, entities.getGame().getEffects()));
			}
			break;
		case STAR:
			double R = 100;
			double r = 60;
			double d = 60.0 * time / Loop.UPS;
			for (double i = 0; i < 19; i += random.nextDouble() / 10) {
				double x = this.x + (R - r) * Math.cos(i + Math.atan2(yV, xV)) + d * Math.cos((R - r) / r * i - Math.atan2(yV, xV));
				double y = this.y + (R - r) * Math.sin(i + Math.atan2(yV, xV)) - d * Math.sin((R - r) / r * i - Math.atan2(yV, xV));
				entities.getGame().getEffects().add(new Particle(x, y, 0.2, Math.toDegrees(Math.atan2(yV, xV)), Loop.UPS / 2, Particle.Type.AETHER, entities.getGame().getEffects()));
			}
		}
		x += xV;
		y += yV;
		if (time >= life) {
			switch (type) {
			case ICEBALL:
				if (life == Loop.UPS * 2) for (int i = 0; i < 6; i++) {
					Projectile p = new Projectile(x, y, Math.sqrt(xV * xV + yV * yV), 60 * i + Math.toDegrees(Math.atan2(yV, xV)), Type.ICEBALL, entities);
					p.setLife(life / 2);
					entities.add(p);
				} else if (life > Loop.UPS / 4) {
					for (int i = 1; i < 4; i += 2) {
						Projectile p1 = new Projectile(x - xV * (life / 4 * i), y - yV * (life / 4 * i), Math.sqrt(xV * xV + yV * yV), Math.toDegrees(Math.atan2(yV, xV)) - 20 * i, Type.ICEBALL, entities);
						Projectile p2 = new Projectile(x - xV * (life / 4 * i), y - yV * (life / 4 * i), Math.sqrt(xV * xV + yV * yV), Math.toDegrees(Math.atan2(yV, xV)) + 20 * i, Type.ICEBALL, entities);
						p1.setLife(life / 2);
						p2.setLife(life / 2);
						entities.add(p1);
						entities.add(p2);
					}
				}
				break;
			default:
			}
			entities.remove(this);
		}
		time++;
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Type getType() {
		return type;
	}
}
