package com.rook.modifier;

import com.rook.life.Creature;

public class Buff {
	
	public static final int BURNING = 0, POISONED = 1, SCARED = 2, CHARMED = 3;
	
	protected int duration, type;
	
	protected Creature creature;
	
	public Buff(int duration, int type, Creature creature) {
		this.duration = duration;
		this.type = type;
		this.creature = creature;
	}
	
	public void onInflict() {}
	
	public void onCure() {}
	
	public void update() {
		if(duration <= 0) {
			creature.cure(this);
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
