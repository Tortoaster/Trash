package com.whale.rogue.effects;

import java.awt.Color;
import java.util.Random;

import com.whale.rogue.main.Window;

public class Fire {
	
	private double x, y;
	
	private int amount;
	
	private Color start = new Color(250, 225, 30), end = new Color(240, 0, 10);
	
	private Random random = new Random();
	
	private final Effects effects;
	
	public Fire(double x, double y, int amount, Effects effects) {
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.effects = effects;
	}
	
	public Fire(double x, double y, int amount, Color color, Effects effects) {
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.effects = effects;
		start = Color.white;
		end = color;
	}
	
	public Fire(double x, double y, int amount, Color start, Color end, Effects effects) {
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.start = start;
		this.end = end;
		this.effects = effects;
	}
	
	public void update() {
		for(int i = 0; i < amount; i++) {
			double angle = random.nextDouble() * 180;
			effects.add(new Particle(x + 0.5, y + 0.5, 0.3, angle, 0.03, 0.001, (90 - angle) / 10, 0, (int) (random.nextDouble() * Window.RATE / 2), start, end, effects));
		}
	}
}
