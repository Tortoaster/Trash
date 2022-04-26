package com.rook.modifier;

import com.rook.life.Entity;
import com.rook.life.Projectile;

public abstract class Enchantment {
	
	public static final int FLEETING = 0, FIRE = 1, POISON = 2, EXPLOSIVE = 3, VAMPIRIC = 4, HOMING = 5, RICOCHETING = 6, PHASING = 7, PIERCING = 8, SEEKING = 9;
	
	protected int duration, type;
	
	protected Projectile projectile;
	
	public Enchantment(int duration, int type, Projectile projectile) {
		this.duration = duration;
		this.type = type;
		this.projectile = projectile;
	}
	
	public void onImpact(Entity e) {}
	
	public void update() {
		if(duration <= 0) {
			projectile.removeModifier(this);
		}
		duration--;
	}
	
	public void addDuration(int duration) {
		setDuration(getDuration() + duration);
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getType() {
		return type;
	}
}
