package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.effects.Bubble;

public class Bubbler extends Tile {
	
	private int time;
	
	public Bubbler(World world) {
		super(false, world);
	}
	
	public void update(int x, int y) {
		int direction = random.nextInt(180) + 180;
		double speed = random.nextDouble();
		if (time % 10 == 0) world.getGame().getEffects().add(new Bubble(x * SIZE + SIZE / 2, y * SIZE + SIZE / 2, Math.cos(Math.toRadians(direction)) * speed, Math.sin(Math.toRadians(direction)) * speed, random.nextInt(10) + 1, random.nextInt(10) + 1, world.getGame().getEffects()));
		time++;
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(100, 150, 150));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
