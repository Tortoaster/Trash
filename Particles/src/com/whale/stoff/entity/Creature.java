package com.whale.stoff.entity;

import java.awt.Graphics;
import java.util.Random;

public abstract class Creature {
	
	protected double x, y, xV, yV, xD, yD;
	
	protected Entity entities;
	
	protected Random random = new Random();
	
	public Creature(double x, double y, Entity entities) {
		this.x = x;
		this.y = y;
		this.entities = entities;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		
	}
}
