package com.whale.nope.effects;

import java.awt.Color;
import java.util.Random;
import com.whale.nope.main.Loop;

public class Fire {
	
	private int x, y, intensity, time;
	
	private Color start, end;
	
	private Effects effects;
	
	private Random random = new Random();
	
	public Fire(int x, int y, int intensity, Color start, Color end, Effects effects) {
		this.x = x;
		this.y = y;
		this.intensity = Math.max(1, Loop.UPS / intensity);
		this.start = start;
		this.end = end;
		this.effects = effects;
	}
	
	public void update() {
		if ((time % intensity) == 0) {
			int direction = random.nextInt(360);
			double speed = 1 + random.nextDouble() / 2;
			effects.add(new Flame(x, y, Math.cos(Math.toRadians(direction)) * speed, Math.sin(Math.toRadians(direction)) * speed, start, end, 0.5, effects));
		}
		time++;
	}
	
	public void setStartColor(Color color) {
		start = color;
	}
	
	public void setEndColor(Color color) {
		end = color;
	}
}
