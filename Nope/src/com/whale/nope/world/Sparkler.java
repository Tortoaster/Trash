package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.effects.Spark;
import com.whale.nope.main.Loop;

public class Sparkler extends Tile {
	
	private int time;
	
	public Sparkler(World world) {
		super(false, world);
	}
	
	public void update(int x, int y) {
		if (time % Loop.UPS == 0) for (int i = 0; i < 100; i++) {
			int direction = random.nextInt(21) + 260;
			int speed = random.nextInt(10) + 1;
			world.getGame().getEffects().add(new Spark(x * SIZE + SIZE / 2, y * SIZE + SIZE / 2, Math.cos(Math.toRadians(direction)) * speed, Math.sin(Math.toRadians(direction)) * speed, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()), random.nextDouble(), world.getGame().getEffects()));
		}
		time++;
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(150, 150, 100));
		g.fillRect(x * SIZE + SIZE / 2 - SIZE / 8, y * SIZE + SIZE / 4, SIZE / 4, SIZE / 4 * 3);
	}
}
